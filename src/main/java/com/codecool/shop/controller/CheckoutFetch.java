package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

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
        //rep.sendRedirect(req.getContextPath() + "/");
        ArrayList dataList = new ArrayList();
        String fname = req.getParameter("firstname");
        String email = req.getParameter("email");
        String adr = req.getParameter("address");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String zip = req.getParameter("zip");
        dataList.add(fname);
        dataList.add(adr);
        dataList.add(city);
        dataList.add(state);
        dataList.add(zip);
        String cardName = req.getParameter("cardname");
        String cnum = req.getParameter("cardnumber");
        String expmonth = req.getParameter("expmonth");
        String expyear = req.getParameter("expyear");
        String cvv = req.getParameter("cvv");
        String orderNum = orderNumber();
        dataList.add(cardName);
        dataList.add(cnum);
        dataList.add(expmonth);
        dataList.add(expyear);
        dataList.add(cvv);
        dataList.add(orderNum);
        dataList.add(Date());
        dataList.add(email);
        EmailSend emailToSend = new EmailSend(email, dataList);
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, rep, req.getServletContext());
        CartDao cart = CartDaoMem.getInstance();
        context.setVariable("cart", cart);
        context.setVariable("emailToSend", emailToSend);
        context.setVariable("datalist", dataList);
        engine.process("product/OrderDetail.html", context, rep.getWriter());

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

