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

    @GetMapping //wczytanie wszystkich pracownikow
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/login")  //przyjecie json'ow do zalogowania
    public String login(@RequestBody JSONObject requestPara) {

        //musi byc konstruktor w EmployeeController
        Employee employeeLogowanie = new Employee(requestPara.get("login").toString(),
                requestPara.get("password").toString()); //przypisania loginu i hasla z JSON do pracownika
        System.out.println(employeeLogowanie);
        System.out.println(requestPara.get("login").toString());

        return employeeService.getLogowanie(employeeLogowanie);
    }

    @PostMapping("/create")  //przyjecie json'ow do stworzenia pracownika
    public String create(@RequestBody JSONObject requestPara) {

        //musi byc konstruktor w EmployeeController - bez ID
        Employee employeeCreate = new Employee(
                //Integer.parseInt(requestPara.get("id").toString()),
                requestPara.get("firstName").toString(),
                requestPara.get("lastName").toString(),
                requestPara.get("status").toString(),
                requestPara.get("login").toString(),
                requestPara.get("password").toString());
        //przypisania danych z JSON do nowego pracownika
        System.out.println("Z controllera: " + employeeCreate);

        return employeeService.postEmployeeCreate(employeeCreate);
    }

    @PostMapping("/read")  //przyjecie json'ow do wczytania pracownika
    public List<Employee> read(@RequestBody JSONObject requestPara) {

        //musi byc konstruktor w EmployeeController
        Employee employeeRead = new Employee(
                Integer.parseInt(requestPara.get("id").toString()),
                requestPara.get("firstName").toString(),
                requestPara.get("lastName").toString(),
                requestPara.get("status").toString(),
                requestPara.get("login").toString(),
                requestPara.get("password").toString());
        //przypisania danych z JSON do nowego pracownika
        System.out.println("Z controllera: " + employeeRead);

        return employeeService.postEmployeeRead(employeeRead);
    }

    @PostMapping("/update")  //przyjecie json'ow do stworzenia pracownika
    public String update(@RequestBody JSONObject requestPara) {

        //musi byc konstruktor w EmployeeController
        Employee employeeUpdate = new Employee(
                Integer.parseInt(requestPara.get("id").toString()),
                requestPara.get("firstName").toString(),
                requestPara.get("lastName").toString(),
                requestPara.get("status").toString(),
                requestPara.get("login").toString(),
                requestPara.get("password").toString());
        //przypisania danych z JSON do nowego pracownika
        System.out.println("Z controllera: " + employeeUpdate);

        return employeeService.postEmployeeUpdate(employeeUpdate);
    }

    @PostMapping("/delete")  //przyjecie json'ow do stworzenia pracownika
    public String delete(@RequestBody JSONObject requestPara) {

        //musi byc konstruktor w EmployeeController
        Employee employeeDelete = new Employee(
                Integer.parseInt(requestPara.get("id").toString())
        );
        //przypisania danych z JSON do nowego pracownika
        System.out.println("Z controllera: " + employeeDelete);

        return employeeService.postEmployeeDelete(employeeDelete);
    }

}
