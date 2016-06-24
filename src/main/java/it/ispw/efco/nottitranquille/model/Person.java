package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.enumeration.Gender;
import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SuppressWarnings("JpaDataSourceORMInspection")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    /**
     *
     */
    protected String firstName;

    /**
     *
     */
    protected String lastName;

    /**
     *
     */
    protected String email;

    /**
     *
     */
    protected DateTime birthdate;

    /**
     *
     */
    protected String phoneNumber;

    protected Address address;

    protected Gender gender;

    /**
     * Default constructor
     */
    public Person() {
    }

    /* Getter and Setter */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getCompleteName() {
        return firstName + " " + lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DateTime getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(DateTime birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}