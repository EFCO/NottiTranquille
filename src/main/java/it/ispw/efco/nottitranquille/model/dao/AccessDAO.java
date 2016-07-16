package it.ispw.efco.nottitranquille.model.DAO;

import it.ispw.efco.nottitranquille.model.*;
import it.ispw.efco.nottitranquille.view.LoginBean;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Federico on 02/05/2016.
 */
public class AccessDAO {

    public AccessDAO() {
    }

    public List<Person> selectUser(String username, String password) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        String querystring = "FROM Person WHERE (username = :u OR email =:u) AND password = :p";
        TypedQuery<Person> query = entityManager.createQuery(querystring,Person.class);
        query.setParameter("u",username);
        query.setParameter("p",password);
        List<Person> result = query.getResultList();
        entityManager.close();
        return result;
    }

    public void saveLogin(LoginBean lb) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(lb);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void setExpired(String cookie) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        String querystring = "FROM loggedusers WHERE cookie = :c";
        TypedQuery<LoginBean> query = entityManager.createQuery(querystring,LoginBean.class);
        query.setParameter("c",cookie);
        List<LoginBean> result = query.getResultList();
        LoginBean lb = entityManager.find(LoginBean.class,result.get(0).getId());
        entityManager.getTransaction().begin();
        lb.setExpired(true);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public LoginBean getLoggedUser(String username, String password){
        EntityManager entityManager = JPAInitializer.getEntityManager();
        String querystring = "FROM loggedusers WHERE username = :u AND password = :p";
        TypedQuery<LoginBean> query = entityManager.createQuery(querystring,LoginBean.class);
        query.setParameter("u",username);
        query.setParameter("p",password);
        List<LoginBean> result = query.getResultList();
        if (result.size() == 0) {
            return null;
        }
        entityManager.close();
        return result.get(0);
    }

    public void updateLogin(Long id, String cookie) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        LoginBean lb = entityManager.find(LoginBean.class,id);
        entityManager.getTransaction().begin();
        lb.setCookie(cookie);
        lb.setExpired(false);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public boolean removeLoggedUser(Long id){
        EntityManager entityManager = JPAInitializer.getEntityManager();
        LoginBean lb = entityManager.find(LoginBean.class,id);
        entityManager.getTransaction().begin();
        entityManager.remove(lb);
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    public void register(Person person) throws Exception {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(person);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Long verifyPendingStatus(String hash) throws Exception {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        String querystring = "FROM Person WHERE hash = :h AND req_status = :rs";
        TypedQuery<Person> query = entityManager.createQuery(querystring,Person.class);
        query.setParameter("h",hash);
        query.setParameter("rs","pending");
        List<Person> result = query.getResultList();
        entityManager.close();
        return result.get(0).getId();
    }

    public void verify(Long id) throws Exception {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        Person person = entityManager.find(Person.class,id);
        entityManager.getTransaction().begin();
        person.setReq_status("accepted");
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void modifyField(String field, String value, Long id) throws Exception{
        EntityManager entityManager = JPAInitializer.getEntityManager();
        String querystring = "UPDATE Person p SET p." + field + " = :v WHERE p.id = :id";
        entityManager.getTransaction().begin();
        entityManager.createQuery(querystring).setParameter("v",value).setParameter("id",id).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void modifyAddress(Address newAddress, Long id) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        Person person = entityManager.find(Person.class,id);
        entityManager.getTransaction().begin();
        //I have to do this because new Address records would be created otherwise
        person.getAddress().setAddress(newAddress.getAddress());
        person.getAddress().setCity(newAddress.getCity());
        person.getAddress().setNation(newAddress.getNation());
        person.getAddress().setPostalcode(newAddress.getPostalcode());
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void updateLoggedUser(String old_pass, String new_pass) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        String querystring = "UPDATE loggedusers lu SET lu.password = :n WHERE lu.password = :o";
        entityManager.getTransaction().begin();
        entityManager.createQuery(querystring).setParameter("n",new_pass).setParameter("o",old_pass).executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public String checkPassword(Long id) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        Person person = entityManager.find(Person.class,id);
        entityManager.getTransaction().begin();
        String password = person.getPassword();
        entityManager.getTransaction().commit();
        entityManager.close();
        return password;
    }

    public void addManagerRole(Long id) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        Person person = entityManager.find(Person.class, id);
        entityManager.getTransaction().begin();
        try {
            person.addRole(new Manager());
        } catch (Exception e) {
            e.printStackTrace();
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Person selectUserByEmail(String ownerEmail) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        String querystring = "FROM Person WHERE email =:e";
        TypedQuery<Person> query = entityManager.createQuery(querystring, Person.class);
        query.setParameter("e", ownerEmail);
        List<Person> result = query.getResultList();
        entityManager.close();
        return result.get(0);
    }

    public void addOwnerRole(Person manager) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        Person person = entityManager.find(Person.class, manager.getId());
        entityManager.getTransaction().begin();
        try {
            person.addRole(new Owner());
        } catch (Exception e) {
            e.printStackTrace();
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

//    public void addOwnerRole(Owner owner, Long id) {
//        EntityManager entityManager = JPAInitializer.getEntityManager();
//        Person person = entityManager.find(Person.class, id);
//        entityManager.getTransaction().begin();
//        try {
//            person.addRole(owner);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        entityManager.getTransaction().commit();
//        entityManager.close();
//    }
}
