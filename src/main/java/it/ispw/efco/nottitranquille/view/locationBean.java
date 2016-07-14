package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.ManageLocation;
import it.ispw.efco.nottitranquille.model.Structure;

import java.util.List;

/**
 * Created by Federico on 13/07/2016.
 */
public class LocationBean {

    /**
     *
     */
    private String description;

    /**
     *
     */
    private String numberOfRooms;

    /**
     *
     */
    private String numberOfBathrooms;

    /**
     *
     */
    private String maxGuestsNumber;

    /**
     *
     */
    private String numberOfBeds;

    /**
     *
     */
    private List<String> photos;

    /**
     *
     */
    private String numberOfBedrooms;


    private Structure currentStructure;


//    private Prices prices;

    private String type;

//    private List<Service> services;

//    private Structure structure;


    public LocationBean() {

    }

    public void validate() {
        this.toString();
        ManageLocation.addNewLocation(this, this.currentStructure);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(String numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public String getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(String numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public String getMaxGuestsNumber() {
        return maxGuestsNumber;
    }

    public void setMaxGuestsNumber(String maxGuestsNumber) {
        this.maxGuestsNumber = maxGuestsNumber;
    }

    public String getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(String numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public String getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(String numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Structure getCurrentStructure() {
        return currentStructure;
    }

    public void setCurrentStructure(Structure currentStructure) {
        this.currentStructure = currentStructure;
    }

    @Override
    public String toString() {
        return "LocationBean{" +
                "description='" + description + '\'' +
                ", numberOfRooms='" + numberOfRooms + '\'' +
                ", numberOfBathrooms='" + numberOfBathrooms + '\'' +
                ", maxGuestsNumber='" + maxGuestsNumber + '\'' +
                ", numberOfBeds='" + numberOfBeds + '\'' +
                ", photos=" + photos +
                ", numberOfBedrooms='" + numberOfBedrooms + '\'' +
                ", currentStructure=" + currentStructure +
                ", type='" + type + '\'' +
                '}';
    }
}