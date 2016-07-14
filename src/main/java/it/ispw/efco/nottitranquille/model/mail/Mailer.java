package it.ispw.efco.nottitranquille.model.mail;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * Provide service to send mail with authentication
 *
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class Mailer {

    private String username = "nottitranquile@gmail.com";
    private String password = "persistencesql";

    public Mailer() {
    }

    public Mailer(String username, String password) {
        this.username = username;
        this.password = password;
    }


    /**
     * @param dest: receiver's email address
     * @param object: object of the message
     * @param text: body
     * @param filename: optional path for a filename to attach
     */
    public void send(String dest, String object, String text, String filename) {

        Session session = setProperties();

        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(username)); //set address sender
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(dest));   // set address destination

            message.setSubject(object); // object

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(text);                    // message body
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);


            // attach a file
            if (filename != null) {
                messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(filename);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(filename);
                multipart.addBodyPart(messageBodyPart);
            }

            message.setContent(multipart);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendHtml( String dest, String object, String html) {

        Session session = setProperties();

        try {
            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setContent(html,"text/html");
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(mbp);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(dest));
            message.setSubject(object);
            message.setContent(multipart);
            message.setText(html, "UTF-8", "html");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * set properties for mail-server connection enabling authentication and setting
     * other parameters.
     *
     * @return Session
     */
    private Session setProperties() {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");   // enabling authentication
        props.put("mail.smtp.starttls.enable", "true"); // starting tls connection
        props.put("mail.smtp.host", "smtp.gmail.com");  // set mail server
        props.put("mail.smtp.port", "587"); // set mail port

        // set properties and authentication
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        return session;
    }

}
