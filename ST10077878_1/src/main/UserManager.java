/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author maxju
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static final String USER_DATA_FILE = "userdata.txt";

    public static List<User> readUserData() {
        List<User> userList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_FILE))) {
            String line;
            User user = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Username: ")) {
                    if (user != null) {
                        userList.add(user);
                    }
                    user = new User();
                    user.setUsername(line.substring("Username: ".length()));
                } else if (line.startsWith("Password: ")) {
                    user.setPassword(line.substring("Password: ".length()));
                } else if (line.startsWith("First Name: ")) {
                    user.setFirstName(line.substring("First Name: ".length()));
                } else if (line.startsWith("Last Name: ")) {
                    user.setLastName(line.substring("Last Name: ".length()));
                }
            }

            // Add the last user
            if (user != null) {
                userList.add(user);
            }

            System.out.println("User data loaded successfully.");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("An error occurred while reading user data.");
        }

        return userList;
    }

    public static void saveUserData(User user) {
        try (FileWriter fileWriter = new FileWriter(USER_DATA_FILE, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println("Username: " + user.getUsername());
            printWriter.println("Password: " + user.getPassword());
            printWriter.println("First Name: " + user.getFirstName());
            printWriter.println("Last Name: " + user.getLastName());
            printWriter.println();

            System.out.println("User data saved successfully.");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("An error occurred while saving user data.");
        }
    }
}
    

