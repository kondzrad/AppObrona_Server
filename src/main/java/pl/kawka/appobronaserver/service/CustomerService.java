package pl.kawka.appobronaserver.service;

import pl.kawka.appobronaserver.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomerList();

    String postCustomerCreate(Customer customerCreate);

    List<Customer> postCustomerRead(Customer customerRead);

    String postCustomerDelete(Customer customerDelete);

    String postCustomerUpdate(Customer customerUpdate);
}
