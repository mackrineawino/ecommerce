package com.ecom.utils;

import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Stateless
public class EmailService {

    public void sendOrderConfirmationEmail(String userEmail, String orderNumber) {
        String to = userEmail;
        String from = "coolstuffcorp758@gmail.com";
        final String username = "coolstuffcorp758@gmail.com";
        final String password = "gwso fces chyh enuy";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                System.out.println("Username: " + username);
                System.out.println("Password: " + password );
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Order Confirmation - Order Number: " + orderNumber);
            message.setText("Thank you for placing this order. Your order with Order Number " + orderNumber
                    + " has been confirmed.");

            Transport.send(message);

            System.out.println("Order confirmation email sent successfully!");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
