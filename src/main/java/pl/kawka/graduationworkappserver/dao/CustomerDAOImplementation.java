package pl.kawka.graduationworkappserver.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.kawka.graduationworkappserver.model.Customer;

import javax.persistence.EntityManager;

import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class CustomerDAOImplementation implements CustomerDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Customer> restGetAllCustomerList() {

        Session currentSession = entityManager.unwrap(Session.class);
        Query<Customer> query = currentSession.createQuery("from Customer", Customer.class);
        List<Customer> list = query.getResultList();
        //System.out.println(list);

        return list;
    }

    @Override
    public String restPostCustomerCreate(Customer customerCreate) {

        //System.out.println(customerCreate);
        Session currentSession = entityManager.unwrap(Session.class);
        //duze H daje 20 wieczor, a nie 8pm
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

        //stworzenie obiektu klasy Customer
        Customer customer = new Customer();
        //customer.setId(50);  //z automatu mysql se to robi
        customer.setFirstName(customerCreate.getFirstName()); //pobranie imienia co przyszlo i wstawienie go do tabeli
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

        //insert into ... values ... nie dziala w HQL
        // tak sie nie da bo: In HQL, only the INSERT INTO … SELECT … is supported; there is no
        // INSERT INTO … VALUES. HQL only support insert from another table.
        //wiec mozna dodac wartosci ale tlyko z innej tabeli

        return null;
    }

    @Override
    public List<Customer> restPostCustomerRead(Customer customerRead) {

        //System.out.println("Wchodze do wczytania klientow");
        Session currentSession = entityManager.unwrap(Session.class);

        List<String> listCondition = new ArrayList<>();
        if (customerRead.getId() > 0) {
            listCondition.add("id='" + customerRead.getId() + "'");
        } else if(customerRead.getId() == 0){
        }else{
            listCondition.add("id='" + "-1" + "'");
        }
        if (!customerRead.getFirstName().isEmpty()) {
            listCondition.add("firstName='" + customerRead.getFirstName() + "'");
        }
        if (!customerRead.getLastName().equals("")) {
            listCondition.add("lastName='" + customerRead.getLastName() + "'");
        }
        if (!customerRead.getTown().isEmpty()) {
            listCondition.add("town='" + customerRead.getTown() + "'");
        }
        if (!customerRead.getStreet().equals("")) {
            listCondition.add("street='" + customerRead.getStreet() + "'");
        }
        if (!customerRead.getPostcode().isEmpty()) {
            listCondition.add("postcode='" + customerRead.getPostcode() + "'");
        }
        if (!customerRead.getTelephoneNumber().equals("")) {
            listCondition.add("telephoneNumber='" + customerRead.getTelephoneNumber() + "'");
        }
        if (!customerRead.getNip().isEmpty()) {
            listCondition.add("nip='" + customerRead.getNip() + "'");
        }
        if (!customerRead.getDateAdded().isEmpty()) {
            listCondition.add("dateAdded='" + customerRead.getDateAdded() + "'");
        }

        String endCondition = "";
        for (int i = 0; i < listCondition.size() - 1; i++) {
            endCondition = endCondition + listCondition.get(i) + " and ";
        }

        if (listCondition.size()>0) {
            endCondition = "WHERE " + endCondition + listCondition.get(listCondition.size() - 1);
        }

        System.out.println("Ilosc warunkow: " + listCondition.size());
        //System.out.println(endCondition);

        Query<Customer> query = currentSession.createQuery("from Customer " + endCondition, Customer.class);
        List<Customer> list = query.getResultList();

        //System.out.println("Lista klientow do wczytania :" + list);
        System.out.println("Ilosc na liscie: " + list.size());

        return list;
    }

    @Override
    public String restPutCustomerUpdate(Customer customerUpdate) {

        Session currentSession = entityManager.unwrap(Session.class);
        Integer numberIdToUpdate = customerUpdate.getId(); //int na Integer bo tak mam w Customer
        //System.out.println(numberIdToUpdate);

        Customer customer = currentSession.get(Customer.class, numberIdToUpdate);

        customer.setFirstName(customerUpdate.getFirstName());
        customer.setLastName(customerUpdate.getLastName());
        customer.setTown(customerUpdate.getTown());
        customer.setStreet(customerUpdate.getStreet());
        customer.setPostcode(customerUpdate.getPostcode());
        customer.setTelephoneNumber(customerUpdate.getTelephoneNumber());
        customer.setNip(customerUpdate.getNip());
        customer.setDateAdded(customerUpdate.getDateAdded());

        currentSession.beginTransaction();
        currentSession.update(customer);
        currentSession.getTransaction().commit();
        //zamkniecie obiektu SessionFactory
        currentSession.close();
        return null;
    }


    @Override
    public String restDeleteCustomerDelete(Customer customerDelete) {

        Session currentSession = entityManager.unwrap(Session.class);

        Customer customer = new Customer();
        customer.setId(customerDelete.getId());

        currentSession.beginTransaction();
        //merge musi byc bo: java.lang.IllegalArgumentException: Removing a detached instance
        currentSession.delete(currentSession.merge(customerDelete));
        currentSession.getTransaction().commit();
        //zamkniecie obiektu SessionFactory
        currentSession.close();

        return null;
    }

}
