package com.codecool.shop.dao.implementation;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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
    // TODO: egy v több elem remove, if, boolean
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

    @Override
    public HashMap<Product, Integer> countProducts() {
        HashMap<Product, Integer> productNumber = new HashMap<>();
        for(Product p : productCartList) {
            if(productNumber.containsKey(p)) {
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

    /*@Override
    public HashMap<Product, Double> CountProductsByEachPrice() {
        HashMap<Product, Double> MoneyByProducts = new HashMap<>();
        HashMap<Product, Integer> mapForMoney = this.countProducts();
        for (Map.Entry<Product, Integer> pair: mapForMoney.entrySet()){

            MoneyByProducts.put(pair.getKey(), Double.parseDouble(pair.getKey().getPrice().replace("USD", "")) * pair.getValue());
        }
        System.out.println(MoneyByProducts.toString());

        return MoneyByProducts;
    }*/

    @Override
    public Double CountProductByAll() {
        Double money_cost = 0.0;
        HashMap<Product, Integer> mapForMoney = this.countProducts();
        for (Map.Entry<Product, Integer> pair: mapForMoney.entrySet()){

            money_cost += Double.parseDouble(pair.getKey().getPrice().replace("USD", "")) * pair.getValue();
        }
        return money_cost;
    }

}
