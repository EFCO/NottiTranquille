package it.ispw.efco.nottitranquille;

import it.ispw.efco.nottitranquille.model.mail.Mailer;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class DemoMail {

    public static void main(String args[]) {

        Mailer mail = new Mailer();

        mail.send("ema.nick94@gmail.com", "prova", "messaggio privato 3", null);
    }
}