package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.enumeration.Gender;

import javax.persistence.*;
import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "persontype",discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("Person")
public class Person {

    /**
     * Default constructor
     */
    public Person() {
    }

    /**
     * 
     */
    @Column
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
    private Date birthdate;

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