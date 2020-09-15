package pl.kawka.appobronaserver.service;

import pl.kawka.appobronaserver.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> restGetAllCustomerList();

    String restPostCustomerCreate(Customer customerCreate);

    List<Customer> restPostCustomerRead(Customer customerRead);

    String restDeleteCustomerDelete(Customer customerDelete);

    String restPutCustomerUpdate(Customer customerUpdate);
}
