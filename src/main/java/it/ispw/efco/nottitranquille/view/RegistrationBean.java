package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.AccessController;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONObject;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Federico on 02/05/2016.
 */
public class RegistrationBean {

    private String username = "";
    private String password = "";
    private String firstName = "Federico";
    private String lastName = "Vagnoni";
    private String email = "fede93.vagnoni@gmail.com";
    private String address = "Piazza Ciao";
    private String city = "Roma";


    private String postalcode = "Roma";
    private String nation = "Roma";
    private DateTime dateofbirth = null;
    private String phonenumber = "";
    private String gender;
    private String hash = "";
    private String req_status = "";

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getReq_status() {
        return req_status;
    }

    public void setReq_status(String req_status) {
        this.req_status = req_status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public DateTime getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy");
        this.dateofbirth = DateTime.parse(dateofbirth, dateTimeFormatter);
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

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    //-------------------------------------------------------------------//

    public void register() throws Exception {
        System.out.println("Ciao a tutti");
        if (!this.username.equals("") && !this.password.equals("")
                && !this.firstName.equals("") && !this.lastName.equals("")
                && !this.address.equals("") && this.dateofbirth != null
                && !this.email.equals("")) {
            if (AccessController.getRegisteredUserId(this.username,this.password) == null) {
                AccessController.registration(this);
            } else {
                throw new Exception("User already registered or waiting for verification");
            }
        } else {
            throw new Exception("Invalid data");
        }
    }

    public void verify() throws Exception {
        AccessController.registration(this);
    }

    public String api_register_response() throws Exception {
        JSONObject response = new JSONObject();
        try {
            this.register();
            response.put("code",1);
            response.put("message","registration_success");
            return response.toString();
        } catch (Exception e) {
            response.put("code",0);
            response.put("message",e.getMessage());
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }
}
