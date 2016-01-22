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
    }

    public List<Request> selectAllRequestsByFilter(String nation, String city, RequestStatus status) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        TypedQuery<Request> query = entityManager.createQuery(
                "FROM Request r WHERE r.status=:s AND (r.structure.address.nation = :n AND r.structure.address.city = :c)",Request.class);
        query.setParameter("n",nation);
        query.setParameter("s", status);
        query.setParameter("c",city);
        List<Request> result;
        result = query.getResultList();
        return result;
    }

    public List<Request> selectAcceptedRequests(String nation, String city) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        TypedQuery<Request> query = entityManager.createQuery(
                "FROM Request r WHERE r.status=:s AND (r.structure.address.nation = :n AND r.structure.address.city = :c)",Request.class);
        query.setParameter("n",nation);
        query.setParameter("s", RequestStatus.Accepted);
        query.setParameter("c",city);
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