package com.codecool.shop.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@WebServlet(urlPatterns = "/checkout")
public class CheckoutFetch extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
        rep.sendRedirect(req.getContextPath() + "/");
        ArrayList dataList = new ArrayList();
        String fname = req.getParameter("fname");
        String email = req.getParameter("email");
        String adr = req.getParameter("adr");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String zip = req.getParameter("zip");
        String cardName = req.getParameter("cardname");
        String cnum = req.getParameter("cardnumber");
        String expmonth = req.getParameter("expmonth");
        String expyear = req.getParameter("expyear");
        String cvv = req.getParameter("cvv");
        String orderNum = orderNumber();
        dataList.add(fname);
        dataList.add(adr);
        dataList.add(city);
        dataList.add(state);
        dataList.add(zip);
        dataList.add(cardName);
        dataList.add(cnum);
        dataList.add(expmonth);
        dataList.add(expyear);
        dataList.add(cvv);
        dataList.add(orderNum);
        dataList.add(Date());
        dataList.add(email);
        EmailSend emailToSend = new EmailSend(email, dataList);
        emailToSend.emailSend();
    }
    protected String Date(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return String.valueOf(dtf.format(now));
    }

    protected String orderNumber(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        return "CCS-"+String.valueOf(dtf.format(now));
    }
}

