package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.ProductService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        session.setAttribute("category", req.getParameter("chose_category"));
        session.setAttribute("supplier", req.getParameter("chose_supplier"));

        //user is logged in or not


        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore, supplierDataStore);
        SupplierDao suppliers = SupplierDaoMem.getInstance();


        CartDao cart = CartDaoMem.getInstance();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());


        boolean loggedIn = false;
        String username = null;
        if (session.getAttribute("loggedin") != null) {
            loggedIn = true;
            for(User u : AllUser.getInstance().getAllUser()) {
                if (u.getEmail().equals(session.getAttribute("loggedin"))) {
                    username = u.getName();
                    break;
                }
            }

        }


        context.setVariable("username", "Logged in as" + username) ;
        context.setVariable("loggedin", loggedIn);
        context.setVariable("Cart", cart);
        context.setVariable("allCategory", getAllCategory(productDataStore));
        context.setVariable("allSupplier", getAllSupplier(suppliers));


        if(session.getAttribute("category") != null && session.getAttribute("supplier") != null) {
            if (session.getAttribute("supplier").equals("all") || session.getAttribute("category").equals("all")) {
                session.removeAttribute("supplier");
                session.removeAttribute("category");
            }
        }

        if(session.getAttribute("category") == null && session.getAttribute("supplier") == null) {
            context.setVariable("products", productDataStore.getAll());
        } else if (session.getAttribute("supplier") != null && session.getAttribute("category").equals("base")) {
            context.setVariable("products", productService.getProductsForSupplier(Integer.parseInt(String.valueOf(session.getAttribute("supplier")))));
        } else if (session.getAttribute("category") != null && session.getAttribute("supplier").equals("base")){
            context.setVariable("products", productService.getProductsForCategory(Integer.parseInt(String.valueOf(session.getAttribute("category")))));
        }

        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);
        engine.process("product/index.html", context, resp.getWriter());
    }

    private ArrayList<ProductCategory> getAllCategory(ProductDao products) {
        ArrayList<ProductCategory> allCategory = new ArrayList<>();
        for (Product p : products.getAll() ){
            if(allCategory.isEmpty() || !allCategory.contains(p.getProductCategory())){
                allCategory.add(p.getProductCategory());
            }
        }
        return allCategory;
    }

    private ArrayList<Supplier> getAllSupplier(SupplierDao suppliers) {
        ArrayList<Supplier> allSupplier = new ArrayList<>();
        for(Supplier s : suppliers.getAll()) {
            if(allSupplier.isEmpty() || !allSupplier.contains(s)){
                allSupplier.add(s);
            }
        }
        return allSupplier;
    }

}

