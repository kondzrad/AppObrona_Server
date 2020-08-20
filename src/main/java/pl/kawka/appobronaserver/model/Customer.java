package pl.kawka.appobronaserver.model;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    // private TableColumn<Customer, String> id, firstName,
    // lastName, town, street, postcode, telephoneNumber, nip, dateAdded;

// `id_customer` int not null auto_increment,
//    `first_name` varchar(40) default null,
//    `last_name` varchar(40) default null,
//    `town` varchar(40) default null,
//    `street` varchar(40) default null,
//    `postcode` varchar(40) default null,
//    `telephone_number` varchar(40) default null,
//    `nip` varchar(40) default null,
//    `date_added` DATETIME,

    @Column(name = "id_customer")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "town")
    private String town;
    @Column(name = "street")
    private String street;
    @Column(name = "postcode")
    private String postcode;
    @Column(name = "telephone_number")
    private String telephoneNumber;
    @Column(name = "nip")
    private String nip;
    @Column(name = "date_added")
    private String dateAdded;


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

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", town='" + town + '\'' +
                ", street='" + street + '\'' +
                ", postcode='" + postcode + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", nip='" + nip + '\'' +
                ", dateAdded='" + dateAdded + '\'' +
                '}';
    }
}
