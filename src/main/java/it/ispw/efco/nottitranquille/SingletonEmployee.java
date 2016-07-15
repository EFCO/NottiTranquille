package it.ispw.efco.nottitranquille;

import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.view.LoginBean;

/**
 *
 */
public class SingletonEmployee {

    /**
     *
     */
    private Person employee;

    /**
     *
     */
    private LoginBean loggedEmployee;

    /**
     *
     */
    private static class Container {
        private final static SingletonEmployee singletonInstance = new SingletonEmployee();
    }

    /**
     *
     * @return
     */
    public Person getEmployee() {
        return employee;
    }

    /**
     *
     * @param employee
     */
    public void setEmployee(Person employee) {
        this.employee = employee;
    }

    /**
     *
     * @return
     */
    public LoginBean getLoggedEmployee() {
        return loggedEmployee;
    }

    /**
     *
     * @param loggedEmployee
     */
    public void setLoggedEmployee(LoginBean loggedEmployee) {
        this.loggedEmployee = loggedEmployee;
    }

    /**
     *
     * @return
     */
    public static SingletonEmployee getInstance() {
        return Container.singletonInstance;
    }
}