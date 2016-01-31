package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.Manager;
import it.ispw.efco.nottitranquille.model.RegisteredUser;
import it.ispw.efco.nottitranquille.model.Tenant;
import it.ispw.efco.nottitranquille.model.dao.ManagerDAO;
import it.ispw.efco.nottitranquille.model.dao.TenantDao;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

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

        try {
            Tenant tenant = TenantDao.findByNameAndPassword(username, password);
            return tenant;
        }catch (NoResultException e1){

            try{
                Manager manager = ManagerDAO.findByNameAndPassword(username,password);
                return manager;
            }catch (NoResultException e2){
                throw new NoResultException();
            }

        }
    }
}
