package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.SingletonEmployee;
import javafx.application.Application;

/**
 * {@link SingleWindow} with Logout method.
 */
public class SingleWindowsLogged extends SingleWindow {

    /**
     * Logout from {@link Application}.
     */
    public static void logout() {
        try {
            Access.setLogout(SingletonEmployee.getInstance().getLoggedEmployee().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
