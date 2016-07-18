package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.DAO.EmployeeDAO;
import org.joda.time.DateTime;

import javax.persistence.*;

import it.ispw.efco.nottitranquille.model.enumeration.StructureType;
import it.ispw.efco.nottitranquille.view.StructureBean;

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

    /**
     *
     */
    @ManyToOne
    @Access(AccessType.PROPERTY)
    private Manager managedBy;

    /**
     *
     */
    @ManyToMany(targetEntity = Owner.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Owner> owners = null;

    /**
     *
     */
    @OneToOne(targetEntity = Address.class, optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;

    /**
     *
     */
    @Enumerated(EnumType.STRING)
    private StructureType type;

    /**
     *
     */
    @OneToMany(targetEntity = Service.class, fetch = FetchType.EAGER)
    private List<Service> services;

    /**
     *
     */
    @OneToMany(targetEntity = Location.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Location> locations;

    /**
     *
     */
    @OneToOne(targetEntity = Request.class, optional = false, mappedBy = "structure", cascade = CascadeType.ALL)
    private Request request;

    /**
     *
     */
    @Transient
    private Person managePerson;

    /**
     *
     */
    @Transient
    private Person ownerPerson;

    /**
     * Default constructor
     */
    public Structure() {
    }

    public Structure(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Structure(StructureBean bean, Manager manager, Owner owner) {
        this.name = bean.getName();
        this.numberOfLocations = 0;
        this.photos = new ArrayList<>();
        this.termsOfService = bean.getTermsOfService();
        this.termsOfCancellation = bean.getTermsOfCancellation();
        this.checkIn = bean.getCheckIn();
        this.checkOut = bean.getCheckOut();
        this.managedBy = manager;
        this.owners = new ArrayList<>();
        this.owners.add(owner);
        this.address = new Address(bean.getNation(), bean.getCity(), bean.getAddress(), bean.getPostalcode());
        this.type = bean.getType();
        this.services = bean.getServices();
        this.locations = new ArrayList<>();
    }

    @PostLoad
    private void fetchPerson() {
        EmployeeDAO employeeDAO = new EmployeeDAO();

        // Gets request person
        List<Person> managers = employeeDAO.retrievePersonsWithRole(managedBy.getId());
        this.managePerson = managers.get(0);

        // Gets owner person
        if (owners != null) {
            // Gets review person
            List<Person> ownersPersons = employeeDAO.retrievePersonsWithRole(owners.get(0).getId());
            this.ownerPerson = ownersPersons.get(0);
        } else {
            this.ownerPerson = null;
        }
    }

    public Structure(List<Service> services) {
        this.services = services;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfLocations() {
        return numberOfLocations;
    }

    public void setNumberOfLocations(int numberOfLocations) {
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

    public Manager getManagedBy() {
        return managedBy;
    }

    public void setManagedBy(Manager managedBy) {
        this.managedBy = managedBy;
    }

    public List<Owner> getOwners() {
        return owners;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
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

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Person getManagePerson() {
        return managePerson;
    }

    public void setManagePerson(Person managePerson) {
        this.managePerson = managePerson;
    }

    public Person getOwnerPerson() {
        return ownerPerson;
    }

    public void setOwnerPerson(Person ownerPerson) {
        this.ownerPerson = ownerPerson;
    }

    public void addLocation(Location location) {
        this.locations.add(location);
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
}