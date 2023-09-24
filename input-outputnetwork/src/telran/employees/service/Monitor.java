package telran.employees.service;

public record Monitor(Lock read_lock, Lock write_lock) {

}
