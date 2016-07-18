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
 * This class represent a physical person.
 * <p>
 *     All the registered user are represented into the system as a Person. So all the childs of the class {@link Role}
 *     are also Person.
 * </p>
 *
 * @see Role
 * @see Administrator
 * @see Manager
 * @see Owner
 * @see Scout
 * @see Tenant
 * @see Address
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * The first name of the Person
     */
    private String firstName;

    /**
     * The last name of the Person
     */
    private String lastName;

    /**
     * The {@link Role}s that the Person has into the sysyem
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Role> roles;

    /**
     * The username of the Person
     */
    private String username;

    /**
     * The password of the Person
     */
    private String password;

    /**
     * The email of the Person
     */
    private String email;

    /**
     * The birth date of the Person
     */
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime birthDate;

    /**
     * The phone number of the Person
     */
    private String phoneNumber;

    /**
     * The physical address of the Person
     */
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    /**
     * The gender of the Person
     */
    private Gender gender;

    /**
     * //TODO
     */
    private String hash = "";

    /**
     * //TODO
     */
    private String reqStatus = "";

    /**
     * Default constructor
     */
    public Person() {
    }

    public Person(RegistrationBean registrationBean) {
        this.username = registrationBean.getUsername();
        this.firstName = registrationBean.getFirstName();
        this.lastName = registrationBean.getLastName();
        this.password = registrationBean.getPassword();
        this.email = registrationBean.getEmail();
        this.birthDate = registrationBean.getDateofbirth();
        this.phoneNumber = registrationBean.getPhonenumber();
        this.address = new Address(registrationBean.getNation(),registrationBean.getCity(),registrationBean.getAddress(),registrationBean.getPostalcode());
        this.gender = Gender.valueOf(registrationBean.getGender());
        this.hash = registrationBean.getHash();
        this.reqStatus = registrationBean.getReq_status();
        this.roles = new ArrayList<>();
    }

    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setReqStatus(String req_status) {
        this.reqStatus = req_status;
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

    public String getFullName() {
        return firstName + " " + lastName;
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

    public DateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(DateTime birthdate) {
        this.birthDate = birthdate;
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

    /**
     *
     * @param newRole
     * @throws Exception
     */
    public void addRole(Role newRole) throws Exception {
        for (Role role : roles) {
            if (role.getClass().getSimpleName().equals(newRole.getClass().getSimpleName())) {
                throw new Exception("This user has already this role");
            }
        }
        roles.add(newRole);
    }

    /**
     *
     * @param roleString
     * @throws Exception
     */
    public void removeRole(String roleString) throws Exception {
        Iterator iterator = roles.iterator();
        while (iterator.hasNext()){
            if (iterator.next().getClass().getSimpleName().equals(roleString)) {
                iterator.remove();
            }
        }
        throw new Exception("This user does not have this role");
    }

    /**
     *
     * @param roleString
     * @return
     * @throws Exception
     */
    public Role getRole(String roleString) throws Exception {
        for (Object role : roles) {
            if (role.getClass().getSimpleName().equals(roleString)) {
                return (Role) role;
            }
        }
        throw new Exception("This user does not have such authorization");
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getRoles() {
        ArrayList<String> list = new ArrayList<>();
        for (Role role: roles) {
            list.add(role.getClass().getSimpleName());
        }
        return list;
    }

}