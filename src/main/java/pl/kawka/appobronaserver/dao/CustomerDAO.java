package pl.kawka.appobronaserver.dao;

import pl.kawka.appobronaserver.model.Customer;

import java.util.List;

public interface CustomerDAO {

    String restPostCustomerCreate(Customer customerCreate);

    List<Customer> restGetAllCustomerList();

    List<Customer> restPostCustomerRead(Customer customerRead);

    String restPutCustomerUpdate(Customer customerUpdate);

    String restDeleteCustomerDelete(Customer customerDelete);
}
