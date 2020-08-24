package pl.kawka.appobronaserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kawka.appobronaserver.dao.CustomerDAO;
import pl.kawka.appobronaserver.dao.CustomerDAOImplementation;
import pl.kawka.appobronaserver.model.Customer;

import java.util.List;

@Service
public class CustomerServiceImplementation implements CustomerService{

    @Autowired
    CustomerDAOImplementation customerDAO;

    @Override
    public List<Customer> getAllCustomerList() {
        return customerDAO.getAllCustomerList();
    }

    @Override
    public String postCustomerCreate(Customer customerCreate) {
        return customerDAO.postCustomerCreate(customerCreate);
    }

}
