package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.model.dao.UserDAO;

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
    public Person login(String username, String password) {

        try {

            return UserDAO.findBy(username, password);

        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }

    }
}

