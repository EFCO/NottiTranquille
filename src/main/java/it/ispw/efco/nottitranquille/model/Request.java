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
@SuppressWarnings("JpaDataSourceORMInspection")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private DateTime lastModified;

    private String ApplicantUsername;

    private String reviewedBy;

    @Enumerated
    private RequestStatus status;

    @ManyToOne
    private Structure structure;

    /**
     * Default constructor
     */
    public Request() {
    }


}