package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.model.Service;
import it.ispw.efco.nottitranquille.model.enumeration.StructureType;
import org.joda.time.DateTime;

import java.util.List;

public class StructureBean {

    private String name;

    private String numberOfLocations;

    private List<String> photos;

    private String termsOfService;

    private String termsOfCancellation;

    private DateTime checkIn;

    private DateTime checkOut;

    private String address;

    private String city;

    private String postalcode;

    private String nation;

    private StructureType type;

    private List<Service> services;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberOfLocations() {
        return numberOfLocations;
    }

    public void setNumberOfLocations(String numberOfLocations) {
        this.numberOfLocations = numberOfLocations;
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

    public void setCheckIn(DateTime checkIn) {
        this.checkIn = checkIn;
    }

    public DateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(DateTime checkOut) {
        this.checkOut = checkOut;
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

    public void createNewStructure() {

    }
}
