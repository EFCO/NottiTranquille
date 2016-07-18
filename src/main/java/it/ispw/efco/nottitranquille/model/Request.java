package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.DAO.EmployeeDAO;
import it.ispw.efco.nottitranquille.model.enumeration.RequestStatus;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.List;

/**
 * This class represent a Request created by a {@link Manager} of a {@link Structure} composed by {@link Location} to be
 * add into the system.
 * <p>
 *     A Request could have a certain state represented by {@link RequestStatus} and it can be modified only by {@link Scout}.
 * </p>
 *
 * @see Structure
 * @see RequestStatus
 * @see Scout
 * @see Manager
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
public class Request {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * The date of the request
     */
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime requestDate;

    /**
     * The last date in which the request was accepted
     */
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime acceptedDate;

    /**
     * The last modified date
     */
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime lastModified;

    /**
     * The {@link Manager} that made the Request
     */
    @ManyToOne(optional = false, targetEntity = Manager.class)
    private Manager requestedBy;

    /**
     * The {@link Person} that made the Request
     */
    @Transient
    private Person requestPerson;

    /**
     * The last {@link Scout} that reviewed the Request
     */
    @ManyToOne(cascade = CascadeType.ALL)
    private Scout reviewedBy;

    /**
     * The last {@link Person} that reviewed the Request
     */
    @Transient
    private Person reviewerPerson;

    /**
     * The {@link Structure} bound to the Request
     */
    @OneToOne(optional = false)
    @MapsId
    private Structure structure;

    /**
     * The {@link RequestStatus} of the Request
     */
    @Enumerated
    private RequestStatus status;

    /**
     * Default constructor
     */
    public Request() {
    }

    public Request(DateTime requestDate, DateTime acceptedDate, DateTime lastModified, Manager requestedBy, Scout reviewedBy, Structure structure, RequestStatus status) {
        this.requestDate = requestDate;
        this.acceptedDate = acceptedDate;
        this.lastModified = lastModified;
        this.requestedBy = requestedBy;
        this.reviewedBy = reviewedBy;
        this.structure = structure;
        this.structure.setRequest(this);
        this.status = status;
    }

    public Request(Structure structure, Manager manager) {
        this.structure = structure;
        this.status = RequestStatus.TO_BE_REVIEWED;
        this.requestDate = new DateTime();
        this.requestedBy = manager;
    }

    /**
     * Method called after database loading is finished.
     * It is useful in order to fetch the Request Person from the database.
     */
    @PostLoad
    private void fetchPerson() {
        EmployeeDAO employeeDAO = new EmployeeDAO();

        // Gets request person
        List<Person> requests = employeeDAO.retrievePersonsWithRole(requestedBy.getId());
        this.requestPerson = requests.get(0);

        if (reviewedBy != null) {
            // Gets review person
            List<Person> reviewers = employeeDAO.retrievePersonsWithRole(reviewedBy.getId());
            this.reviewerPerson = reviewers.get(0);
        } else {
            this.reviewerPerson = null;
        }
    }

    public Long getId() {
        return id;
    }

    public DateTime getRequestDate() {
        return requestDate;
    }

    public DateTime getAcceptedDate() {
        return acceptedDate;
    }

    public DateTime getLastModified() {
        return lastModified;
    }

    public Structure getStructure() {
        return structure;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public Person getRequestPerson() {
        return requestPerson;
    }

    public Person getReviewerPerson() {
        return reviewerPerson;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public void setReviewedBy(Scout reviewedBy) {
        this.reviewedBy = reviewedBy;
    }

    public void setLastModified(DateTime lastModified) {
        this.lastModified = lastModified;
    }

    public void setAcceptedDate(DateTime acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

    /**
     * Updates Request's state with the state of the Request provided.
     *
     * @param requestToUpdate the new price
     */
    public void update(Request requestToUpdate) {
        this.id = requestToUpdate.id;
        this.requestDate = requestToUpdate.requestDate;
        this.acceptedDate = requestToUpdate.acceptedDate;
        this.lastModified = requestToUpdate.lastModified;
        this.requestedBy = requestToUpdate.requestedBy;
        this.reviewedBy = requestToUpdate.reviewedBy;
        this.structure = requestToUpdate.structure;
        this.status = requestToUpdate.status;
        this.requestPerson = requestToUpdate.requestPerson;
        this.reviewerPerson = requestToUpdate.reviewerPerson;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", requestDate=" + requestDate +
                ", acceptedDate=" + acceptedDate +
                ", lastModified=" + lastModified +
                ", requestedBy=" + requestedBy +
                ", reviewedBy=" + reviewedBy +
                ", structure=" + structure +
                ", status=" + status +
                ", requestPerson=" + requestPerson +
                '}';
    }
}