package com.codecool.shop.dtbManager;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DatabaseInfo {
    private String username;
    private String password;
    private String dbsName;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public String getDbsName() {
        return dbsName;
    }


    public DatabaseInfo() {
        try {
            Scanner scanner = new Scanner(new File("src/main/java/com/codecool/shop/dtbManager/dbsInfo.txt"));
            int counter = 1;

            String line = scanner.nextLine();
            while (counter !=4) {
                if (counter == 1) {
                    dbsName = line;
                } else if (counter == 2) {
                    username = line;
                } else if (counter == 3) {
                    password = line;
                    break;
                }
                counter++;
                line = scanner.nextLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
