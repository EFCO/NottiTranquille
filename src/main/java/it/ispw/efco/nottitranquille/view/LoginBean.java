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
    private String nome;
    private String cognome;

    private String role;

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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setCognome(String cogn) {
        this.cognome = cogn;
    }

    public String getCognome() {
        return this.cognome;
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


            if (found instanceof Applicant)
                this.setRole("Manager");
            else if (found instanceof Tenant)
                this.setRole("Tenant");


            return (found != null);


        } catch (NoResultException e) {
            return false;
        }
    }
}
