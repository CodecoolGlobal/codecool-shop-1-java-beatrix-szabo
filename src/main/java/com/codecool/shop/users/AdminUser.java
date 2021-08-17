package com.codecool.shop.users;

import javax.swing.*;

public class AdminUser {

     AdminUser(String password, String email) {
        this.password = password;
        this.email = email;
    }

    private AdminUser() {
        password = "aaa";
        email = "super@user.com";
    }

    public static AdminUser getInstance() {
        if(instance == null) {
            instance = new AdminUser();
        }
        return instance;
    }

    //----------------------------------------Getters
    public String[] getAdminsName() {
        return AdminsName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    //-----------------------------------------------Private
    //-----------------------------------------Fields
    private String[] AdminsName = {"Roland", "Krisz", "Betti"};
    private String password;
    private String email;
    private static AdminUser instance;
}
