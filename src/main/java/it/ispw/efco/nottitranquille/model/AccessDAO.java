package it.ispw.efco.nottitranquille.model;

import it.ispw.efco.nottitranquille.view.UserBean;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Federico on 02/05/2016.
 */
public class AccessDAO {

    public AccessDAO() {
    }

    public List<UserBean> isRegistered(String username, String password) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        String querystring = "FROM usersdata WHERE username = :u AND password = :p";
        TypedQuery<UserBean> query = entityManager.createQuery(querystring,UserBean.class);
        query.setParameter("u",username);
        query.setParameter("p",password);
        List<UserBean> result = query.getResultList();
        return result;
    }

    public boolean login(Long id){
        EntityManager entityManager = JPAInitializer.getEntityManager();
        UserBean ub = entityManager.find(UserBean.class,id);
        if (ub.isLogged()) {
            return false;
        }
        entityManager.getTransaction().begin();
        ub.setLogged(true);
        entityManager.getTransaction().commit();
        return true;
    }

    public void logout(Long id){
        EntityManager entityManager = JPAInitializer.getEntityManager();
        UserBean ub = entityManager.find(UserBean.class,id);
        entityManager.getTransaction().begin();
        ub.setLogged(false);
        entityManager.getTransaction().commit();
    }

    public void register(UserBean userBean) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(userBean);
        entityManager.getTransaction().commit();
    }
}
