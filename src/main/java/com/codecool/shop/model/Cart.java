package com.codecool.shop.model;

import com.codecool.shop.model.Product;
import java.util.ArrayList;

public class Cart {

    public ArrayList<Product> getProductCartList() {
        return productCartList;
    }


    public void add(Product product){
        this.productCartList.add(product);
    }

    private ArrayList<Product> productCartList = new ArrayList<>();

}
