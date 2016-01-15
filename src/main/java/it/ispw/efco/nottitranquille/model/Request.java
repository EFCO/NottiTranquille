package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.enumeration.RequestStatus;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import javax.persistence.*;
import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     */
    private Interval requestDate;

    /**
     *
     */
    private Interval acceptedDate;

    /**
     *
     */
    private DateTime lastModified;

    @ManyToOne
    private Applicant requestedBy;

    @ManyToOne
    private Scout reviewedBy;

    @ManyToOne
    private Structure structure;

    @Enumerated
    private RequestStatus status;

    /**
     * Default constructor
     */
    public Request() {
    }






}