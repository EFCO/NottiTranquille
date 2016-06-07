package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.AccessDAO;
import it.ispw.efco.nottitranquille.view.UserBean;

import java.util.List;

/**
 * Created by Federico on 02/05/2016.
 */
public class AccessController {

    public static boolean registrationValidation(UserBean ub) {
        AccessDAO accessDAO = new AccessDAO();
        List<UserBean> result = accessDAO.isRegistered(ub.getUsername(),ub.getPassword());
        if (result.isEmpty()) {
            return false;
        } else {
            ub.setId(result.get(0).getId());
            return true;
        }
    }

    public static boolean loginSetter(Long id) {
        AccessDAO accessDAO = new AccessDAO();
        return accessDAO.login(id); //TODO da migliorare il controllo dell'errore
    }

    public static boolean logoutSetter(Long id) {
        AccessDAO accessDAO = new AccessDAO();
        accessDAO.logout(id); //TODO da migliorare il controllo dell'errore
        return true;
    }

        public static boolean registration(UserBean userBean) {
        //TODO need to cypher password before saving it maybe changing the existing one inside the attribute of the bean
        //TODO email verification should be done too
        AccessDAO accessDAO = new AccessDAO();
        accessDAO.register(userBean);
        return true;
    }
}
