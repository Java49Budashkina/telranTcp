package telran.employees;

import java.io.Serializable;
import java.util.ArrayList;

import telran.employees.controller.CompanyController;
import telran.employees.dto.*;

import telran.employees.service.Company;
import telran.net.ApplProtocol;
import telran.net.Request;
import telran.net.Response;
import telran.net.ResponseCode;
import telran.view.Item;

public class CompanyProtocol implements ApplProtocol {

	private Company company;

	public CompanyProtocol(Company company) {
		this.company = company;
	}

	@Override
	public Response getResponse(Request request) {
		Response response = null;
		String requestType = request.requestType();
		Serializable data = request.requestData();
		try {
			Serializable responseData = switch(requestType) {
			case "employee/add" -> employee_add(data);
			case "employee/remove" -> employee_remove(data);
			case "employee/get" -> employee_get(data);
			case "employees/get" -> employees_get(data);
			case "department/salary/distribution" -> department_salary_distribution(data);
			case "salary/distribution" -> salary_distribution(data);
			case "employees/department" -> employees_department(data);
			case "employees/salary" -> employees_salary(data);
			case "employees/age" -> employees_age(data);
			case "salary/update" -> salary_update(data);
			case "department/update" -> department_update(data);
			
			default -> new Response(ResponseCode.WRONG_TYPE, requestType +
			    		" is unsupported in the Company Protocol");
			};
			response = (responseData instanceof Response) ? 
					(Response) responseData :
				new Response(ResponseCode.OK, responseData);
			
		} catch (Exception e) {
			response = new Response(ResponseCode.WRONG_DATA, e.toString());
		}
		return response;
	}

	 private Serializable salary_update(Serializable data) {
		 @SuppressWarnings("unchecked")
			UpdateData<String> updateData = (UpdateData<String>) data;
			long id = updateData.id();
			int salary = Integer.parseInt(updateData.data());
		return company.updateSalary(id, salary);
	}

	private Serializable employees_age(Serializable data) {
		FromTo rang= (FromTo) data;
		return new ArrayList<>( company.getEmployeesByAge(rang.from(), rang.to()));
	}

	private Serializable employees_salary(Serializable data) {
		FromTo rang= (FromTo) data;
		return new ArrayList<>( company.getEmployeesBySalary(rang.from(), rang.to()));

	}

	private Serializable employees_department(Serializable data) {
		String dept = (String) data;
		return new ArrayList<>( company.getEmployeesByDepartment(dept));

	}

	private Serializable salary_distribution(Serializable data) {
		int salary = (int) data;
		return new ArrayList<>( company.getSalaryDistribution(salary));

	}

	private Serializable department_salary_distribution(Serializable data) {
		return new ArrayList<>( company.getDepartmentSalaryDistribution());
	}

	private Serializable employee_remove(Serializable data) {
		int id = (int) data;
		return company.removeEmployee(id);
	}

	private Serializable department_update(Serializable data) {
		
		@SuppressWarnings("unchecked")
		UpdateData<String> updateData = (UpdateData<String>) data;
		long id = updateData.id();
		String department = updateData.data();
		return company.updateDepartment(id, department);
	}

	Serializable employees_get(Serializable data) {
		
		return new ArrayList<> (company.getEmployees());
	}

	Serializable employee_get(Serializable data) {
		long id = (long) data;
		return company.getEmployee(id );
	}

	Serializable employee_add(Serializable data) {
		
		Employee empl = (Employee) data;
		return company.addEmployee(empl);
	}

}

