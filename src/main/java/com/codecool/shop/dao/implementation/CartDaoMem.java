package com.codecool.shop.dao.implementation;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import java.util.ArrayList;
import java.util.HashMap;


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


    public HashMap countProducts() {
        HashMap<Product, Integer> productNumber = new HashMap();
        for(Product p : productCartList) {
            if(productNumber.containsKey(p.getName())) {
                int prodNum;
                prodNum = productNumber.get(p);
                prodNum += 1;
                productNumber.put(p, prodNum);
            }
            else {
                productNumber.put(p, 1);
            }
        }
        return productNumber;
    }
}
