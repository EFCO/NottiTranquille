package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.enumeration.RequestStatus;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
public class Request {

    /**
     * Default constructor
     * @param requestDate
     * @param acceptedDate
     * @param lastModified
     * @param requestedBy
     * @param reviewedBy
     * @param structure
     * @param status
     */
    public Request(DateTime requestDate, DateTime acceptedDate, DateTime lastModified, Person requestedBy, Scout reviewedBy, Structure structure, RequestStatus status) {
        this.requestDate = requestDate;
        this.acceptedDate = acceptedDate;
        this.lastModified = lastModified;
        this.requestedBy = requestedBy;
        this.reviewedBy = reviewedBy;
        this.structure = structure;
        structure.setRequest(this);
        this.status = status;
    }

    public Request(Structure structure) {
        this.structure = structure;
        structure.setRequest(this);
        this.status = RequestStatus.Accepted;
    }

    public Request() {
    }

    /**
     * 
     */
    private DateTime requestDate;

    /**
     * 
     */
    private DateTime acceptedDate;

    /**
     * 
     */
    private DateTime lastModified;

    @ManyToOne      //Scout/Manager
    private Person requestedBy;

    @ManyToOne
    private Scout reviewedBy;

    @OneToOne(optional=false, cascade = javax.persistence.CascadeType.ALL)
    private Structure structure;

    @Enumerated
    private RequestStatus status;


    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }


    public Structure getStructure() {
        return structure;
    }
}