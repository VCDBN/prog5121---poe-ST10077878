
package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.io.*;
import java.util.regex.Pattern;
import javax.swing.Timer;

public class Login extends JFrame implements ActionListener {

    private static JLabel label;
    private static JTextField userText;
    private static JLabel label2;
    private static JPasswordField passwordField;
    private static JButton loginButton;

    private String username;
    private String password;

    public Login() {
        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel();
        this.add(panel);
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Login Form");

        this.setLocationRelativeTo(null);
        this.setResizable(false);
        panel.setLayout(null);

        label = new JLabel("Username:");
        label.setBounds(20, 80, 100, 20);
        panel.add(label);

        userText = new JTextField(20);
        userText.setBounds(90, 80, 120, 20);
        panel.add(userText);

        label2 = new JLabel("Password:");
        label2.setBounds(20, 140, 100, 20);
        panel.add(label2);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(90, 140, 120, 20);
        panel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        loginButton.setBounds(90, 200, 120, 20);
        panel.add(loginButton);

        panel.setVisible(true);
        this.setVisible(true);
    }

    @Override
public void actionPerformed(ActionEvent e) {
    String username = userText.getText();
    String password = String.valueOf(passwordField.getPassword());

    if (loginUser(username, password)) {
        String fullName = getFullName(username);
        if (JOptionPane.showConfirmDialog(null, "Welcome " + fullName + ", it is great to see you again. Opening tasks...", "Success", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.OK_OPTION) {
            JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");

        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Kanban kanban = new Kanban();
                 kanban.setVisible(true);
                // Close the login form:
                dispose();
            }
        });
        timer.setRepeats(false); // So the action is only performed once
        timer.start(); // Start the timer
    } else {
        JOptionPane.showMessageDialog(null, "Username or password incorrect, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

}
    // Method to check if the username exists
    public static boolean checkUserName(String username) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("userdata.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals("Username: " + username)) {
                    reader.close();
                    return true;
                }
                // Skip the next 3 lines
                for(int i = 0; i < 3; i++) reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to check password complexity
    public static boolean checkPasswordComplexity(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
        return pattern.matcher(password).matches();
    }

    // Method to login a user
    public static boolean loginUser(String username, String password) {
    try {
        BufferedReader reader = new BufferedReader(new FileReader("userdata.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println("Read line: " + line); // Print the line being read
            if (line.equals("Username: " + username)) {
                String passwordLine = reader.readLine(); // Store the password line
                System.out.println("Read password line: " + passwordLine); // Print the password line
                if(passwordLine.equals("Password: " + password)) {
                    reader.close();
                    return true;
                }
            }
            // Skip the next 2 lines if it's not the correct user
            for(int i = 0; i < 2; i++) reader.readLine();
        }
        reader.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return false;
}

    // Method to retrieve the full name of a user
  // Method to retrieve the full name of a user
public static String getFullName(String username) {
    try {
        BufferedReader reader = new BufferedReader(new FileReader("userdata.txt"));
        String line;
        String savedUsername = "";
        String firstName = "";
        String lastName = "";

        while ((line = reader.readLine()) != null) {
            if (line.startsWith("Username: ")) {
                savedUsername = line.split(": ")[1];
            } else if (line.startsWith("Password: ")) {
                // Skip the password line
            } else if (line.startsWith("First Name: ")) {
                firstName = line.split(": ")[1];
            } else if (line.startsWith("Last Name: ")) {
                lastName = line.split(": ")[1];
            }

            // If a complete record is found for the user
            if (savedUsername.equals(username) && !firstName.isEmpty() && !lastName.isEmpty()) {
                reader.close();
                return firstName + " " + lastName;
            }
        }
        reader.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return "User not found";
}


    public static String returnLoginStatus(String username, String password){
        if (loginUser(username, password)) {
            return "Successfully logged in";
        } else {
            return "Invalid username or password";
        }
    }
}

