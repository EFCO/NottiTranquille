package it.ispw.efco.nottitranquille.model;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import javax.persistence.*;
import java.sql.Struct;
import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
@SuppressWarnings("JpaDataSourceORMInspection")
public class Structure {

    /**
     *
     */
    private String name;


    /**
     *
     */
    private String description;

    /**
     *
     */
    private String numberOfLocations;

    @OneToMany
    @JoinTable(name = "Structure_Location",
            joinColumns = {@JoinColumn(name = "StructureId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "LocationId", referencedColumnName = "id")})
    private List<Location> locations;

    /**
     *
     */
    @Transient
    private Set<String> photos;

    /**
     *
     */
    private String termsOfService;

    /**
     *
     */
    private String termsOfCancellation;

    /**
     *
     */
    @Transient
    private DateTime checkIn;

    /**
     *
     */
    @Transient
    private DateTime checkOut;

    @ManyToOne
    private Manager managedBy;

    @ManyToOne
    private Owner owner;

    @Embedded
    private Address address;

    @ManyToOne
    private StructureType type;

    @Override
    public String toString() {
        return "Structure{" +
                "name='" + name + '\'' +
                ", numberOfLocations='" + numberOfLocations + '\'' +
                ", photos=" + photos +
                ", termsOfService='" + termsOfService + '\'' +
                ", termsOfCancellation='" + termsOfCancellation + '\'' +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", managedBy=" + managedBy +
                ", owner=" + owner +
                ", address=" + address +
                ", type=" + type +
                ", id=" + id +
                '}';
    }

    @Id
    @GeneratedValue
    private Long id;

    /**
     * Default constructor
     */
    public Structure() {
        this(null);
    }

    public Structure(String name){
        this(name, null);
    }

    public Structure(String name, Address address) {
        this.name = name;
        this.address = address;

        locations= new ArrayList<Location>();

    }


    public void addLocations(List<Location> newLocations) {
        locations.addAll(newLocations);
        for (Location location : newLocations) {
            location.setStructure(this);
        }

        numberOfLocations += newLocations.size();

    }

    public void addLocation(Location location) {
        locations.add(location);
        location.setStructure(this);

        numberOfLocations += 1;
    }

    /**
     * Not Tested
     *
     * @param period: Interval of date through research Location in databse
     * @return List of Locations that respect criteria of time.
     */
    public List<Location> findLocationByDate(Interval period) {
        List<Location> foundLocations = new ArrayList<Location>();

        DateTime start = period.getStart();
        DateTime end = period.getEnd();

        for (Location l : this.locations) {
            List<Interval> availableDates = l.getAvailableDate();

            for (Interval interval : availableDates) {
                DateTime availableStart = interval.getStart();
                DateTime availableEnd = interval.getEnd();

                if (start.isAfter(availableStart) && end.isBefore(availableEnd)) {
                    foundLocations.add(l);
                    break;
                }

            }

        }
        return foundLocations;
    }

    /**
     * Not Tested
     *
     * @param beds: Number of beds through research Location in database
     * @return List of Locations that respect criteria of bed's number.
     */
    public List<Location> findLocationByBed(Integer beds) {
        List<Location> foundLocations = new ArrayList<Location>();

        for (Location l : this.locations) {
            if (l.getNumberOfBeds().equals(beds)) {
                foundLocations.add(l);
            }
        }

        return foundLocations;
    }


    /* Getter and Setter */

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumberOfLocations() {
        return numberOfLocations;
    }

    public void setNumberOfLocations(String numberOfLocations) {
        this.numberOfLocations = numberOfLocations;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public Set<String> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<String> photos) {
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

    public Manager getManagedBy() {
        return managedBy;
    }

    public void setManagedBy(Manager managedBy) {
        this.managedBy = managedBy;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public StructureType getType() {
        return type;
    }

    public void setType(StructureType type) {
        this.type = type;
    }

    public void setId(Long id) {
        this.id = id;
    }
}