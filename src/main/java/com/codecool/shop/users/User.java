package com.codecool.shop.users;

public class User extends AdminUser{
    public User(String name, String password, String email) {
        super(password, email);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    //-----------------------------------------------Private
    //-----------------------------------------Fields
    private String name;
    private String password = "";
    private String email = "";
}
