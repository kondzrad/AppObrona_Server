package pl.kawka.AppObronaServerTest5.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.kawka.AppObronaServerTest5.model.PracownikSQL;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PracownikDAOImplementacja implements PracownikDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<PracownikSQL> getPracownikSQL() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<PracownikSQL> query = currentSession.createQuery("from PracownikSQL",PracownikSQL.class);
        List<PracownikSQL> list = query.getResultList();
        System.out.println("DAO_pezdan1");
        System.out.println(list);
        System.out.println("DAO_pezdan2");

       /* JSONObject json = new JSONObject();
        json.put("login", login.getText());*/

        return list;
    }

    @Override
    public String getLogowanie(PracownikSQL pracownikSQLaaa2) {

        System.out.println("DAO_nic1");
        System.out.println(pracownikSQLaaa2);
        String login1 = pracownikSQLaaa2.getLogin();
        System.out.println(login1); //zczytanie loginu z JSONA co przyszedl
        String password1 = pracownikSQLaaa2.getPassword();
        System.out.println(password1); //zczytanie hasla z JSONA co przyszedl

        Session currentSession = entityManager.unwrap(Session.class);
        Query<PracownikSQL> query = currentSession.createQuery("from PracownikSQL " +
                        "WHERE login='"+login1+"' and password='"+password1+"' ",
                PracownikSQL.class);
        List<PracownikSQL> list = query.getResultList();
        System.out.println("Logowanie lista :" + list);

        System.out.println("Status pracownika: " + list.toString().contains("status='pracownik'"));
        System.out.println("Status admina: " + list.toString().contains("status='admin'"));

        System.out.println("DAO_nic2");
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
