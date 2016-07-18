package it.ispw.efco.nottitranquille;

import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.model.Role;
import it.ispw.efco.nottitranquille.view.LoginBean;

/**
 * Singleton for Employee ({@link Role}) for JavaFX {@link javafx.application.Application}.
 *
 * @see Role
 * @see Person
 * @see LoginBean
 */
public class SingletonEmployee {

    /**
     * The employee.
     */
    private Person employee;

    /**
     * The LoginBean bound to the employee.
     */
    private LoginBean loggedEmployee;

    /**
     * Lazy singleton container.
     */
    private static class Container {
        private final static SingletonEmployee singletonInstance = new SingletonEmployee();
    }

    /**
     * Gets employee.
     *
     * @return the employee
     */
    public Person getEmployee() {
        return employee;
    }

    /**
     * Sets employee.
     *
     * @param employee the employee to set.
     */
    public void setEmployee(Person employee) {
        this.employee = employee;
    }

    /**
     * Gets the LoginBean.
     *
     * @return the LoginBean
     */
    public LoginBean getLoggedEmployee() {
        return loggedEmployee;
    }

    /**
     * Sets the LoginBean.
     *
     * @param loggedEmployee the LoginBean to set
     */
    public void setLoggedEmployee(LoginBean loggedEmployee) {
        this.loggedEmployee = loggedEmployee;
    }

    /**
     * Gets singleton instance.
     *
     * @return the singleton instance
     */
    public static SingletonEmployee getInstance() {
        return Container.singletonInstance;
    }
}