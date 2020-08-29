package pl.kawka.appobronaserver.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.kawka.appobronaserver.model.Customer;

import javax.persistence.EntityManager;

import java.text.SimpleDateFormat;
import java.util.*;

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
        System.out.println("WCZYTANIE CALEJ BAZY");
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

    @Override
    public List<Customer> postCustomerRead(Customer customerRead) {

        System.out.println("Wchodze do wczytania klientow");

        Session currentSession = entityManager.unwrap(Session.class);

        List<String> listaMoja= new ArrayList<>();
        if (customerRead.getId()!=0){
            listaMoja.add("id='"+customerRead.getId()+"'");
        }
        if (! customerRead.getFirstName().isEmpty()){
            listaMoja.add("firstName='"+customerRead.getFirstName()+"'");
        }
        if (! customerRead.getLastName().equals("")){
            listaMoja.add("lastName='"+customerRead.getLastName()+"'");
        }
        if (! customerRead.getTown().isEmpty()){
            listaMoja.add("town='"+customerRead.getTown()+"'");
        }
        if (! customerRead.getStreet().equals("")){
            listaMoja.add("street='"+customerRead.getStreet()+"'");
        }
        if (! customerRead.getPostcode().isEmpty()){
            listaMoja.add("postcode='"+customerRead.getPostcode()+"'");
        }
        if (! customerRead.getTelephoneNumber().equals("")){
            listaMoja.add("telephoneNumber='"+customerRead.getTelephoneNumber()+"'");
        }
        if (! customerRead.getNip().isEmpty()){
            listaMoja.add("nip='"+customerRead.getNip()+"'");
        }
        //dodac obsluge daty

        String stringKoncowy="";
        for (int i = 0; i < listaMoja.size()-1; i++) {
            stringKoncowy = stringKoncowy + listaMoja.get(i) + " and ";
        }
        stringKoncowy = stringKoncowy + listaMoja.get(listaMoja.size()-1);

        System.out.println("Ilosc warunkow: " + listaMoja.size());
        System.out.println(stringKoncowy);

        Query<Customer> query = currentSession.createQuery("from Customer " + "WHERE " + stringKoncowy, Customer.class);
        List<Customer> list = query.getResultList();

        System.out.println("Lista klientow do wczytania :" + list);
        System.out.println("Ilosc na liscie: " + list.size());

        return list;
    }

    @Override
    public List<Customer> postCustomerDelete(Customer customerDelete) {
        Session currentSession = entityManager.unwrap(Session.class);

        Customer customer = new Customer();
        System.out.println(customerDelete.getId());
        customer.setId(customerDelete.getId());
        currentSession.beginTransaction();
        //pobranie encji i przypisanie do nowej encji
        //Employee employee = session.get(Employee.class, 9);
        currentSession.delete(currentSession.merge(customerDelete)); //merge musi byc bo: java.lang.IllegalArgumentException: Removing a detached instance
        currentSession.getTransaction().commit();
        //zamkniecie obiektu SessionFactory
        currentSession.close();
        return null;
    }


    private String checkNull(String tekst){
        if (tekst.equals("")){
            return "";
        }
        return null;
    }

}
