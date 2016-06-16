package it.ispw.efco.nottitranquille.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.*;


/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
@Entity
public class Manager extends Applicant {

    @OneToMany
    // Bound to use linked list and not Deque because it is not supported from JPA.
    private List<Reservation> toApprove;

    /**
     * Default constructor
     */
    public Manager() {
        toApprove = new ArrayList<Reservation>();

    }

    public boolean addReservationToApprove(Reservation reservation) {
        for (Reservation r : toApprove)
            if (r.equals(reservation))
                return false;

        toApprove.add(reservation);
        return true;
    }

    public boolean deleteReservationToApprove(Reservation reservation) {
        Iterator list = toApprove.iterator();
        while (list.hasNext()) {
            Reservation r = (Reservation) list.next();

            if (r.equals(reservation)) {
                list.remove();
                return true;
            }
        }

        return false;
    }

    public void update(Manager toUpdate) {
        super.update(toUpdate);
        this.toApprove = toUpdate.getToApprove();
    }

    public List<Reservation> getToApprove() {
        return toApprove;
    }

}