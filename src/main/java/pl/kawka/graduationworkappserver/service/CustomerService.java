package pl.kawka.graduationworkappserver.service;

import pl.kawka.graduationworkappserver.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> restGetAllCustomerList();

    String restPostCustomerCreate(Customer customerCreate);

    List<Customer> restPostCustomerRead(Customer customerRead);

    String restPutCustomerUpdate(Customer customerUpdate);

    String restDeleteCustomerDelete(Customer customerDelete);

}
