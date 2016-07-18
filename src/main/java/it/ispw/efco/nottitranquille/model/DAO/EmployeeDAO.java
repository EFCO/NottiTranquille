package it.ispw.efco.nottitranquille.model.DAO;

import it.ispw.efco.nottitranquille.JPAInitializer;
import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.model.Role;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * DAO for Employee (all {@link Person} with {@link Role}) entity.
 *
 * @see Person
 * @see it.ispw.efco.nottitranquille.model.Role
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class EmployeeDAO {

    /**
     * Retrieves {@link Person} with given {@link Role}.
     *
     * @param id the id of the Role to retrieve
     * @return the list of Person with given Role.
     */
    public List<Person> retrievePersonsWithRole(long id) {
        EntityManager entityManager = JPAInitializer.getEntityManager();

        return entityManager
                .createQuery("SELECT person FROM Person person JOIN person.roles role WHERE " +
                        "role.id = :roleId",
                        Person.class)
                .setParameter("roleId", id)
                .getResultList();
    }

    /**
     * Retrieves {@link Person}s with given {@link Role}.
     *
     * @param role the Role to retrieve
     * @return the list of Person with given Role.
     */
    public List<Person> retrievePersonsWithRole(Role role) {
        return retrievePersonsWithRole(role.getId());
    }
}