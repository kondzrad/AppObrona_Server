package pl.kawka.appobronaserver.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.kawka.appobronaserver.model.EmployeeLogin;
import pl.kawka.appobronaserver.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public List<EmployeeLogin> getPracownikSQL(@RequestBody JSONObject requestPara){

        //by nie bylo bledu musi byc konstruktor w EmployeeController
        EmployeeLogin pracownikSQLaaa = new EmployeeLogin(requestPara.get("login").toString(),
                requestPara.get("password").toString());
        System.out.println(pracownikSQLaaa);
        System.out.println(requestPara.get("login").toString());

        return employeeService.getPracownikSQL();
    }

    @PostMapping("/login")
    public String logowanie(@RequestBody JSONObject requestPara){

        //by nie bylo bledu musi byc konstruktor w EmployeeController
        EmployeeLogin employeeLoginLogowanie = new EmployeeLogin(requestPara.get("login").toString(),
                requestPara.get("password").toString()); //przypisania loginu i hasla z JSON do pracownika
        System.out.println(employeeLoginLogowanie);
        System.out.println(requestPara.get("login").toString());

        return employeeService.getLogowanie(employeeLoginLogowanie);
    }

}
