package com.codecool.shop.dao;
import java.util.HashMap;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.HashMap;

public interface CartDao {
    void add(Product product);
    void remove(Product product);
    ArrayList<Product> getAll();
    public HashMap countProducts();
}
