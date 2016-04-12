package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.enumeration.LocationType;
import it.ispw.efco.nottitranquille.model.enumeration.RequestStatus;
import it.ispw.efco.nottitranquille.view.SearchBean;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class CatalogueDAO {
    /**
     * Default constructor
     */
    public CatalogueDAO() {

    }

    /**
     * 
     */
    public void saveRequest(Request request) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(request);
        entityManager.getTransaction().commit();
        JPAInitializer.shutdown();
    }

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
        return result;
    }

    public List<Request> selectAcceptedRequests(String nation, String city) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        String querystring = "FROM Request r WHERE r.status = 0";
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
        List<Request> result;
        result = query.getResultList();
        return result;
    }

    public Location selectLocationWithId(Long id) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        TypedQuery<Location> query = entityManager.createQuery("FROM Location l WHERE l.id=:id",Location.class);
        query.setParameter("id",id);
        Location result;
        result = query.getSingleResult();
        return result;
    }
}