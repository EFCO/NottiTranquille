package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.Reservation;
import it.ispw.efco.nottitranquille.model.dao.ReservationDAO;

import java.util.*;

/**
 * ReservationController Class implements design pattern Singleton.
 *
 * @see  Reservation
 * @see ReservationDAO
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class ReservationController {

    private static ReservationController ourInstance = new ReservationController();

    public static ReservationController getInstance() {
        return ourInstance;
    }

    private ReservationController() {
    }



}