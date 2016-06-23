package it.ispw.efco.nottitranquille;


import it.ispw.efco.nottitranquille.model.Manager;
import it.ispw.efco.nottitranquille.model.Tenant;
import it.ispw.efco.nottitranquille.model.dao.RegisteredUserDAO;

public class DemoUser {

    public static void main(String args[]) {

        /* Manager */

        Manager manager = new Manager();
        manager.setFirstName("Claudio");
        manager.setLastName("Pastorini");

        manager.setUsername("manager");
        manager.setPassword("password");

        RegisteredUserDAO.store(manager);

        /* Tenant */

        Tenant me = new Tenant();
        me.setFirstName("Emanuele");
        me.setLastName("Vannacci");

        me.setUsername("Zanna");
        me.setPassword("password");

        RegisteredUserDAO.store(me);
    }
}
