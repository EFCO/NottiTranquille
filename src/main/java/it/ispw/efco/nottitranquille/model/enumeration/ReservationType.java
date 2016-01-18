package it.ispw.efco.nottitranquille.model.enumeration;

import it.ispw.efco.nottitranquille.model.LocationType;

/**
 * Enumeration elements are types for the Class {@link LocationType}.
 * They give information about the reservation flow.
 * Element "Direct" means that the Reservation can be created and stored in database immediately.
 * Element "WithConfirmation" means that the Reservation have to be confirmed by Manager after that
 * a Tenant asks for it. Just after confirmation the Tenant can pay.
 *
 * Created by emanuele on 18/01/16.
 */
public enum ReservationType {
    Direct, WithConfirmation
}
