package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.ManageStructures;
import it.ispw.efco.nottitranquille.model.Applicant;
import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.model.Service;
import it.ispw.efco.nottitranquille.model.Structure;
import it.ispw.efco.nottitranquille.model.enumeration.StructureType;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;

public class StructureBean {

    private String name;

    private List<String> photos;

    private String termsOfService;

    private String termsOfCancellation;

    private DateTime checkIn;

    private DateTime checkOut;

    private String address;

    private String city;

    private String postalcode;

    private String nation;

    private StructureType type = null;

    private List<Service> services = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public String getTermsOfService() {
        return termsOfService;
    }

    public void setTermsOfService(String termsOfService) {
        this.termsOfService = termsOfService;
    }

    public String getTermsOfCancellation() {
        return termsOfCancellation;
    }

    public void setTermsOfCancellation(String termsOfCancellation) {
        this.termsOfCancellation = termsOfCancellation;
    }

    public DateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy");
        this.checkIn = DateTime.parse(checkIn, dateTimeFormatter);
    }

    public DateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy");
        this.checkOut = DateTime.parse(checkOut, dateTimeFormatter);
    }

    public StructureType getType() {
        return type;
    }

    public void setType(StructureType type) {
        this.type = type;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

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

    public void validate(Person manager) throws Exception {
        ManageStructures.addNewStructure(this, manager);
    }

    public List<Structure> getAllStructures(Applicant manager) {
        return ManageStructures.getAll(manager);
    }

    @Override
    public String toString() {
        return "StructureBean{" +
                "name='" + name + '\'' +
                ", photos=" + photos +
                ", termsOfService='" + termsOfService + '\'' +
                ", termsOfCancellation='" + termsOfCancellation + '\'' +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", postalcode='" + postalcode + '\'' +
                ", nation='" + nation + '\'' +
                ", type=" + type +
                ", services=" + services +
                '}';
    }
}
