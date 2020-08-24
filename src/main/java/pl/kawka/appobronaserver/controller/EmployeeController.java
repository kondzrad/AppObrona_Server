package pl.kawka.appobronaserver.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.kawka.appobronaserver.model.Employee;
import pl.kawka.appobronaserver.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping //jak wysle jsona z log i haslo to da mi liste pracownikow
    public List<Employee> getPracownikSQL(@RequestBody JSONObject requestPara){

        //by nie bylo bledu musi byc konstruktor w EmployeeController
        Employee pracownikSQLaaa = new Employee(requestPara.get("login").toString(),
                requestPara.get("password").toString());
        System.out.println(pracownikSQLaaa);
        System.out.println(requestPara.get("login").toString());

        return employeeService.getPracownikSQL();
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


}
