package pl.kawka.appobronaserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kawka.appobronaserver.dao.EmployeeDAO;
import pl.kawka.appobronaserver.model.Employee;

import java.util.List;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Transactional
    @Override
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    @Override
    public String getLogowanie(Employee employeeLogowanie) {
        return employeeDAO.getLogowanie(employeeLogowanie);
    }

    @Override
    public String postEmployeeCreate(Employee employeeCreate) {
        return employeeDAO.postEmployeeCreate(employeeCreate);
    }

    @Override
    public List<Employee> postEmployeeRead(Employee employeeRead) {
        return employeeDAO.postEmployeeRead(employeeRead);
    }

    @Override
    public String postEmployeeUpdate(Employee employeeUpdate) {
        return employeeDAO.postEmployeeUpdate(employeeUpdate);
    }

}
