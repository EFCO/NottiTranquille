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

    public List<UserBean> login(String username, String password) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        String querystring = "FROM usersdata WHERE username = :u AND password = :p";
        TypedQuery<UserBean> query = entityManager.createQuery(querystring,UserBean.class);
        query.setParameter("u",username);
        query.setParameter("p",password);
        List<UserBean> result = query.getResultList();
        return result;
    }

    public void register(UserBean userBean) {
        EntityManager entityManager = JPAInitializer.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(userBean);
        entityManager.getTransaction().commit();
    }
}
