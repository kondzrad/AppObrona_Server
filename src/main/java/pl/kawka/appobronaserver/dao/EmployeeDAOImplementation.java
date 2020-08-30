package pl.kawka.appobronaserver.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.kawka.appobronaserver.model.Employee;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDAOImplementation implements EmployeeDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Employee> getAllEmployees() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Employee> query = currentSession.createQuery("from Employee", Employee.class);
        List<Employee> list = query.getResultList();
        System.out.println(list);

        return list;
    }

    @Override
    public String getLogowanie(Employee employeeLogowanie) {

        System.out.println(employeeLogowanie);
        String login1 = employeeLogowanie.getLogin();
        System.out.println(login1); //odczyt loginu z JSONA co przyszedl
        String password1 = employeeLogowanie.getPassword();
        System.out.println(password1); //odczyt hasla z JSONA co przyszedl

        ////////////////////////////////
        //do analizy

/*
        Query query = session.createQuery("update User set count = count + :count" +" where id = :Id");
        query.setParameter("Id", id);
        query.setParameter("count", count);

*/

        ///////////////////


        Session currentSession = entityManager.unwrap(Session.class);
        Query<Employee> query = currentSession.createQuery("from Employee " +
                        "WHERE login='"+login1+"' and password='"+password1+"' ",
                Employee.class);
        List<Employee> list = query.getResultList();
        System.out.println("Logowanie lista :" + list);

        System.out.println("Status pracownika: " + list.toString().contains("status='pracownik'"));
        System.out.println("Status admina: " + list.toString().contains("status='admin'"));

        if (list.isEmpty()){
            System.out.println("nieudane logowanie");
            return "zle";
        } else if (list.toString().contains("status='pracownik'")){
            System.out.println("udane logowanie pracownika");
            return "OKpracownik";
        }  else if (list.toString().contains("status='admin'")){
        System.out.println("udane logowanie admina");
        return "OKadmin";
        }
        else{
            System.out.println("nieudane logowanie2");
            return "zle2";
        }




    }
}
