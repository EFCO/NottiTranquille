package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.enumeration.Gender;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "persontype",discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("Person")
public abstract class Person {

    /**
     * Default constructor
     */
    public Person() {
    }

    /**
     * 
     */
    private String firstName;

    /**
     * 
     */
    private String lastName;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private DateTime birthdate;

    /**
     * 
     */
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    private Gender gender;

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }


}