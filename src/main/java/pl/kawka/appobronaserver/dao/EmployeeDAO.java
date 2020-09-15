package pl.kawka.appobronaserver.dao;

import pl.kawka.appobronaserver.model.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> restGetAllEmployees();

    String restPostLogin(Employee employee);

    String restPostEmployeeCreate(Employee employeeCreate);

    List<Employee> restPostEmployeeRead(Employee employeeRead);

    String restPutEmployeeUpdate(Employee employeeUpdate);

    String restDeleteEmployeeDelete(Employee employeeDelete);
}
