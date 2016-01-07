package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.view.SearchBean;

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
    public List<Structure> selectAcceptedRequestsByFilter(SearchBean searchBean) {
        // TODO implement here
        EntityManager entityManager = JPAInitializer.getEntityManager();
        TypedQuery<Structure> query = entityManager.createQuery("FROM Structure s WHERE s.address.nation = :n",Structure.class);
        query.setParameter("n",searchBean.getNation());
        List<Structure> result;
        result = query.getResultList();
        return result;
    }

    /**
     * 
     */
    public void saveStructure(Structure structure) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(structure);
        entityManager.getTransaction().commit();
    }

    public void selectAllRequestsByFilter() {
        // TODO implement here
    }

}