package com.codecool.shop.dao;
import java.util.HashMap;

import com.codecool.shop.model.Product;

import java.util.ArrayList;

public interface CartDao {
    void add(Product product);
    void remove(Product product);
    ArrayList<Product> getAll();
    HashMap<Product, Integer> countProducts();
    //HashMap<Product, Double> CountProductsByEachPrice();
    Double CountProductByAll();

}
