package pl.kawka.appobronaserver.dao;

import pl.kawka.appobronaserver.model.EmployeeLogin;

import java.util.List;

public interface EmployeeDAO {

    List<EmployeeLogin> getPracownikSQL();

    String getLogowanie(EmployeeLogin employeeLogin);
}
