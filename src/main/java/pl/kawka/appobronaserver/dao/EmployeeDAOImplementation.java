package pl.kawka.appobronaserver.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.kawka.appobronaserver.model.Customer;
import pl.kawka.appobronaserver.model.Employee;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

    @Override
    public String postEmployeeCreate(Employee employeeCreate) {

        System.out.println(employeeCreate);

        Session currentSession = entityManager.unwrap(Session.class);

        //stworzenie obiektu klasy Customer
        Employee employee = new Employee();
        //customer.setId(50);  //nie da sie, tylko z automatu mysql se to robi
        employee.setFirstName(employeeCreate.getFirstName()); //pobranie imienia co przyszlo i wstawienie go do
        // tabeli
        employee.setLastName(employeeCreate.getLastName());
        employee.setStatus(employeeCreate.getStatus());
        employee.setLogin(employeeCreate.getLogin());
        employee.setPassword(employeeCreate.getPassword());

        // rozpoczecie transakcji
        currentSession.beginTransaction();
        //zapisanie klienta
        currentSession.save(employee);
        //zakonczenie transakcji
        currentSession.getTransaction().commit();
        //zamkniecie obiektu Session
        currentSession.close();


        return null;
    }

    @Override
    public List<Employee> postEmployeeRead(Employee employeeRead) {

        System.out.println("Wchodze do wczytania klientow");

        Session currentSession = entityManager.unwrap(Session.class);

        List<String> listaMoja= new ArrayList<>();
        if (employeeRead.getId()!=0){
            listaMoja.add("id='"+employeeRead.getId()+"'");
        }
        if (! employeeRead.getFirstName().isEmpty()){
            listaMoja.add("firstName='"+employeeRead.getFirstName()+"'");
        }
        if (! employeeRead.getLastName().equals("")){
            listaMoja.add("lastName='"+employeeRead.getLastName()+"'");
        }
        if (! employeeRead.getStatus().isEmpty()){
            listaMoja.add("status='"+employeeRead.getStatus()+"'");
        }
        if (! employeeRead.getLogin().equals("")){
            listaMoja.add("login='"+employeeRead.getLogin()+"'");
        }
        if (! employeeRead.getPassword().isEmpty()){
            listaMoja.add("password='"+employeeRead.getPassword()+"'");
        }


        String stringKoncowy="";
        for (int i = 0; i < listaMoja.size()-1; i++) {
            stringKoncowy = stringKoncowy + listaMoja.get(i) + " and ";
        }
        stringKoncowy = stringKoncowy + listaMoja.get(listaMoja.size()-1);

        System.out.println("Ilosc warunkow: " + listaMoja.size());
        System.out.println(stringKoncowy);

        Query<Employee> query = currentSession.createQuery("from Employee " + "WHERE " + stringKoncowy, Employee.class);
        List<Employee> list = query.getResultList();

        System.out.println("Lista klientow do wczytania :" + list);
        System.out.println("Ilosc na liscie: " + list.size());

        return list;
    }

    @Override
    public String postEmployeeUpdate(Employee employeeUpdate) {

        //String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

        Session currentSession = entityManager.unwrap(Session.class);
        //Customer customer = new Customer();
        System.out.println(employeeUpdate.getFirstName());
        // customer.setId(customerUpdate.getId());
        Integer numerIdDoUpdate = employeeUpdate.getId(); //int na Integer bo tak mam w customer
        System.out.println(numerIdDoUpdate);

        Employee employee = currentSession.get(Employee.class, numerIdDoUpdate);

        employee.setFirstName(employeeUpdate.getFirstName());
        employee.setLastName(employeeUpdate.getLastName());
        employee.setStatus(employeeUpdate.getStatus());
        employee.setLogin(employeeUpdate.getLogin());
        employee.setPassword(employeeUpdate.getPassword());
        currentSession.beginTransaction();
        //pobranie encji i przypisanie do nowej encji
        //Employee employee = session.get(Employee.class, 9);
        // currentSession.update(currentSession.merge(customerUpdate)); //merge musi byc bo: java.lang
        currentSession.update(employee);
        // .IllegalArgumentException: Removing a detached instance
        currentSession.getTransaction().commit();
        //zamkniecie obiektu SessionFactory
        currentSession.close();

        return null;
    }

    @Override
    public List<Employee> postEmployeeDelete(Employee employeeDelete) {

        Session currentSession = entityManager.unwrap(Session.class);

        Employee employee = new Employee();
        System.out.println(employeeDelete.getId());
        employee.setId(employeeDelete.getId());
        currentSession.beginTransaction();
        //pobranie encji i przypisanie do nowej encji
        //Employee employee = session.get(Employee.class, 9);
        currentSession.delete(currentSession.merge(employeeDelete)); //merge musi byc bo: java.lang.IllegalArgumentException: Removing a detached instance
        currentSession.getTransaction().commit();
        //zamkniecie obiektu SessionFactory
        currentSession.close();

        return null;
    }
}
