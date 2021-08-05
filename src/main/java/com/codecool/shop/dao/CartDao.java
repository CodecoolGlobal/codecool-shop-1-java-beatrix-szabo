package com.codecool.shop.dao;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;

import java.util.ArrayList;

public interface CartDao {
    void add(Product product);
    void remove(Product product);
    ArrayList<Product> getAll();
}
