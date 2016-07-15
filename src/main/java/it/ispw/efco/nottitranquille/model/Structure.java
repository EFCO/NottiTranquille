package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.enumeration.StructureType;
import it.ispw.efco.nottitranquille.view.StructureBean;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
public class Structure {

    @Id
    @GeneratedValue
    private Long id;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private int numberOfLocations;

    /**
     *
     */
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> photos;

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
    private DateTime checkIn;

    /**
     *
     */
    private DateTime checkOut;

    @ManyToOne
//    @Access(AccessType.PROPERTY)
    private Manager managedBy;

    //    @ManyToMany(targetEntity = Owner.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ManyToMany(mappedBy = "ownedStructures",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Owner> owners = new ArrayList<Owner>();

    @OneToOne(targetEntity = Address.class, optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;

    @Enumerated(EnumType.STRING)
    private StructureType type;

    @OneToMany(targetEntity = Service.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Service> services;

    //    @OneToMany(targetEntity = Location.class,mappedBy = "structure", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "structure", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Location> locations = new ArrayList<Location>();

    @OneToOne(mappedBy = "structure", cascade = CascadeType.ALL, orphanRemoval = true)
    private Request request;

    public Structure() {
    }


    public Structure(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Structure(StructureBean bean, Manager manager, Owner owner) {
        this.name = bean.getName();
        this.numberOfLocations = 0;
        this.photos = new ArrayList<String>();
        this.termsOfService = bean.getTermsOfService();
        this.termsOfCancellation = bean.getTermsOfCancellation();
        this.checkIn = bean.getCheckIn();
        this.checkOut = bean.getCheckOut();
        this.setManagedBy(manager);
        this.addOwner(owner);
        this.address = new Address(bean.getNation(), bean.getCity(), bean.getAddress(), bean.getPostalcode());
        this.type = bean.getType();
        this.services = bean.getServices();
    }

    public Structure(List<Service> services) {
        this.services = services;
    }

    public void addOwner(Owner owner) {
        this.owners.add(owner);
        owner.ownedStructures.add(this);
    }

    private void removeOwner(Owner owner) {
        this.owners.remove(owner);
        owner.ownedStructures.remove(this);
    }

    public void removeOwners() {
        for (Owner owner : new ArrayList<Owner>(this.owners)) {
            removeOwner(owner);
        }
    }

    public void setRequest(Request request) {
        this.request = request;
        request.setStructure(this);
    }

    public void removeRequest() {
        if (this.request != null) {
            this.request.setStructure(null);
        }
        this.request = null;
    }

    public void addLocation(Location location) {
        this.locations.add(location);
        location.setStructure(this);
    }

    public void removeLocation(Location location) {
        location.setStructure(null);
        this.locations.remove(location);
    }

    public String getName() {
        return name;
    }

    public Address getStructureAddress() {
        return address;
    }

    public Long getId() {
        return id;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public List<Owner> getOwners() {
        return owners;
    }

    public Address getAddress() {
        return address;
    }

    public void setNumberOfLocations(int numberOfLocations) {
        this.numberOfLocations = numberOfLocations;
    }

    public void setTermsOfService(String termsOfService) {
        this.termsOfService = termsOfService;
    }

    public void setTermsOfCancellation(String termsOfCancellation) {
        this.termsOfCancellation = termsOfCancellation;
    }

    public void setCheckIn(DateTime checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(DateTime checkOut) {
        this.checkOut = checkOut;
    }

    private void setManagedBy(Manager managedBy) {
        this.managedBy = managedBy;
        managedBy.addManagedStructure(this);
    }

    public List<String> getPhotos() {
        return photos;
    }

    public String getTermsOfService() {
        return termsOfService;
    }

    public String getTermsOfCancellation() {
        return termsOfCancellation;
    }

    public DateTime getCheckIn() {
        return checkIn;
    }

    public List<Service> getServices() {
        return services;
    }

    public DateTime getCheckOut() {
        return checkOut;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }

    public void setType(StructureType type) {
        this.type = type;
    }

    public Request getRequest() {
        return request;
    }

    public int getNumberOfLocations() {
        return numberOfLocations;
    }

    public StructureType getType() {
        return type;
    }


    public Manager getManagedBy() {
        return managedBy;
    }

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
                ", owner=" + owners +
                ", address=" + address +
                ", type=" + type +
                ", services=" + services +
                ", id=" + id +
                '}';
    }

    public void removeManagedBy() {
        if (this.managedBy != null) {
            this.managedBy.removeManagedStructure(this);
        }
        this.managedBy = null;
    }
}