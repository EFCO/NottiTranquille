package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.RegisteredUser;
import it.ispw.efco.nottitranquille.model.dao.ManagerDAO;
import it.ispw.efco.nottitranquille.model.dao.TenantDAO;

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
            user = TenantDAO.findByNameAndPassword(username, password);
        } catch (NoResultException e) {

            try {
                user = ManagerDAO.findByNameAndPassword(username, password);
            } catch (NoResultException e2) {
                return null;
            }

        }

        return user;
    }

}

