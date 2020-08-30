package pl.kawka.appobronaserver.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.kawka.appobronaserver.model.Customer;
import pl.kawka.appobronaserver.model.Employee;
import pl.kawka.appobronaserver.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping //jak wysle jsona z log i haslo to da mi liste pracownikow
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @PostMapping("/login")  //przyjecie json'ow do zalogowania
    public String logowanie(@RequestBody JSONObject requestPara){

        //by nie bylo bledu musi byc konstruktor w EmployeeController
        Employee employeeLogowanie = new Employee(requestPara.get("login").toString(),
                requestPara.get("password").toString()); //przypisania loginu i hasla z JSON do pracownika
        System.out.println(employeeLogowanie);
        System.out.println(requestPara.get("login").toString());

        return employeeService.getLogowanie(employeeLogowanie);
    }

    @PostMapping("/create")  //przyjecie json'ow do stworzenia clienta
    public String tworzenie(@RequestBody JSONObject requestPara){

        //by nie bylo bledu musi byc konstruktor w CustomerController - bez ID bo nie chcemy wyslac ID
        Employee employeeCreate = new Employee(
                //Integer.parseInt(requestPara.get("id").toString()),
                requestPara.get("firstName").toString(),
                requestPara.get("lastName").toString(),
                requestPara.get("status").toString(),
                requestPara.get("login").toString(),
                requestPara.get("password").toString());
        //przypisania danych z JSON do nowego klienta
        System.out.println("Z controllera" + employeeCreate);

        return employeeService.postEmployeeCreate(employeeCreate);
    }

    @PostMapping("/read")  //przyjecie json'ow do wczytania clienta
    public List<Employee> wczytanie(@RequestBody JSONObject requestPara){

        //by nie bylo bledu musi byc konstruktor w CustomerController
        Employee employeeRead = new Employee(
                Integer.parseInt(requestPara.get("id").toString()),
                requestPara.get("firstName").toString(),
                requestPara.get("lastName").toString(),
                requestPara.get("status").toString(),
                requestPara.get("login").toString(),
                requestPara.get("password").toString());
        //przypisania danych z JSON do nowego klienta
        System.out.println(employeeRead);

        return employeeService.postEmployeeRead(employeeRead);
    }

}
