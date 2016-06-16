package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.Manager;
import it.ispw.efco.nottitranquille.model.RegisteredUser;
import it.ispw.efco.nottitranquille.model.Tenant;
import it.ispw.efco.nottitranquille.model.dao.RegisteredUserDAO;

import javax.persistence.NoResultException;

public class LoginController {

    private static LoginController instance;

    public static LoginController getInstance() {
        if (instance == null)
            instance = new LoginController();
        return instance;
    }

    private LoginController() {
    }

    /**
     * Carica l'utente corrispondente alla coppia username/password in input
     *
     * @param username username
     * @param password password
     * @return l'utente loggato oppure null se nessun utente corrisponde alla coppia username/password
     */
    public RegisteredUser login(String username, String password) {
        RegisteredUser user;
        try {
            user = (RegisteredUser) RegisteredUserDAO.findByNameAndPassword(username, password, Tenant.class);
        } catch (NoResultException e) {

            try {
                user = (RegisteredUser) RegisteredUserDAO.findByNameAndPassword(username, password, Manager.class);
            } catch (NoResultException e2) {
                return null;
            }

        }

        return user;
    }

}

