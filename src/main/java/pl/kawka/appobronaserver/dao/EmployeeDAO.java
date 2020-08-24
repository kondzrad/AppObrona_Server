package pl.kawka.appobronaserver.dao;

import pl.kawka.appobronaserver.model.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> getPracownikSQL();

    String getLogowanie(Employee employee);
}
