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

    @GetMapping //pobieranie bazy klientow
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomerList();
    }

    @PostMapping("/create")  //przyjecie json'ow do stworzenia klienta
    public String create(@RequestBody JSONObject requestPara) {

        //musi byc konstruktor w CustomerController - bez ID i daty
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
        System.out.println("Z controllera: " + customerCreate);

        return customerService.postCustomerCreate(customerCreate);
    }

    @PostMapping("/read")  //przyjecie json'ow do wczytania klienta
    public List<Customer> read(@RequestBody JSONObject requestPara) {

        //musi byc konstruktor w CustomerController
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
        System.out.println("Z controllera: " + customerRead);

        return customerService.postCustomerRead(customerRead);
    }

    @PostMapping("/update")  //przyjecie json'ow do stworzenia klienta
    public String update(@RequestBody JSONObject requestPara) {

        //musi byc konstruktor w CustomerController - bez ID
        Customer customerUpdate = new Customer(
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
        System.out.println("Z controllera: " + customerUpdate);

        return customerService.postCustomerUpdate(customerUpdate);
    }

    @PostMapping("/delete")  //przyjecie json'ow do usuniecia klienta
    public String delete(@RequestBody JSONObject requestPara) {

        //musi byc konstruktor w CustomerController
        Customer customerDelete = new Customer(
                Integer.parseInt(requestPara.get("id").toString())
        );
        //przypisania danych z JSON do nowego klienta
        System.out.println("Z controllera: " + customerDelete);

        return customerService.postCustomerDelete(customerDelete);
    }

}
