package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.enumeration.Gender;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.persistence.Entity;
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
    public Person() {
        this(null, null);
    }

    public Person(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.roles = new ArrayList<Role>();
    }

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String firstname;

    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<Role> roles;

    private String lastname;

    private String password;

    private String email;

    private DateTime birthdate;

    private String phoneNumber;

    private Gender gender;

    private String hash = "";

    private String req_status = "";

    public boolean hasAuthorization(String rolestring) {

        for (Role role : roles) {
            if (role.getClass().getSimpleName().equals(rolestring))
                return true;

        }

        return false;
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
        while (iterator.hasNext()) {
            if (iterator.next().getClass().getSimpleName().equals(rolestring)) {
                iterator.remove();
            }
        }
        throw new Exception("This user does not have this role");
    }

    public Role getRole(String rolestring) throws Exception {

        Iterator iterator = roles.iterator();
        while (iterator.hasNext()) {

            Role role = (Role) iterator.next();
            if (role.getClass().getSimpleName().equals(rolestring)) {
                return role;
            }

        }

        throw new Exception("This user does not have such authorization");
    }

    /**
     * Called by {@link it.ispw.efco.nottitranquille.model.dao.UserDAO} to update entity
     *
     * @param toUpdate
     */
    public void update(Person toUpdate) {
        this.roles = toUpdate.getRoles();
        this.id = toUpdate.getId();
        this.firstname = toUpdate.getfirstname();
        this.lastname = toUpdate.getlastname();
        this.username = toUpdate.getUsername();
        this.password = toUpdate.getPassword();
        this.email = toUpdate.getEmail();
        this.gender = toUpdate.getGender();
        this.phoneNumber = toUpdate.getPhoneNumber();
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getfirstname() {
        return firstname;
    }

    public void setfirstname(String firstname) {
        this.firstname = firstname;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getlastname() {
        return lastname;
    }

    public void setlastname(String lastname) {
        this.lastname = lastname;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getReq_status() {
        return req_status;
    }

    public void setId(Long id) {
        this.id = id;
    }
}