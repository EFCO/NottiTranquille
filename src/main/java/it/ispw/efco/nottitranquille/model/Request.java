package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.enumeration.RequestStatus;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

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
    public Request(DateTime requestDate, DateTime acceptedDate, DateTime lastModified, Applicant requestedBy, Person reviewedBy, Structure structure, RequestStatus status) {
        this.requestDate = requestDate;
        this.acceptedDate = acceptedDate;
        this.lastModified = lastModified;
        this.requestedBy = requestedBy;
        this.reviewedBy = reviewedBy;
        this.structure = structure;
        structure.setRequest(this);
        this.status = status;
    }

    public Request(Structure structure, Applicant applicant) {
        this.structure = structure;
        structure.setRequest(this);
        this.status = RequestStatus.To_be_reviewed;
        this.requestDate = new DateTime();
        this.requestedBy = applicant;
    }

    public Request() {
    }

    /**
     * 
     */
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime requestDate;

    /**
     * 
     */
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime acceptedDate;

    /**
     * 
     */
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime lastModified;

    @Transient //TODO Fix annotation (//Scout/Manager)
    private Applicant requestedBy;

    @ManyToOne
    private Person reviewedBy;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @MapsId
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

    public RequestStatus getStatus() {
        return status;
    }
}