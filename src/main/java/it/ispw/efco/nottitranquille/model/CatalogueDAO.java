package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.model.enumeration.RequestStatus;
import it.ispw.efco.nottitranquille.view.SearchBean;
import org.joda.time.DateTime;

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

    public void selectAllRequestsByFilter() {
        // TODO implement here
    }

    public List<Request> selectAcceptedRequestsByFilter(String nation, String city, DateTime checkin, DateTime checkout, String pricerange) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        TypedQuery<Request> query = entityManager.createQuery("FROM Request r WHERE r.status=:s AND (r.structure.address.nation = :n OR r.structure.address.city = :c)",Request.class);
        query.setParameter("n",nation);
        query.setParameter("s", RequestStatus.Accepted);
        query.setParameter("c",city);
        List<Request> result;
        result = query.getResultList();
        return result;
    }
}