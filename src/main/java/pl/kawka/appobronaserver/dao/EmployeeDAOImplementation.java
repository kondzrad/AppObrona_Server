package pl.kawka.appobronaserver.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.kawka.appobronaserver.model.Employee;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDAOImplementation implements EmployeeDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Employee> restGetAllEmployees() {

        Session currentSession = entityManager.unwrap(Session.class);
        Query<Employee> query = currentSession.createQuery("from Employee", Employee.class);
        List<Employee> list = query.getResultList();
        //System.out.println(list);

        return list;
    }

    @Override
    public String restPostLogin(Employee employeeLogowanie) {

        //System.out.println(employeeLogowanie);
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Employee> query = currentSession.createQuery("from Employee " +
                "WHERE login= :login and password= :password ", Employee.class)
                .setParameter("login", employeeLogowanie.getLogin())
                .setParameter("password", employeeLogowanie.getPassword());

        List<Employee> list = query.getResultList();
        //System.out.println("Logowanie lista :" + list);
        //System.out.println("Status pracownika: " + list.toString().contains("status='pracownik'"));
        //System.out.println("Status admina: " + list.toString().contains("status='admin'"));

        if (list.isEmpty()) {
            System.out.println("Nieudane logowanie!");
            return "zle";
        } else if (list.toString().contains("status='Pracownik'")) {
            System.out.println("Zalogowano pracownika");
            return "OKpracownik";
        } else if (list.toString().contains("status='Admin'")) {
            System.out.println("Zalogowano admina");
            return "OKadmin";
        } else {
            System.out.println("Nieudane logowanie");
            return "zle2";
        }

    }

    @Override
    public String restPostEmployeeCreate(Employee employeeCreate) {

        //System.out.println(employeeCreate);
        Session currentSession = entityManager.unwrap(Session.class);

        Employee employee = new Employee();
        //customer.setId(50);  //z automatu mysql se to robi
        employee.setFirstName(employeeCreate.getFirstName()); //pobranie imienia i wstawienie do tabeli
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
    public List<Employee> restPostEmployeeRead(Employee employeeRead) {

        //System.out.println("Wchodze do wczytania klientow");
        Session currentSession = entityManager.unwrap(Session.class);

        List<String> listCondition = new ArrayList<>();
        if (employeeRead.getId() > 0) {
            listCondition.add("id='" + employeeRead.getId() + "'");
        } else if(employeeRead.getId() == 0){
        }else {
            listCondition.add("id='" + "-1" + "'");
        }
        if (!employeeRead.getFirstName().isEmpty()) {
            listCondition.add("firstName='" + employeeRead.getFirstName() + "'");
        }
        if (!employeeRead.getLastName().equals("")) {
            listCondition.add("lastName='" + employeeRead.getLastName() + "'");
        }
        if (!employeeRead.getStatus().isEmpty()) {
            listCondition.add("status='" + employeeRead.getStatus() + "'");
        }
        if (!employeeRead.getLogin().equals("")) {
            listCondition.add("login='" + employeeRead.getLogin() + "'");
        }
        if (!employeeRead.getPassword().isEmpty()) {
            listCondition.add("password='" + employeeRead.getPassword() + "'");
        }

        String endCondition = "";
        for (int i = 0; i < listCondition.size() - 1; i++) {
            endCondition = endCondition + listCondition.get(i) + " and ";
        }

        if (listCondition.size()>0){
            endCondition = "WHERE " + endCondition + listCondition.get(listCondition.size() - 1);
        }

        System.out.println("Ilosc warunkow: " + listCondition.size());
        //System.out.println(endCondition);

        Query<Employee> query = currentSession.createQuery("from Employee " + endCondition, Employee.class);
        List<Employee> list = query.getResultList();

        //System.out.println("Lista pracownikow do wczytania :" + list);
        System.out.println("Ilość znalezionych pracownikow: " + list.size());

        return list;
    }

    @Override
    public String restPutEmployeeUpdate(Employee employeeUpdate) {

        Session currentSession = entityManager.unwrap(Session.class);
        Integer numerIdDoUpdate = employeeUpdate.getId(); //int na Integer bo tak mam w Employee
        //System.out.println(numerIdDoUpdate);

        Employee employee = currentSession.get(Employee.class, numerIdDoUpdate);

        employee.setFirstName(employeeUpdate.getFirstName());
        employee.setLastName(employeeUpdate.getLastName());
        employee.setStatus(employeeUpdate.getStatus());
        employee.setLogin(employeeUpdate.getLogin());
        employee.setPassword(employeeUpdate.getPassword());

        currentSession.beginTransaction();
        currentSession.update(employee);
        currentSession.getTransaction().commit();
        //zamkniecie obiektu SessionFactory
        currentSession.close();

        return null;
    }

    @Override
    public String restDeleteEmployeeDelete(Employee employeeDelete) {

        Session currentSession = entityManager.unwrap(Session.class);

        Employee employee = new Employee();
        employee.setId(employeeDelete.getId());

        currentSession.beginTransaction();
        //merge musi byc bo: java.lang.IllegalArgumentException: Removing a detached instance
        currentSession.delete(currentSession.merge(employeeDelete));
        currentSession.getTransaction().commit();
        //zamkniecie obiektu SessionFactory
        currentSession.close();

        return null;
    }
}
