package pl.kawka.appobronaserver.service;

import pl.kawka.appobronaserver.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getPracownikSQL();

    String getLogowanie(Employee employeeLogowanie);

}
