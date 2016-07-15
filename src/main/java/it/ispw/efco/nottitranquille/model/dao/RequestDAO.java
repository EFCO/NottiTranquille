package it.ispw.efco.nottitranquille.model.DAO;

import it.ispw.efco.nottitranquille.model.JPAInitializer;
import it.ispw.efco.nottitranquille.model.Location;
import it.ispw.efco.nottitranquille.model.Request;
import it.ispw.efco.nottitranquille.model.enumeration.RequestStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class RequestDAO {
    /**
     * Default constructor
     */
    public RequestDAO() {

    }

    /**
     * 
     */
//    public void saveRequest(Request request) {
//        EntityManager entityManager = JPAInitializer.getEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.persist(request);
//        entityManager.getTransaction().commit();
//        entityManager.close();
//    }

    public List<Request> selectAllRequestsByFilter(String nation, String city, RequestStatus status) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        String querystring = "FROM Request r WHERE r.status = :s";
        if (!nation.equals("")) {
            querystring += " AND r.structure.address.nation = :n";
        }
        if (!city.equals("")) {
            querystring += " AND r.structure.address.city = :c";
        }
        TypedQuery<Request> query = entityManager.createQuery(querystring,Request.class);
        if (!nation.equals("")) {
            query.setParameter("n", nation);
        }
        if (!city.equals("")) {
            query.setParameter("c", city);
        }
        query.setParameter("s", status);
        List<Request> result;
        result = query.getResultList();
        entityManager.close();
        return result;
    }

    public List<Location> selectAcceptedRequests(String nation, String city) throws IllegalArgumentException, PersistenceException {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        String querystring = "FROM Location l WHERE l.structure.request.status = 2";
        if (!nation.equals("")) {
            querystring += " AND l.structure.address.nation = :n";
        }
        if (!city.equals("")) {
            querystring += " AND l.structure.address.city = :c";
            }
        //can throw Illegal Argument exception
        TypedQuery<Location> query = entityManager.createQuery(querystring, Location.class);
        if (!nation.equals("")) {
            query.setParameter("n", nation);
        }
        if (!city.equals("")) {
            query.setParameter("c", city);
        }
        List<Location> result;
        //TODO tutte le eccezioni dovrebbero essere lanciate?
        result = query.getResultList();
        entityManager.close();
        return result;
    }

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