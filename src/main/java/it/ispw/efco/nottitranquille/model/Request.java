package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.enumeration.RequestStatus;

import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class Request {

    /**
     * Default constructor
     */
    public Request() {
    }

    /**
     * 
     */
    private Date requestDate;

    /**
     * 
     */
    private Date acceptedDate;

    /**
     * 
     */
    private Date lastModified;

    private Applicant requestedBy;

    private Scout reviewedBy;

    private Structure structure;

    private RequestStatus status;






}