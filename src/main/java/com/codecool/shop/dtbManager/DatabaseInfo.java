package com.codecool.shop.dtbManager;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DatabaseInfo {
    private String username;
    private String password;
    private String dbsName;

    public String getUsername() {
        System.out.println(username);
        return username;
    }

    public String getPassword() {
        System.out.println(password);
        return password;
    }


    public String getDbsName() {
        System.out.println(dbsName);
        return dbsName;
    }


    public DatabaseInfo() {
        try {
            Scanner scanner = new Scanner(new File("src/main/java/com/codecool/shop/dtbManager/dbsInfo.txt"));
            int counter = 1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                while (scanner.hasNextLine()) {
                    if (counter == 1) {
                        dbsName = line;
                    } else if (counter == 2) {
                        username = line;
                    } else if (counter == 3) {
                        password = line;
                        break;
                    }
                    counter++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
