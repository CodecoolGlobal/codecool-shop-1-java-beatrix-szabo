package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
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

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    private boolean error = false;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException{
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, rep, req.getServletContext());

        //context.setVariable();
        engine.process("product/login.html", context, rep.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        AllUser alluser = AllUser.getInstance();

        HttpSession session = req.getSession();
        for(User u : alluser.getAllUser()) {
            if(u.getEmail().equals(email) && u.getPassword().equals(password)) {
                session.setAttribute("loggedin", email);
                error = false;
                rep.sendRedirect(req.getContextPath() + "/");
            } else {
                error = true;
                rep.sendRedirect(req.getContextPath() + "/login");
            }
        }
    }
}
