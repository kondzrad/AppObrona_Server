package pl.kawka.graduationworkappserver.service;

import pl.kawka.graduationworkappserver.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> restGetAllEmployees();

    String restPostLoginEmployee(Employee employeeLogin);

    String restPostEmployeeCreate(Employee employeeCreate);

    List<Employee> restPostEmployeeRead(Employee employeeRead);

    String restPutEmployeeUpdate(Employee employeeUpdate);

    String restDeleteEmployeeDelete(Employee employeeDelete);
}
