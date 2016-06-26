package it.ispw.efco.nottitranquille.model;

import com.sun.javafx.beans.annotations.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
public abstract class RegisteredUser extends Person implements Applicant {

    @NonNull
    @Column(unique = true)
    protected String username;

    protected String password;

    public RegisteredUser() {
        super();
    }

    public RegisteredUser(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /* Getter and Setter */

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void update(RegisteredUser toUpdate) {
        this.setId(toUpdate.getId());
        this.setFirstName(toUpdate.getFirstName());
        this.setLastName(toUpdate.getLastName());
        this.setUsername(toUpdate.getUsername());
        this.setPassword(toUpdate.getPassword());
    }
}
