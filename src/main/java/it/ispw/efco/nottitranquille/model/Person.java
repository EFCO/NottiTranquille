package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.enumeration.Gender;
import it.ispw.efco.nottitranquille.view.RegistrationBean;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
public class Person {

    /**
     *
     */
    private String username;


    private String firstName;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Role> roles;

    /**
     *
     */
    private String lastName;

    /**
     *

     */
    private String password;

    /**
     *
     */
    private String email;

    /**
     *
     */
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime birthdate;

    /**
     *
     */
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    private Gender gender;

    private String hash = "";

    private String req_status = "";

    @Id
    @GeneratedValue
    private Long id;

    public Person() {
    }

    /**
     * Default constructor
     */
    public Person(RegistrationBean registrationBean) {
        this.username = registrationBean.getUsername();
        this.firstName = registrationBean.getFirstName();
        this.lastName = registrationBean.getLastName();
        this.password = registrationBean.getPassword();
        this.email = registrationBean.getEmail();
        this.birthdate = registrationBean.getDateofbirth();
        this.phoneNumber = registrationBean.getPhonenumber();
        this.address = new Address(registrationBean.getNation(),registrationBean.getCity(),registrationBean.getAddress(),registrationBean.getPostalcode());
        this.gender = Gender.valueOf(registrationBean.getGender());
        this.hash = registrationBean.getHash();
        this.req_status = registrationBean.getReq_status();
        this.roles = new ArrayList<Role>();
    }

    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = new ArrayList<Role>();
    }

    public Long getId() {
        return id;
    }

    public void setReq_status(String req_status) {
        this.req_status = req_status;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void addRole(Role newRole) throws Exception {
        for (Role role : roles) {
            if (role.getClass().getSimpleName().equals(newRole.getClass().getSimpleName())) {
                throw new Exception("This user has already this role");
            }
        }
        roles.add(newRole);
    }

    public void removeRole(String rolestring) throws Exception {
        Iterator iterator = roles.iterator();
        while (iterator.hasNext()){
            if (iterator.next().getClass().getSimpleName().equals(rolestring)) {
                iterator.remove();
            }
        }
        throw new Exception("This user does not have this role");
    }

    public Role getRole(String rolestring) throws Exception {
        for (Object role : roles) {
            if (role.getClass().getSimpleName().equals(rolestring)) {
                return (Role) role;
            }
        }
        throw new Exception("This user does not have such authorization");
    }

    public ArrayList<String> getRoles() {
        ArrayList<String> list = new ArrayList<String>();
        for (Role role: roles) {
            list.add(role.getClass().getSimpleName());
        }
        return list;
    }

    public Role getFirstRole() throws Exception {
        if (roles.size() == 0) {
            throw new Exception("This user has no Role");
        }
        return roles.get(0);
    }
}