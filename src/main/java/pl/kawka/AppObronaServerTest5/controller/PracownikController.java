package pl.kawka.AppObronaServerTest5.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.kawka.AppObronaServerTest5.model.PracownikSQL;
import pl.kawka.AppObronaServerTest5.service.PracownikService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PracownikController {

    @Autowired
    private PracownikService pracownikService;

    @GetMapping("/employee")
    public List<PracownikSQL> getPracownikSQL(@RequestBody JSONObject requestPara){

        //by nie bylo bledu musi byc konstruktor w PracownikController
        PracownikSQL pracownikSQLaaa = new PracownikSQL(requestPara.get("login").toString(),
                requestPara.get("password").toString());
        System.out.println(pracownikSQLaaa);
        System.out.println(requestPara.get("login").toString());

        return pracownikService.getPracownikSQL();
    }

    @PostMapping("/employee2")
    public String logowanie(@RequestBody JSONObject requestPara){

        //by nie bylo bledu musi byc konstruktor w PracownikController
        PracownikSQL pracownikSQLaaa2 = new PracownikSQL(requestPara.get("login").toString(),
                requestPara.get("password").toString()); //przypisania loginu i hasla z JSON do pracownika2
        System.out.println(pracownikSQLaaa2);
        System.out.println(requestPara.get("login").toString());

        return pracownikService.getLogowanie(pracownikSQLaaa2);
    }

}
