package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.users.AdminUser;
import com.codecool.shop.users.AllUser;
import com.codecool.shop.users.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import com.codecool.shop.users.User;

@WebServlet(urlPatterns = {"/registration"})
public class RegistrationController extends HttpServlet {
    //TODO if email is registered, dont let it register again!
    static boolean registeredEmail = false;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, rep, req.getServletContext());
        context.setVariable("registeredEmail", registeredEmail);
        engine.process("product/registration.html", context, rep.getWriter());
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
        AllUser alluser = AllUser.getInstance();
        HttpSession session = req.getSession();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        for (User u : alluser.getAllUser()) {
            if (u.getEmail().equals(email)) {
                registeredEmail = true;
                rep.sendRedirect(req.getContextPath() + "/registration");
                rep.sendRedirect(req.getContextPath() + "/registration");
            }
        }
        alluser.addUser(new User(username, password, email));
        session.setAttribute("loggedin", email);
        registeredEmail = false;
        rep.sendRedirect(req.getContextPath() + "/");
    }
}
