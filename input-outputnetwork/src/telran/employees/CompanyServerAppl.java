package telran.employees;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import telran.employees.service.*;
import telran.net.TcpServer;

public class CompanyServerAppl {

	private static final String DEFAULT_FILE_NAME = "employees.data";
	private static String fileName ;
	private static int PORT = 5000;

	public static void main(String[] args) throws IOException {
		fileName = args.length > 0 ? args[0] : DEFAULT_FILE_NAME;
		ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
		Monitor monitor = new Monitor(rwLock.readLock(), rwLock.writeLock());
		Company company = new CompanyImpl(monitor);
		
		company.restore(fileName );
		TcpServer tcpServer = new TcpServer(PORT , new CompanyProtocol(company));
		tcpServer.run();

}
}