package com.codecool.shop.controller;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class EmailSend {
    private String userenamail;
    private ArrayList dataList;
    CartDao cart = CartDaoMem.getInstance();

    public EmailSend(String email, ArrayList dataList) {
        this.userenamail = email;
        this.dataList = dataList;
    }

    public void emailSend() {

        final String username = "insaniteam1@gmail.com";
        final String password = "Asdasd1234";


        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(userenamail)
            );
            message.setSubject("Order from CodeCool shop");
            message.setText("Thanks for your order, "+dataList.get(0) +"\n"+
            "Your order Number: " + dataList.get(10)+"\n"+
            "You orderd: "+"\n"+
            "Date of order: " + dataList.get(11)+"\n"+
            "Your card number: " + dataList.get(6)+"\n"+
            "Hope you like our shop");


            Transport.send(message);

            cart.clear();

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}
