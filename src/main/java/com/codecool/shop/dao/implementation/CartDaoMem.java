package com.codecool.shop.dao.implementation;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import java.util.ArrayList;

public class CartDaoMem implements CartDao {

    private ArrayList<Product> productCartList = new ArrayList<>();
    private static CartDaoMem instance = null;

    private CartDaoMem(){};

    public static CartDao getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        this.productCartList.add(product);
    }

    @Override
    public void remove(Product product) {
        for (Product prod: productCartList ){
            if (prod.getName().equals(product.getName())){
                productCartList.remove(prod);
                break;
            }
        }
    }

    @Override
    public ArrayList<Product> getAll(){
        return productCartList;
    }
}
