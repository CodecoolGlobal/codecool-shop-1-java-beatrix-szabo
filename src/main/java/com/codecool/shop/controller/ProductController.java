package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore);
        SupplierDao suppliers = SupplierDaoMem.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        //context.setVariable("category", productService.getProductCategory(1));
        context.setVariable("allCategory", getAllCategory(productDataStore));
        //context.setVariable("products", productService.getProductsForCategory(1));
        context.setVariable("allProducts", productDataStore.getAll());
        context.setVariable("allSupplier", getAllSupplier(suppliers));
        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);
        engine.process("product/index.html", context, resp.getWriter());
    }

    private ArrayList<String> getAllCategory(ProductDao products) {
        ArrayList<String> allCategory = new ArrayList<>();
        for (Product p : products.getAll() ){
            if(allCategory.isEmpty() || !allCategory.contains(p.getProductCategory().getName())){
                allCategory.add(p.getProductCategory().getName());
            }
        }
        return allCategory;
    }

    private ArrayList<String> getAllSupplier(SupplierDao suppliers) {
        ArrayList<String> allSupplier = new ArrayList<>();
        for(Supplier s : suppliers.getAll()) {
            if(allSupplier.isEmpty() || !allSupplier.contains(s.getName())){
                allSupplier.add(s.getName());
            }
        }
        return allSupplier;
    }
}
