package pl.kawka.appobronaserver.dao;

import pl.kawka.appobronaserver.model.Customer;

import java.util.List;

public interface CustomerDAO {

    String postCustomerCreate(Customer customerCreate);

    public List<Customer> getAllCustomerList();

    List<Customer> postCustomerRead(Customer customerRead);

    List<Customer> postCustomerDelete(Customer customerDelete);
}
