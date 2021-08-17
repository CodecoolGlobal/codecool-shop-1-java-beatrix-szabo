package com.codecool.shop.users;

import java.util.ArrayList;
import java.util.List;

public class AllUser {
    private static AllUser instance;
private AdminUser admins = AdminUser.getInstance();
    private List<User> allUser = new ArrayList<User>();

    public static AllUser getInstance() {
        if(instance == null) {
            instance = new AllUser();
        }
        return instance;
    }

    public AdminUser getAdmins() {
        return admins;
    }

    public List<User> getAllUser() {
        return allUser;
    }

    public void addUser(User user) {
        this.allUser.add(user);
    }
}
