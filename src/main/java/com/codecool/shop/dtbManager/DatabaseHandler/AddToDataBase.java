package com.codecool.shop.dtbManager.DatabaseHandler;

import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dtbManager.ConnectDatabase;
import com.codecool.shop.model.Product;
import com.codecool.shop.users.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddToDataBase {
    private static AddToDataBase instance;

    private AddToDataBase() throws SQLException {
    }

    public static AddToDataBase getInstance() throws SQLException {
        if (instance == null) {
            instance = new AddToDataBase();
        }
        return instance;
    }

    public void addToCart(Product p) throws SQLException {
        String sql = "INSERT INTO cart (product_id) VALUES (" + p.getId() + ")";
        conn.createStatement().executeUpdate(sql);

    }

    public void deleteFromCart(Product p) throws SQLException {
        String sql = "DELETE FROM cart WHERE id = "+ p.getId();
        conn.createStatement().executeUpdate(sql);
    }

    public void addNewUser(User user) throws SQLException {
        String sql = new StringBuilder().append("INSERT INTO users (username, email, user_password) VALUES('").append(user.getName()).append("', '").append(user.getEmail()).append("', '").append(user.getPassword()).append("')").toString();
        conn.createStatement().executeUpdate(sql);
    }

    DataSource dataSource = ConnectDatabase.connect();
    Connection conn = dataSource.getConnection();
}
