package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.AccessDAO;
import it.ispw.efco.nottitranquille.view.UserBean;

import java.util.List;

/**
 * Created by Federico on 02/05/2016.
 */
public class AccessController {

    public static boolean loginValidation(String username,String password) {
        AccessDAO accessDAO = new AccessDAO();
        List<UserBean> result = accessDAO.login(username,password);
        if (result.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean registration(UserBean userBean) {
        //TODO need to cypher password before saving it maybe changing the existing one inside the attribute of the bean
        //TODO email verification should be done too
        AccessDAO accessDAO = new AccessDAO();
        accessDAO.register(userBean);
        return true;
    }
}
