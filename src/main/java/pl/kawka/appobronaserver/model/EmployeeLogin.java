package pl.kawka.appobronaserver.model;

import javax.persistence.*;

@Entity
@Table(name = "employee_login") //pisac malymi bo jak: daneLogowania to hibernate robi dane_logowania
public class EmployeeLogin {

    //pusty kontruktor tez do request
    public EmployeeLogin() {

    }

    //kontruktor do requestParameter
    public EmployeeLogin(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Column(name = "id_employee")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "status")
    private String status;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "EmployeeLogin{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", status='" + status + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
