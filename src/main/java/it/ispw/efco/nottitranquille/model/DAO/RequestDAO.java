package it.ispw.efco.nottitranquille.model.DAO;

import it.ispw.efco.nottitranquille.model.*;
import it.ispw.efco.nottitranquille.model.enumeration.RequestStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * DAO for {@link Request} entity.
 *
 * @see Request
 * @see it.ispw.efco.nottitranquille.model.Structure
 * @see Location
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class RequestDAO {

    /**
     * Updates {@link Request} into persistent system with the given Request's state.
     *
     * @param requestToUpdate the Request to update with the new state
     */
    public void update(Request requestToUpdate) {
        EntityManager entityManager = it.ispw.efco.nottitranquille.JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();

        Request requestLoaded = entityManager.find(Request.class, requestToUpdate.getId());
        requestLoaded.update(requestToUpdate);
        entityManager.merge(requestToUpdate.getStructure());

        entityManager.getTransaction().commit();
    }

    /**
     * Retrieves {@link Request}s from persistent system filtered the result.
     *
     * @param address the address of the {@link Structure} into the Request
     * @param city the city of the {@link Structure} into the Request
     * @param postalCode the postal code of the {@link Structure} into the Request
     * @param nation the nation of the {@link Structure} into the Request
     * @param structureName the name of the {@link Structure} into the Request
     * @param requestPerson the name of the {@link Person} that created the Request
     * @param status the {@link RequestStatus} of the Request
     * @return the list of all the Request that match the filter
     */
    //TODO
    public List<Request> retrieveRequestsByFilter(String address, String city, String postalCode, String nation, String structureName, String requestPerson, RequestStatus status) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        String queryString = "FROM Request request";
        if (!requestPerson.equals("")) {
            queryString += " JOIN request.requestedBy role WHERE role.id = :roleId";
        } else {
            queryString += " WHERE request.status = :status";
        }
        if (!address.equals("")) {
            queryString += " AND LOWER(request.structure.address.address) LIKE :address";
        }
        if (!city.equals("")) {
            queryString += " AND LOWER(request.structure.address.city) = :city";
        }
        if (!postalCode.equals("")) {
            queryString += " AND request.structure.address.postalcode = :postalCode";
        }
        if (!nation.equals("")) {
            queryString += " AND LOWER(request.structure.address.nation) = :nation";
        }
        if (!structureName.equals("")) {
            queryString += " AND LOWER(request.structure.name) LIKE :structureName";
        }

        TypedQuery<Request> query = entityManager.createQuery(queryString, Request.class);
        if (!address.equals("")) {
            query.setParameter("address", address.toLowerCase());
        }
        if (!city.equals("")) {
            query.setParameter("city", city.toLowerCase());
        }
        if (!postalCode.equals("")) {
            query.setParameter("postalCode", postalCode);
        }
        if (!nation.equals("")) {
            query.setParameter("nation", nation.toLowerCase());
        }
        if (!structureName.equals("")) {
            query.setParameter("structureName", structureName.toLowerCase());
        }
        if (!requestPerson.equals("")) {
            query.setParameter("requestPerson", requestPerson);
        }
        query.setParameter("status", status);

        return query.getResultList();
    }

    /**
     *
     * @param nation
     * @param city
     * @return
     * @throws IllegalArgumentException
     * @throws PersistenceException
     */
    public List<Request> selectAcceptedRequests(String nation, String city) throws IllegalArgumentException, PersistenceException {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        String queryString = "FROM Request r WHERE r.status = 0";
        if (!nation.equals("")) {
            queryString += " AND r.structure.address.nation = :n";
        }
        if (!city.equals("")) {
            queryString += " AND r.structure.address.city = :c";
        }
        //can throw Illegal Argument exception
        TypedQuery<Request> query = entityManager.createQuery(queryString, Request.class);
        if (!nation.equals("")) {
            query.setParameter("n", nation);
        }
        if (!city.equals("")) {
            query.setParameter("c", city);
        }
        List<Request> result;
        //TODO tutte le eccezioni dovrebbero essere lanciate?
        result = query.getResultList();
        entityManager.close();
        return result;
    }

    /**
     *
     * @param id
     * @return
     */
    public Location selectLocationWithId(Long id) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        TypedQuery<Location> query = entityManager.createQuery("FROM Location l WHERE l.id=:id",Location.class);
        query.setParameter("id",id);
        Location result;
        result = query.getSingleResult();
        entityManager.close();
        return result;
    }
}