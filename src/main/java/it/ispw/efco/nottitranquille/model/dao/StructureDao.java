package it.ispw.efco.nottitranquille.model.DAO;

import it.ispw.efco.nottitranquille.model.*;
import it.ispw.efco.nottitranquille.view.StructureBean;

import javax.persistence.EntityManager;
import java.util.List;

public class StructureDAO {

    /**
     * Stores {@link Structure} into persistent system.
     *
     * @param structure the Price to persist
     */
    public void store(Structure structure, Person manager) throws Exception {
        if (structure != null) {
            EntityManager entityManager = JPAInitializer.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(manager);
            entityManager.persist(structure);
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
        EntityManager entityManager;
        entityManager = JPAInitializer.getEntityManager();
        Structure structureToDelete;
        structureToDelete = entityManager.find(Structure.class, structure.getId());
//        Request requestToDelete = entityManager.find(Request.class,structure.getRequest().getId());
        entityManager.getTransaction().begin();

        structureToDelete.removeOwners();
        structureToDelete.removeManagedBy();
        structureToDelete.removeAddress();
        structureToDelete.removeRequest();
        structureToDelete.removeLocations();
        entityManager.remove(structureToDelete);

        entityManager.getTransaction().commit();
        entityManager.close();

        entityManager = JPAInitializer.getEntityManager();
        structureToDelete = entityManager.find(Structure.class, structure.getId());
        entityManager.getTransaction().begin();
        entityManager.remove(structureToDelete);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void modifyField(String field, Object value, Long id) {
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
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("from Structure ", Structure.class)
                .getResultList();
    }

    public void store(StructureBean structure, Manager managerInstance, Owner ownerInstance) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();
        Structure newStructure = new Structure(structure, managerInstance, ownerInstance);
        Request request = new Request(managerInstance);
        newStructure.setRequest(request);
        entityManager.merge(newStructure);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}