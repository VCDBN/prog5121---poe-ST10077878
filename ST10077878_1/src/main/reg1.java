package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class reg1 extends JFrame {

    private static JLabel label;
    private static JTextField userText;
    private static JLabel label1;
    private static JTextField userText1;
    private static JLabel label2;
    private static JTextField userText2;
    private static JLabel label3;
    private static JPasswordField passwordField;
    private static JButton registerButton;
    private static JButton loginformButton;

    public static void main(String[] args) {
        //GUI information 
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Registration Form");
    
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        panel.setLayout(null);
    
        label = new JLabel("Firstname:");
        label.setBounds(20,80, 100, 20);
        panel.add(label);
    
        userText = new JTextField(20);
        userText.setBounds(90,80, 120, 20);
        panel.add(userText);
        
        label1 = new JLabel("Surname:");
        label1.setBounds(20, 120, 100, 20);
        panel.add(label1);
    
        userText1 = new JTextField(20);
        userText1.setBounds(90, 120, 120, 20);
        panel.add(userText1);
   
        label2 = new JLabel("Username:");
        label2.setBounds(20, 160, 100, 20);
        panel.add(label2);
    
        userText2 = new JTextField(20);
        userText2.setBounds(90, 160, 120, 20);
        panel.add(userText2);
    
        label3 = new JLabel("Password:");
        label3.setBounds(20, 200, 100, 20);
        panel.add(label3);
    
        passwordField = new JPasswordField(20);
        passwordField.setBounds(90, 200, 120, 20);
        panel.add(passwordField);
    
        registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        registerButton.setBounds(50, 240, 120, 20);
        panel.add(registerButton);
        //button for user to go to login page
        loginformButton = new JButton("Click To Login");
        loginformButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToLogin();
            }
        });
        loginformButton.setBounds(50, 300, 120, 20);
        panel.add(loginformButton);
    
        panel.setVisible(true);
        frame.setVisible(true);
    }
    
    private static void registerUser() {
        String username = userText2.getText();
        String password = passwordField.getText();
        String firstName = userText.getText();
        String lastName = userText1.getText();
    
        boolean isUsernameValid = username.matches("^\\w{1,5}$");
        boolean isPasswordValid = password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-])[A-Za-z\\d!@#$%^&*()_+\\-]{8,}$");
    
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a username and password.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!isUsernameValid) {
            JOptionPane.showMessageDialog(null, "Username is not correctly formatted. Please ensure that your username contains an underscore and is no more than 5 characters in length.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!isPasswordValid) {
            JOptionPane.showMessageDialog(null, "Password is not correctly formatted. Please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            saveUserData(username, password, firstName, lastName);
            System.out.println("Registered username: " + username);
            System.out.println("Registered password: " + password);
            JOptionPane.showMessageDialog(null, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void goToLogin() {
        new Login().setVisible(true);  // Open the Login form
        ((JFrame) SwingUtilities.getWindowAncestor(loginformButton)).dispose(); 
    }
    //saving user credentials to a file
    private static void saveUserData(String username, String password, String firstName, String lastName) {
        try {
            FileWriter fileWriter = new FileWriter("userdata.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println("Username: " + username);
            printWriter.println("Password: " + password);
            printWriter.println("First Name: " + firstName);
            printWriter.println("Last Name: " + lastName);
            printWriter.println();

            printWriter.close();
            fileWriter.close();

            System.out.println("User data saved successfully.");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("An error occurred while saving user data.");
        }
    }
}

    
    
    
    
    
    
    
    
    
    
        
       
            
        
        
        
        
        
   
   
    
    
        
    
