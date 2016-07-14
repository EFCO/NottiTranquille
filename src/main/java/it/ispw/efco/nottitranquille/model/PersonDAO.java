package it.ispw.efco.nottitranquille.model;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Federico on 14/07/2016.
 */
public class PersonDAO {

    public List<Person> selectEmployees() {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        TypedQuery<Person> query = entityManager.createQuery("FROM Person p", Person.class);
        List<Person> result;
        result = query.getResultList();
        entityManager.close();
        return result;
    }

    public void remove(Person selectedItem) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(selectedItem);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
