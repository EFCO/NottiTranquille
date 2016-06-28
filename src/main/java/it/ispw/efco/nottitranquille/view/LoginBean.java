package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.AccessController;
import it.ispw.efco.nottitranquille.model.Person;
import org.json.JSONObject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * Created by Federico on 07/06/2016.
 */

@Entity(name = "loggedusers")
public class LoginBean {
    private String username = "";
    private String password = "";
    private String cookie = "";
//    private Long user_id;


    public boolean isExpired() {
        return expired;
    }

    @Transient
    private boolean expired;


    public void login() throws Exception {
        if (!this.username.equals("") && !this.password.equals("")) {
            Person person= AccessController.getRegisteredUser(this.username, this.password);
            if (person == null) {
                throw new Exception("User not registered");
            } else {
                this.username = person.getUsername();
                int value = LoggedIn();
                if (value != 2) {
                    //you can not perform login if you are already logged
                    throw new Exception("User is already logged");
                } else {
//                    this.user_id = id;
                    AccessController.logNewUser(this);
                }

            }
        } else {
            throw new Exception("Invalid data");
        }
    }

    public int LoggedIn() {
        if (!this.username.equals("") && !this.password.equals("") && !this.cookie.equals("")) {
            return AccessController.isAlreadyLogged(this,cookie);
        } else {
            return 2;
        }
    }

    public void logout() throws Exception {
        if (this.id != null) {
            AccessController.setLogout(this.id);
        }
    }

    public String api_login_response() {
        JSONObject response = new JSONObject();
        try {
            this.login();
            response.put("code",1);
            response.put("message","user_logged");
            return response.toString();
        } catch (Exception e) {
            response.put("code",0);
            response.put("message",e.getMessage());
            return response.toString();
        }
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    private Long id;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", cookie='" + cookie + '\'' +
//                ", user_id=" + user_id +
                ", expired=" + expired +
                ", id=" + id +
                '}';
    }
}

