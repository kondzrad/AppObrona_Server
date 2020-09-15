package pl.kawka.appobronaserver.service;

import pl.kawka.appobronaserver.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    String getLogowanie(Employee employeeLogowanie);

    String postEmployeeCreate(Employee employeeCreate);

    List<Employee> postEmployeeRead(Employee employeeRead);

    String postEmployeeUpdate(Employee employeeUpdate);

    String postEmployeeDelete(Employee employeeDelete);
}
