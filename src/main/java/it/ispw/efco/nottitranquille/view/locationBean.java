package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.ManageLocation;
import it.ispw.efco.nottitranquille.controller.ManageStructures;
import it.ispw.efco.nottitranquille.model.Location;
import it.ispw.efco.nottitranquille.model.Structure;
import org.joda.time.Interval;

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

    private Location currentLocation;

    private List<Interval> intervalList;

//    private List<Service> services;

//    private Structure structure;


    public LocationBean() {

    }

    public void validate() {
        ManageLocation.addNewLocation(this, this.currentStructure);
    }

    public int modifyField(String field, String value, Long id) {
        if (field != null && value != null) {
            try {
                ManageLocation.modifyField(field, value, id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 1;
        }
        return 0;
    }

    public void delete(String id, List<Location> locations) {
        ManageLocation.deleteLocation(Long.valueOf(id), locations, currentStructure);
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
        return ManageStructures.getStructuredWithID(currentStructure.getId());
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

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Location getCurrentLocation() {
        return ManageLocation.getLocationByID(currentLocation.getId());
    }

    public List<Interval> getIntervalList() {
        return intervalList;
    }

    public void setIntervalList(List<Interval> intervalList) {
        this.intervalList = intervalList;
    }
}
