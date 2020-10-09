package pl.kawka.graduationworkappserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kawka.graduationworkappserver.dao.EmployeeDAO;
import pl.kawka.graduationworkappserver.model.Employee;

import java.util.List;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Transactional
    @Override
    public List<Employee> restGetAllEmployees() {
        return employeeDAO.restGetAllEmployees();
    }

    @Override
    public String restPostLoginEmployee(Employee employeeLogin) {
        return employeeDAO.restPostLogin(employeeLogin);
    }

    @Override
    public String restPostEmployeeCreate(Employee employeeCreate) {
        return employeeDAO.restPostEmployeeCreate(employeeCreate);
    }

    @Override
    public List<Employee> restPostEmployeeRead(Employee employeeRead) {
        return employeeDAO.restPostEmployeeRead(employeeRead);
    }

    @Override
    public String restPutEmployeeUpdate(Employee employeeUpdate) {
        return employeeDAO.restPutEmployeeUpdate(employeeUpdate);
    }

    @Override
    public String restDeleteEmployeeDelete(Employee employeeDelete) {
        return employeeDAO.restDeleteEmployeeDelete(employeeDelete);
    }

}
