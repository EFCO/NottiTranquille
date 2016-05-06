package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.AccessController;
import it.ispw.efco.nottitranquille.model.AccessDAO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * Created by Federico on 02/05/2016.
 */
@Entity(name = "usersdata")
public class UserBean {

    private String username = "";
    private String password = "";
    private String name = "Federico Vagnoni";
    private String address = "Piazza Ciao";
    private String dateofbirth = "duemilamai";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }


    @Transient
    private boolean logged = false;

    //-------------------------------------------------------------------//

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    //-------------------------------------------------------------------//
    public boolean validate() {
        if (!this.username.equals("") && !this.password.equals("")) {
//            AccessDAO accessDAO = new AccessDAO();
//            accessDAO.register(this);
            if(AccessController.loginValidation(this.username,this.password)) {
                this.logged = true;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean register() {
        if (!this.username.equals("") && !this.password.equals("") && !this.name.equals("") && !this.address.equals("") && !this.dateofbirth.equals("")) {
            return AccessController.registration(this);
        } else {
            return false;
        }
    }

    public void logout() {
        this.logged = false;
    }

    //-------------------------------------------------------------------//
    private Long id;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
