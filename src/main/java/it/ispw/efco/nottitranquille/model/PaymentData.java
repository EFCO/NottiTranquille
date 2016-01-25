package it.ispw.efco.nottitranquille.model;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class PaymentData {
    private String intestatario;

    private String number;

    private int code;

    public PaymentData(String intestatario, String number, int code) {
        this.intestatario = intestatario;
        this.number = number;
        this.code = code;
    }

    public String getIntestatario() {
        return intestatario;
    }

    public String getNumber() {
        return number;
    }

    public int getCode() {
        return code;
    }
}
