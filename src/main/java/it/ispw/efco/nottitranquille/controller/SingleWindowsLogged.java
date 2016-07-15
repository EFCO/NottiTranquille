package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.SingletonEmployee;

public class SingleWindowsLogged extends SingleWindow {

    public static void logout() {
        try {
            Access.setLogout(SingletonEmployee.getInstance().getLoggedEmployee().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
