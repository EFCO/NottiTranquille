package it.ispw.efco.nottitranquille.model.dao;

import it.ispw.efco.nottitranquille.model.JPAInitializer;
import it.ispw.efco.nottitranquille.model.Location;
import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.model.Structure;

import javax.persistence.EntityManager;
import java.util.List;

public class StructureDAO {

    /**
     * Stores {@link Structure} into persistent system.
     *
     * @param structure the Price to persist
     */
    public void store(Structure structure, Person manager, Person owner) throws Exception {
        if (structure != null) {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(structure);
            entityManager.merge(manager);
            if (owner != null)
                entityManager.merge(owner);
            entityManager.getTransaction().commit();
            entityManager.close();
        } else {
            throw new Exception("The entity can not be null!");
        }
    }

    public Structure select(Long id) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();
        Structure structure = entityManager.find(Structure.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return structure;
    }

    public void delete(Structure structure) throws Exception {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();
        structure.removeOwners();
//        structure.removeRequest();
        structure.removeManagedBy();
        entityManager.remove(entityManager.merge(structure));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void modifyField(String field, String value, Long id) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        String querystring = "UPDATE Structure s SET s." + field + " = :v WHERE s.id = :id";
        entityManager.getTransaction().begin();
        entityManager.createQuery(querystring).setParameter("v", value).setParameter("id", id).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void modifyAddress(Address newAddress, Long id) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        Person person = entityManager.find(Person.class, id);
        entityManager.getTransaction().begin();
        //I have to do this because new Address records would be created otherwise
        person.getAddress().setAddress(newAddress.getAddress());
        person.getAddress().setCity(newAddress.getCity());
        person.getAddress().setNation(newAddress.getNation());
        person.getAddress().setPostalcode(newAddress.getPostalcode());
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static List<Structure> retrieveStructures() {
        EntityManager entityManager = it.ispw.efco.nottitranquille.JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from Structure ", Structure.class)
                .getResultList();
    }
}