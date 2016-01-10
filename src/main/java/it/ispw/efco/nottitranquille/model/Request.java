package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.enumeration.RequestStatus;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.*;

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
    public Request(Date requestDate, Date acceptedDate, Date lastModified, Applicant requestedBy, Scout reviewedBy, Structure structure, RequestStatus status) {
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
    @Basic
    private Date requestDate;

    /**
     * 
     */
    @Basic
    private Date acceptedDate;

    /**
     * 
     */
    @Basic
    private Date lastModified;

    @Transient //TODO
    private Applicant requestedBy;

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