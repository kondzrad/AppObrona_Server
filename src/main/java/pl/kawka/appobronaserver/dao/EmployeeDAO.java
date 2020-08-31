package pl.kawka.appobronaserver.dao;

import pl.kawka.appobronaserver.model.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> getAllEmployees();

    String getLogowanie(Employee employee);

    String postEmployeeCreate(Employee employeeCreate);

    List<Employee> postEmployeeRead(Employee employeeRead);

    String postEmployeeUpdate(Employee employeeUpdate);
}
