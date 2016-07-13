package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.LoginController;
import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.model.Role;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class LoginBean {
    private String username;
    private String password;

    private List<String> roles = new ArrayList<String>();

    private boolean isLogged = false;


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

    public boolean validate() {
        // Controllo sintattico
        if (this.username.equals("") || this.password.equals("")) {
            return false;
        }

        try {

            LoginController controller = LoginController.getInstance();
            Person found = controller.login(this.username, this.password);

            try {

                // flush previous login
                this.roles = new ArrayList<String>();

                if (found.hasAuthorization("Tenant")) {
                    roles.add("Tenant");
                }

                if (found.hasAuthorization("Manager")) {
                    roles.add("Manager");
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }

            isLogged = true;
            return true;

        } catch (NoResultException e) {
            isLogged = false;
            return false;
        }
    }

    public void logout() {
        isLogged = false;
    }

    public boolean is(String roleString) {
        for (String role : roles) {
            if (role.equals(roleString))
                return true;
        }

        return false;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public boolean getLogged() {
        return isLogged;
    }
}
