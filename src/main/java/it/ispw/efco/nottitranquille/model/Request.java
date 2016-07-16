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


    public Request(Manager manager) {
        this.status = RequestStatus.To_be_reviewed;
        this.requestDate = new DateTime();
        this.setRequestedBy(manager);
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

    //    @ManyToOne(optional = false, targetEntity = Manager.class)
    @ManyToOne
    private Manager requestedBy;

    //    @ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne
    private Scout reviewedBy;

    @OneToOne
    @JoinColumn(name = "request_id")
    @MapsId
    private Structure structure;

    @Enumerated
    private RequestStatus status;


    @Id
    private Long id;


    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public void setRequestedBy(Manager manager) {
        this.requestedBy = manager;
        manager.addRequest(this);
    }

    public void setReviewedBy(Scout scout) {
        this.reviewedBy = scout;
        scout.addReviewedRequest(this);
    }

    public Long getId() {
        return id;
    }


    public Structure getStructure() {
        return structure;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void removeRequestedBy() {
        if (this.requestedBy != null) {
            this.requestedBy.removeRequest(this);
        }
        this.requestedBy = null;
    }
}