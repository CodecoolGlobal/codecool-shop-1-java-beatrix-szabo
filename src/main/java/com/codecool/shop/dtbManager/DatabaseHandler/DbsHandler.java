package com.codecool.shop.dtbManager.DatabaseHandler;

import com.codecool.shop.model.Product;
import com.codecool.shop.dtbManager.ConnectDatabase;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import jdk.jfr.Category;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbsHandler {
    public DbsHandler() throws SQLException {
        DataSource dataSource = ConnectDatabase.connect();
        Connection conn = dataSource.getConnection();
        setSuppliers(conn, dataSource);
        setCategory(conn,dataSource);
        setProducts(conn,dataSource);
    }

    private void setSuppliers(Connection conn, DataSource dataSource) throws SQLException {
        String sql = "SELECT * FROM supplier";
        ResultSet rs = conn.createStatement().executeQuery(sql);
        while(rs.next()) {
            Supplier s = new Supplier(rs.getString(2), rs.getString(3));
            s.setId(rs.getInt(1));
            suppliers.add(s);
        }
    }

    private void setCategory(Connection conn, DataSource dataSource) throws SQLException {
        String sql = "SELECT * FROM product_category";
        ResultSet rs = conn.createStatement().executeQuery(sql);
        while(rs.next()) {
            ProductCategory pc = new ProductCategory(rs.getString(2),rs.getString(3),rs.getString(3));
            pc.setId(rs.getInt(1));
            productCategories.add(pc);
        }
    }

    private void setProducts(Connection conn, DataSource dataSource) throws SQLException {
        String sql = "SELECT * FROM product";
        ResultSet rs = conn.createStatement().executeQuery(sql);
        while(rs.next()) {
            Product p = new Product(rs.getString(2), rs.getFloat(4), rs.getString(5), rs.getString(3), getCategoryToProduct(rs.getInt(7)),getSupplierToProduct(rs.getInt(6)));
            p.setId(rs.getInt(1));
            System.out.println(p.toString());
            products.add(p);
        }
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public List<ProductCategory> getProductCategories() {
        return productCategories;
    }

    private ProductCategory getCategoryToProduct(int id){
        for(ProductCategory pC : productCategories) {
            if(pC.getId() == id){
                return pC;
            }
        }
        return null;
    }

    private Supplier getSupplierToProduct(int id){
        for(Supplier supp : suppliers) {
            if(supp.getId() == id){
                return supp;
            }
        }
        return null;
    }

    public List<Product> getProducts() {
        return products;
    }

    private List<Supplier> suppliers = new ArrayList<>();
    private List<ProductCategory> productCategories = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
}

