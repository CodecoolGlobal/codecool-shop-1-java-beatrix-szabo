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

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore);
        SupplierDao suppliers = SupplierDaoMem.getInstance();
        CartDao cart = CartDaoMem.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        //default: all products
        context.setVariable("allCategory", getAllCategory(productDataStore));
        context.setVariable("allSupplier", getAllSupplier(suppliers));

        if(session.getAttribute("category") == null ||session.getAttribute("category").equals("0")) {
            context.setVariable("products", productDataStore.getAll());
        } else {
            //context.setVariable("category", productService.getProductCategory());
            context.setVariable("products", productService.getProductsForCategory(Integer.valueOf(String.valueOf(session.getAttribute("category")))));
        }
        context.setVariable("hi", session.getAttribute("category"));
        //context.setVariable("category", productService.getProductCategory(1));
        //context.setVariable("products", productService.getProductsForCategory(1));

        /*context.setVariable("test", session.getAttribute("category"));
        context.setVariable("ketteske", session.getAttribute("supplier"));*/



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

