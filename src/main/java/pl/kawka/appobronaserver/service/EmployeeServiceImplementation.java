package pl.kawka.appobronaserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kawka.appobronaserver.dao.EmployeeDAO;
import pl.kawka.appobronaserver.model.EmployeeLogin;

import java.util.List;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Transactional
    @Override
    public List<EmployeeLogin> getPracownikSQL() {
        return employeeDAO.getPracownikSQL();
    }

    @Override
    public String getLogowanie(EmployeeLogin employeeLoginLogowanie) {
        return employeeDAO.getLogowanie(employeeLoginLogowanie);
    }

}
