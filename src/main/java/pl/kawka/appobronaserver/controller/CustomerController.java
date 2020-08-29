package pl.kawka.appobronaserver.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/create")  //przyjecie json'ow do stworzenia clienta
    public String tworzenie(@RequestBody JSONObject requestPara){

        //by nie bylo bledu musi byc konstruktor w CustomerController - bez ID bo nie chcemy wyslac ID
        Customer customerCreate = new Customer(
                //Integer.parseInt(requestPara.get("id").toString()),
                requestPara.get("firstName").toString(),
                requestPara.get("lastName").toString(),
                requestPara.get("town").toString(),
                requestPara.get("street").toString(),
                requestPara.get("postcode").toString(),
                requestPara.get("telephoneNumber").toString(),
                requestPara.get("nip").toString());
                //requestPara.get("dateAdded").toString());
        //przypisania danych z JSON do nowego klienta
        System.out.println("Z controllera" + customerCreate);

        return customerService.postCustomerCreate(customerCreate);
    }

    @PostMapping("/read")  //przyjecie json'ow do stworzenia clienta
    public List<Customer> wczytanie(@RequestBody JSONObject requestPara){

        //by nie bylo bledu musi byc konstruktor w CustomerController
        Customer customerRead = new Customer(
                Integer.parseInt(requestPara.get("id").toString()),
                requestPara.get("firstName").toString(),
                requestPara.get("lastName").toString(),
                requestPara.get("town").toString(),
                requestPara.get("street").toString(),
                requestPara.get("postcode").toString(),
                requestPara.get("telephoneNumber").toString(),
                requestPara.get("nip").toString(),
                requestPara.get("dateAdded").toString());
        //przypisania danych z JSON do nowego klienta
        System.out.println(customerRead);

        return customerService.postCustomerRead(customerRead);
    }


    @PostMapping("/delete")  //przyjecie json'ow do stworzenia clienta
    public List<Customer> usuwanie(@RequestBody JSONObject requestPara){

        //by nie bylo bledu musi byc konstruktor w CustomerController
        Customer customerDelete = new Customer(
                Integer.parseInt(requestPara.get("id").toString())
                );
        //przypisania danych z JSON do nowego klienta
        System.out.println("Controller usuwanie" + customerDelete);

        return customerService.postCustomerDelete(customerDelete);
    }

}
