package pl.kawka.appobronaserver.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.kawka.appobronaserver.model.Customer;
import pl.kawka.appobronaserver.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Repository
public class CustomerDAOImplementation implements CustomerDAO{


   /* @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getAllCustomerList() {
        Session session = sessionFactory.openSession();
        List<Customer> employeeList = (List<Customer>) session.createQuery("from Customer").list();
        session.close();

        return employeeList;
    }*/
/////////////////////////
    @Autowired
    private EntityManager entityManager;



    @Override
    public List<Customer> getAllCustomerList() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Customer> query = currentSession.createQuery("from Customer", Customer.class);
        List<Customer> list = query.getResultList();
        System.out.println(list);


        /*list.replaceAll(s -> s == null ? "x" : s);
        Collections.replaceAll(list, null, "k");
        System.out.println(list);*/
       /* JSONObject json = new JSONObject();
        json.put("login", login.getText());*/

        return list;
    }


    @Override
    public String postCustomerCreate(Customer customerCreate) {

        System.out.println(customerCreate);

        Session currentSession = entityManager.unwrap(Session.class);

        //insert into ... values ... nie dziala w HQL
        // tak sie nie da bo: In HQL, only the INSERT INTO … SELECT … is supported; there is no INSERT INTO … VALUES. HQL only support insert from another table.
        //wiec mozna dodac wartosci ale tlyko z innej tabeli


        String timeStamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());

       // if (customerCreate.getFirstName().equals("")){}else{}

        //stworzenie obiektu klasy Customer
        Customer customer = new Customer();
        //customer.setId(50);  //nie da sie, tylko z automatu mysql se to robi
        customer.setFirstName(customerCreate.getFirstName()); //pobranie imienia co przyszlo i wstawienie go do
        // tabeli
        customer.setLastName(customerCreate.getLastName());
        customer.setTown(customerCreate.getTown());
        customer.setStreet(customerCreate.getStreet());
        customer.setPostcode(customerCreate.getPostcode());
        customer.setTelephoneNumber(customerCreate.getTelephoneNumber());
        customer.setNip(customerCreate.getNip());
        customer.setDateAdded(timeStamp);
        // rozpoczecie transakcji
        currentSession.beginTransaction();
        //zapisanie klienta
        currentSession.save(customer);
        //zakonczenie transakcji
        currentSession.getTransaction().commit();
        //zamkniecie obiektu Session
        currentSession.close();


        return null;
    }

}
