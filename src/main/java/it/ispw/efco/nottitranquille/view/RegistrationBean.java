package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.AccessController;
import org.json.JSONObject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * Created by Federico on 02/05/2016.
 */
@Entity(name = "registeredusersdata")
public class RegistrationBean {

    private String username = "";
    private String password = "";
    private String name = "Federico Vagnoni";
    private String email = "fede93.vagnoni@gmail.com";
    private String address = "Piazza Ciao";
    private String dateofbirth = "duemilamai";

//    @Transient
//    private boolean api = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    //-------------------------------------------------------------------//

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

    //-------------------------------------------------------------------//

    public boolean register() {
        if (!this.username.equals("") && !this.password.equals("") && !this.name.equals("") && !this.address.equals("") && !this.dateofbirth.equals("") && !this.email.equals("")) {
            if (AccessController.getRegisteredUserId(this.username,this.password) == null) {
                return AccessController.registration(this);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public String api_register_response() {
        JSONObject response = new JSONObject();
        if (this.register()) {
            response.put("code",1);
            response.put("message","registration_success");
            return response.toString();
        } else {
            response.put("code",0);
            response.put("message","registration_failure");
            return response.toString();
        }
    }

    //-------------------------------------------------------------------//
    private Long id;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
