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

    /**
     * 
     */
    private String username;

    private String firstName;


    @OneToMany(cascade = CascadeType.ALL)
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
        Iterator iterator = roles.iterator();
        while (iterator.hasNext()){
            Role role = (Role) iterator.next();
            if (role.getClass().getSimpleName().equals(rolestring)) {
                return role;
            }
        }
        throw new Exception("This user does not have such authorization");
    }

    public Person() {
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
}