package pl.kawka.appobronaserver.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.kawka.appobronaserver.model.Customer;
import pl.kawka.appobronaserver.model.EmployeeLogin;

import javax.persistence.EntityManager;
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

       /* JSONObject json = new JSONObject();
        json.put("login", login.getText());*/

        return list;
    }
}
