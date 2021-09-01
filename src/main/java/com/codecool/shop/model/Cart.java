package com.codecool.shop.model;

import com.codecool.shop.model.Product;
import java.util.ArrayList;

public class Cart {

    public static Cart getInstance() {
        if(instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public ArrayList<Product> getProductCartList() {
        return productCartList;
    }


    public void add(Product product){
        this.productCartList.add(product);
    }

    private ArrayList<Product> productCartList = new ArrayList<>();
    private static Cart instance;

}
