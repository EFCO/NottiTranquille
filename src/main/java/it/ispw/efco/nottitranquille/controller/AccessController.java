package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.AccessDAO;
import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.model.Tenant;
import it.ispw.efco.nottitranquille.model.mail.Mailer;
import it.ispw.efco.nottitranquille.view.LoginBean;
import it.ispw.efco.nottitranquille.view.RegistrationBean;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by Federico on 02/05/2016.
 */
public class AccessController {

    public static Person getRegisteredUserId(String username, String password) {
        AccessDAO accessDAO = new AccessDAO();
        List<Person> result = accessDAO.isRegistered(username, password);
        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }
    }

    public static void logNewUser(LoginBean lb) {
        AccessDAO accessDAO = new AccessDAO();
        accessDAO.saveLogin(lb);
    }



    public static int isAlreadyLogged(LoginBean loginBean, String cookie) {
        AccessDAO accessDAO = new AccessDAO();
        LoginBean lb = accessDAO.getLoggedUser(loginBean.getUsername(),loginBean.getPassword());                       //TODO da migliorare il controllo dell'errore
        if (lb != null) {
            loginBean.setId(lb.getId());
            if (!lb.getCookie().equals(cookie) && lb.isExpired()) {
                accessDAO.updateLogin(lb.getId(),cookie);
                return 1; //it's always you but your cookie was expired ---> ACCESS GRANTED
            } else if (!lb.isExpired() && !lb.getCookie().equals(cookie)) {
                return 0; //someone is trying to log from another device --> ACCESS DENIED
            } else {
                return 1; //It's me again so --> ACCESS GRANTED;
            }
        } else {
            //if the user is not logged simply return false
            return 2; //You are not logged so I will log you or simply I'll not show your name
        }
    }

    public static boolean setLogout(Long id) throws Exception {
        AccessDAO accessDAO = new AccessDAO();
        return accessDAO.removeLoggedUser(id); //TODO da migliorare il controllo dell'errore
    }

    public static void registration(RegistrationBean registrationBean) throws Exception {
        //TODO need to cypher password before saving it maybe changing the existing one inside the attribute of the bean
        AccessDAO accessDAO = new AccessDAO();
        if (registrationBean.getHash().equals("")) {
            Mailer mailer = new Mailer();
            String code = null;
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(registrationBean.toString().getBytes("UTF-8"));
                StringBuffer hexString = new StringBuffer();

                //Binary to Hexadecimal
                for (int i = 0; i < hash.length; i++) {
                    String hex = Integer.toHexString(0xff & hash[i]);
                    if (hex.length() == 1) hexString.append('0');
                    hexString.append(hex);
                }

                code = hexString.toString();

            } catch (NoSuchAlgorithmException nsae) {
                nsae.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String link = "localhost:8080/access.jsp?verify=verify&hash=" + code;
            mailer.send(registrationBean.getEmail(), "Welcome to Notti Tranquille", "In order to verify your account click on the following link\n<a href=\"" + link + "\"></a>", null);
            registrationBean.setHash(code);
            registrationBean.setReq_status("pending");
            Person person = new Person(registrationBean);
            person.addRole(new Tenant());
            accessDAO.register(person);
        } else {
            Long id = accessDAO.verifyPendingStatus(registrationBean.getHash());
            accessDAO.verify(id);
        }
    }
}