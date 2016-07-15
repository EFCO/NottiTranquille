package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.DAO.AccessDAO;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Federico on 09/06/2016.
 */
public class SessionListener implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
//       httpSessionEvent.getSession().setMaxInactiveInterval(10);
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        String cookie = httpSessionEvent.getSession().getId();
        AccessDAO accessDAO = new AccessDAO();
        accessDAO.setExpired(cookie);
    }
}
