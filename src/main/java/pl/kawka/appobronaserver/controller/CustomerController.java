package pl.kawka.appobronaserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kawka.appobronaserver.model.Customer;
import pl.kawka.appobronaserver.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /*@GetMapping("/klienci")  //tez dziala
    public ResponseEntity<List<Customer>> showLoginPage(){
        List<Customer> list = customerService.getAllCustomerList();
        return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
    }*/

    @GetMapping //pobiera baze klientow
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomerList();
    }

}
