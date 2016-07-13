package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.ManageStructures;
import it.ispw.efco.nottitranquille.model.Manager;
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

    private boolean owner;

    private String ownerFirstName;

    private String ownerLastName;

    private String ownerEmail;

    private boolean sameaddress;

    private String ownerAddress;

    private String ownerCity;

    private String ownerPostalcode;

    private String ownerNation;

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

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public boolean isSameaddress() {
        return sameaddress;
    }

    public void setSameaddress(boolean sameaddress) {
        this.sameaddress = sameaddress;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public String getOwnerCity() {
        return ownerCity;
    }

    public void setOwnerCity(String ownerCity) {
        this.ownerCity = ownerCity;
    }

    public String getOwnerPostalcode() {
        return ownerPostalcode;
    }

    public void setOwnerPostalcode(String ownerPostalcode) {
        this.ownerPostalcode = ownerPostalcode;
    }

    public String getOwnerNation() {
        return ownerNation;
    }

    public void setOwnerNation(String ownerNation) {
        this.ownerNation = ownerNation;
    }

    public void validate(Person manager) throws Exception {
        ManageStructures.addNewStructure(this, manager);
    }

    public List<Structure> getAllStructures(Manager manager) {
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
