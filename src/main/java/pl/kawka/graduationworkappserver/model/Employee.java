package pl.kawka.graduationworkappserver.model;

import javax.persistence.*;

@Entity
@Table(name = "employees") //pisac malymi bo jak: daneLogowania to hibernate robi dane_logowania
public class Employee {

    //pusty kontruktor tez do request
    public Employee() {
    }

    //pelny kontruktor
    public Employee(Integer id, String firstName, String lastName, String status, String login, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.login = login;
        this.password = password;
    }

    //kontruktor do requestParameter do logowania
    public Employee(String login, String password) {
        this.login = login;
        this.password = password;
    }

    //kontruktor do tworzenia pracownika bez id
    public Employee(String firstName, String lastName, String status, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.login = login;
        this.password = password;
    }

    public Employee(Integer id) { //konstruktor do usuwania
        this.id = id;
    }

    @Column(name = "id_employee")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", status='" + status + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
