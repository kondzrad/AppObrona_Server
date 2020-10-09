package pl.kawka.graduationworkappserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kawka.graduationworkappserver.dao.CustomerDAOImplementation;
import pl.kawka.graduationworkappserver.model.Customer;

import java.util.List;

@Service
public class CustomerServiceImplementation implements CustomerService {

    @Autowired
    CustomerDAOImplementation customerDAO;

    @Override
    public List<Customer> restGetAllCustomerList() {
        return customerDAO.restGetAllCustomerList();
    }

    @Override
    public String restPostCustomerCreate(Customer customerCreate) {
        return customerDAO.restPostCustomerCreate(customerCreate);
    }

    @Override
    public List<Customer> restPostCustomerRead(Customer customerRead) {
        return customerDAO.restPostCustomerRead(customerRead);
    }

    @Override
    public String restDeleteCustomerDelete(Customer customerDelete) {
        return customerDAO.restDeleteCustomerDelete(customerDelete);
    }

    @Override
    public String restPutCustomerUpdate(Customer customerUpdate) {
        return customerDAO.restPutCustomerUpdate(customerUpdate);
    }

}
