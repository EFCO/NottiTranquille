package it.ispw.efco.nottitranquille.model;

import java.util.List;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public abstract class Observer {

    List<Subject> subject;

    public abstract void update(Subject subject, Object arg);

}
