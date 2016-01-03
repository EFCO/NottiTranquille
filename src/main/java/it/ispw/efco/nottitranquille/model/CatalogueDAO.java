package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.view.SearchBean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class CatalogueDAO {


    private static EntityManagerFactory entityManagerFactory;

    private static EntityManager entityManager;
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
        String nation = searchBean.getNation();
        TypedQuery<Structure> query = entityManager.createQuery("SELECT structure FROM Structure structure WHERE structure.address.nation = :nation",Structure.class);
        List<Structure> result;
        result = query.getResultList();
        return result;
    }

    /**
     * 
     */
    public void selectAllRequestsByFilter() {
        // TODO implement here
    }

}