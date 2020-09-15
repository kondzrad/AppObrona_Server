package pl.kawka.appobronaserver.dao;

import pl.kawka.appobronaserver.model.Customer;

import java.util.List;

public interface CustomerDAO {

    String postCustomerCreate(Customer customerCreate);

    List<Customer> getAllCustomerList();

    List<Customer> postCustomerRead(Customer customerRead);

    String postCustomerDelete(Customer customerDelete);

    String postCustomerUpdate(Customer customerUpdate);
}
