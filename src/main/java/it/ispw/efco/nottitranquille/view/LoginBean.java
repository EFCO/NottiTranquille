package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.LoginController;
import it.ispw.efco.nottitranquille.model.Applicant;
import it.ispw.efco.nottitranquille.model.Manager;
import it.ispw.efco.nottitranquille.model.RegisteredUser;
import it.ispw.efco.nottitranquille.model.Tenant;

import javax.persistence.NoResultException;

public class LoginBean {
    private String username;
    private String password;

    private String role;

    private boolean isLogged = false;

    public LoginBean() {
        this.username = "";
        this.password = "";
    }

    public void setUsername(String user) {
        this.username = user;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String pwd) {
        this.password = pwd;
    }

    public String getPassword() {
        return this.password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean validate() {
        // Controllo sintattico
        if (this.username.equals("") || this.password.equals("")) {
            return false;
        }

        try {

            LoginController controller = LoginController.getInstance();
            RegisteredUser found = controller.login(this.username, this.password);


            if (found instanceof Manager)
                this.setRole("Manager");
            else if (found instanceof Tenant)
                this.setRole("Tenant");

            isLogged = true;

            return true;

        } catch (NoResultException e) {
            isLogged = false;
            return false;
        }
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }
}
