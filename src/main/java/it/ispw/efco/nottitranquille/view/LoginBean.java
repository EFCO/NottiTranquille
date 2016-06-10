package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.AccessController;
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


    public int login() {
        if (!this.username.equals("") && !this.password.equals("")) {
            Long id = AccessController.getRegisteredUserId(this.username, this.password);
            if (id == -1) {
                return 0;
            } else {
                int value = LoggedIn();
                if (value != 2) {
                    //you can not perform login if you are already logged
                    return value;
                } else {
//                    this.user_id = id;
                    AccessController.logNewUser(this);
                    return value;
                }

            }
        } else {
            return 0;
        }
    }

    public int LoggedIn() {
        if (!this.username.equals("") && !this.password.equals("") && !this.cookie.equals("")) {
            return AccessController.isAlreadyLogged(this,cookie);
        } else {
            return 2;
        }
    }

    public void logout() {
        if (this.id != null) {
            AccessController.setLogout(this.id);
        }
    }

    public String api_login_response() {
        JSONObject response = new JSONObject();
        if (this.login() == 2) {
            response.put("code",1);
            response.put("message","user_logged");
            return response.toString();
        } else {
            response.put("code",0);
            response.put("message","user_not_found");
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

//    public Long getUser_id() {
//        return user_id;
//    }
//
//    public void setUser_id(Long user_id) {
//        this.user_id = user_id;
//    }

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

