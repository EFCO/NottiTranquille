package it.ispw.efco.nottitranquille.model;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
@DiscriminatorValue("scout")
public class Scout extends Role {

    @OneToMany(mappedBy = "reviewedBy", cascade = CascadeType.ALL)
    private List<Request> reviewedRequests;
    /**
     * Default constructor
     */
    public Scout() {
        this.reviewedRequests = new ArrayList<Request>();
    }

    public void addReviewedRequest(Request request) {
        reviewedRequests.add(request);
    }

    public List<Request> getReviewedRequests() {
        return reviewedRequests;
    }
}