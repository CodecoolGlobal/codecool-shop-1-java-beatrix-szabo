package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/add-cart/*"})
public class NewProductInTheCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
        rep.sendRedirect(req.getContextPath() + "/");
        String uri = req.getRequestURI();
        String productName = uri.substring(10).replace("%20", " ");

        CartDao cart = CartDaoMem.getInstance();
        ProductDao productDataStore = ProductDaoMem.getInstance();

        cart.add(getProductObject(productName, productDataStore.getAll()));
    }

    private Product getProductObject(String Name, List<Product> AllProduct) {
        for (Product i : AllProduct) {
            if (i.getName().equals(Name)){
                return i;
            }
        }
        return AllProduct.get(0);
    }

}
