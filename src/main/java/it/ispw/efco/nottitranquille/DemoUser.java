package it.ispw.efco.nottitranquille;


import it.ispw.efco.nottitranquille.model.Manager;
import it.ispw.efco.nottitranquille.model.Tenant;
import it.ispw.efco.nottitranquille.model.dao.ManagerDAO;
import it.ispw.efco.nottitranquille.model.dao.TenantDAO;

public class DemoUser {

    public static void main(String args[]) {

        /* Manager */

        Manager manager = new Manager();
        manager.setFirstName("Claudio");
        manager.setLastName("Pastorini");

        manager.setUsername("manager");
        manager.setPassword("password");

        try {
            ManagerDAO.store(manager);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        /* Tenant */

        Tenant me = new Tenant();
        me.setFirstName("Emanuele");
        me.setLastName("Vannacci");

        me.setUsername("Zanna");
        me.setPassword("password");

        try {
            TenantDAO.store(me);
        } catch (Exception e2) {
            e2.printStackTrace();
            System.exit(1);
        }
    }
}
