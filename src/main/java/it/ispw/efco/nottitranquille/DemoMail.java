package it.ispw.efco.nottitranquille;

import it.ispw.efco.nottitranquille.model.mail.Mail;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class DemoMail {

    public static void main(String args[]) {

        Mail mail = new Mail();

        mail.send("ema.nick94@gmail.com", "prova", "messaggio privato 3", null);
    }
}