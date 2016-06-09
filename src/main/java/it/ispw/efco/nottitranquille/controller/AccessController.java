package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.AccessDAO;
import it.ispw.efco.nottitranquille.view.LoginBean;
import it.ispw.efco.nottitranquille.view.RegistrationBean;
import sun.rmi.runtime.Log;

import java.util.List;

/**
 * Created by Federico on 02/05/2016.
 */
public class AccessController {

    public static long getRegisteredUserId(String username, String password) {
        AccessDAO accessDAO = new AccessDAO();
        List<RegistrationBean> result = accessDAO.isRegistered(username, password);
        if (result.isEmpty()) {
            return -1;
        } else {
            return result.get(0).getId();
        }
    }

    public static void login(LoginBean lb) {
        AccessDAO accessDAO = new AccessDAO();
        accessDAO.saveLogin(lb);
    }

    public static boolean isAlreadyLogged(String username, String password) {
        AccessDAO accessDAO = new AccessDAO();
        LoginBean lbr = accessDAO.getLoggedUser(username,password);                       //TODO da migliorare il controllo dell'errore
        if (lbr != null) {
            return true;
        } else {
            //if the user is not logged simply return false
            return false;
        }
    }

    public static boolean setLogout(Long id) {
        AccessDAO accessDAO = new AccessDAO();
        return accessDAO.removeLoggedUser(id); //TODO da migliorare il controllo dell'errore
    }

        public static boolean registration(RegistrationBean registrationBean) {
        //TODO need to cypher password before saving it maybe changing the existing one inside the attribute of the bean
        //TODO email verification should be done too
        AccessDAO accessDAO = new AccessDAO();
        accessDAO.register(registrationBean);
        return true;
    }
}
