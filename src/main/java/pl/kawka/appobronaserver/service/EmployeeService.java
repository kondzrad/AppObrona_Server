package pl.kawka.appobronaserver.service;

import pl.kawka.appobronaserver.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> restGetAllEmployees();

    String restPostLoginEmployee(Employee employeeLogin);

    String restPostEmployeeCreate(Employee employeeCreate);

    List<Employee> restPostEmployeeRead(Employee employeeRead);

    String restPutEmployeeUpdate(Employee employeeUpdate);

    String restDeleteEmployeeDelete(Employee employeeDelete);
}
