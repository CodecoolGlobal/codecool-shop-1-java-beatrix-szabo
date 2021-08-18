package com.codecool.shop.controller;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Properties;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;

public class EmailSend {
    private String useremail;
    private ArrayList dataList;
    CartDao cart = CartDaoMem.getInstance();

    public EmailSend(String email, ArrayList dataList) {
        this.useremail = email;
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
                    InternetAddress.parse(useremail)
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
            writeToFile();

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    //Writing to txt file
    private static FileWriter file;
    private void writeToFile(){
        String json = new Gson().toJson(dataList);
        try {

            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter("D:/projects/OOP-JAVA/week5/codecool-shop-1-java-beatrix-szabo/src/main/webapp/static/txt/confirmation.txt");
            file.write(json);

        } catch (Exception e) {
            e.printStackTrace();

        } finally {

            try {
                file.flush();
                file.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
