package it.ispw.efco.nottitranquille.model;

import org.joda.time.DateTime;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class Notification {

    String type;

    String message;

    Object data;

    DateTime timestamp;

    public Notification(String type) {
        this.type = type;

        timestamp= new DateTime();
    }

    /* SETTER AND GETTER */

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(DateTime timestamp) {
        this.timestamp = timestamp;
    }
}
