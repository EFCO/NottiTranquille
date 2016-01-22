package it.ispw.efco.nottitranquille.model;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public abstract class Subject {

    boolean hasChanged;

    public boolean hasChanged(){
        return hasChanged;
    }

    public abstract void notifyObserver();

}
