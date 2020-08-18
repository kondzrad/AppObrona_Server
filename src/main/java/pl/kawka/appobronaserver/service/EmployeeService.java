package pl.kawka.appobronaserver.service;

import pl.kawka.appobronaserver.model.EmployeeLogin;

import java.util.List;

public interface EmployeeService {

    List<EmployeeLogin> getPracownikSQL();

    String getLogowanie(EmployeeLogin employeeLoginLogowanie);

}
