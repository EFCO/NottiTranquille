package it.ispw.efco.nottitranquille.model;

import javax.persistence.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
public class RegisteredUser extends Person {

    private String userName;
    private String password;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /* Getter and Setter */

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
