package com.codecool.shop.controller;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSend {
    private String userEmail;
    private String hash;

    public EmailSend(String userEmail, String hash){
        super();
        this.userEmail = userEmail;
        this.hash = hash;
    }
    public void emailSend(){
        String email = ""; // sender email
        String password = ""; // sender password

        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator(){
           protected  PasswordAuthentication getPasswordAuthenticatoin(){
               return new PasswordAuthentication(email, password);
           }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(userEmail)));
            message.setText("Hi there this is auto generated email don't have to answer it!!");
            message.setText("Your order id is #####");
            message.setText("Your payment detail:");
        }catch (Exception e){
            System.out.println("EmailSend file error" + e);
        }
    }
}
