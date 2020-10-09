package pl.kawka.graduationworkappserver.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.kawka.graduationworkappserver.model.Employee;
import pl.kawka.graduationworkappserver.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping //wczytanie wszystkich pracownikow
    public List<Employee> restGetAllEmployees() {
        System.out.println("********** Wczytanie bazy danych pracowników **********");
        return employeeService.restGetAllEmployees();
    }

    @PostMapping("/login")  //przyjecie json'ow do zalogowania
    public String restLogin(@RequestBody JSONObject requestPara) {

        System.out.println("********** Logowanie **********");
        //musi byc konstruktor w EmployeeController
        Employee employeeLogin = new Employee(requestPara.get("login").toString(),
                requestPara.get("password").toString()); //przypisania loginu i hasla z JSON do pracownika
        //System.out.println(employeeLogin);

        return employeeService.restPostLoginEmployee(employeeLogin);
    }

    @PostMapping("/create")  //przyjecie json'ow do stworzenia pracownika
    public String restCreate(@RequestBody JSONObject requestPara) {

        System.out.println("********** Tworzenie pracownika **********");
        //musi byc konstruktor w EmployeeController - bez ID
        Employee employeeCreate = new Employee(
                //Integer.parseInt(requestPara.get("id").toString()),
                requestPara.get("firstName").toString(),
                requestPara.get("lastName").toString(),
                requestPara.get("status").toString(),
                requestPara.get("login").toString(),
                requestPara.get("password").toString());
        //przypisania danych z JSON do nowego pracownika
        //System.out.println("Z controllera: " + employeeCreate);

        return employeeService.restPostEmployeeCreate(employeeCreate);
    }

    @PostMapping("/read")  //przyjecie json'ow do wczytania pracownika
    public List<Employee> restRead(@RequestBody JSONObject requestPara) {

        System.out.println("********** Wczytanie pracowników **********");
        //musi byc konstruktor w EmployeeController
        Employee employeeRead = new Employee(
                Integer.parseInt(requestPara.get("id").toString()),
                requestPara.get("firstName").toString(),
                requestPara.get("lastName").toString(),
                requestPara.get("status").toString(),
                requestPara.get("login").toString(),
                requestPara.get("password").toString());
        //przypisania danych z JSON do nowego pracownika
        //System.out.println("Z controllera: " + employeeRead);

        return employeeService.restPostEmployeeRead(employeeRead);
    }

    @PutMapping("/update")  //przyjecie json'ow do modyfikacji pracownika
    public String restUpdate(@RequestBody JSONObject requestPara) {

        System.out.println("********** Modyfikowanie pracownika **********");
        //musi byc konstruktor w EmployeeController
        Employee employeeUpdate = new Employee(
                Integer.parseInt(requestPara.get("id").toString()),
                requestPara.get("firstName").toString(),
                requestPara.get("lastName").toString(),
                requestPara.get("status").toString(),
                requestPara.get("login").toString(),
                requestPara.get("password").toString());
        //przypisania danych z JSON do nowego pracownika
        //System.out.println("Z controllera: " + employeeUpdate);

        return employeeService.restPutEmployeeUpdate(employeeUpdate);
    }

    @DeleteMapping("/delete")  //przyjecie json'ow do usuniecia pracownika
    public String restDelete(@RequestBody JSONObject requestPara) {

        System.out.println("********** Usunięcie pracownika **********");
        //musi byc konstruktor w EmployeeController
        Employee employeeDelete = new Employee(
                Integer.parseInt(requestPara.get("id").toString())
        );
        //przypisania danych z JSON do nowego pracownika
        //System.out.println("Z controllera: " + employeeDelete);

        return employeeService.restDeleteEmployeeDelete(employeeDelete);
    }

}
