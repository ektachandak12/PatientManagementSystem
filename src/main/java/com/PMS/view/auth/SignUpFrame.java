package com.PMS.view.auth;

import com.PMS.controller.authController.SignUpController;

import javax.swing.*;
import java.awt.*;

/*
 * SignUpFrame:
 * This class creates the signup window for the Patient Management System.
 * It contains UI components required for user registration.
 */
public class SignUpFrame extends JFrame {

    // Labels for heading and input fields
    private JLabel heading, username, password;

    // Input fields for username and password
    private JTextField usernameField;
    private JPasswordField passwordField;

    // Buttons for different actions
    private JButton signupBtn, clearBtn;

    /*
     * Constructor:
     * Calls initialize() to set up the complete UI.
     */
    public SignUpFrame() {
        initialize();
    }

    /*
     * initialize():
     * Configures the JFrame and adds all UI components.
     */
    public void initialize() {

        // JFrame basic settings
        setTitle("Sign Up");
        setSize(450, 350);
        setLocationRelativeTo(null); // Centers frame on screen
        setLayout(null); // Using absolute positioning
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main heading
        heading = new JLabel("SIGN UP");
        heading.setFont(new Font("Osward", Font.BOLD, 25));
        heading.setBounds(170, 20, 150, 30);
        heading.setForeground(Color.BLACK);
        add(heading);

        // Username label
        username = new JLabel("Username:");
        username.setFont(new Font("Raleway", Font.BOLD, 20));
        username.setBounds(40, 80, 120, 30);
        username.setForeground(Color.BLACK);
        add(username);

        // Username input field
        usernameField = new JTextField();
        usernameField.setFont(new Font("Raleway", Font.BOLD, 20));
        usernameField.setBounds(180, 80, 200, 30);
        add(usernameField);

        // Password label
        password = new JLabel("Password:");
        password.setFont(new Font("Raleway", Font.BOLD, 20));
        password.setBounds(40, 130, 120, 30);
        password.setForeground(Color.BLACK);
        add(password);

        // Password input field
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Raleway", Font.BOLD, 20));
        passwordField.setBounds(180, 130, 200, 30);
        passwordField.setForeground(Color.BLACK);
        add(passwordField);

        // Button to clear all input fields
        clearBtn = new JButton("CLEAR");
        clearBtn.setFont(new Font("Raleway", Font.PLAIN, 15));
        clearBtn.setBackground(Color.BLACK);
        clearBtn.setForeground(Color.WHITE);
        clearBtn.setBounds(50, 190, 150, 30);
        add(clearBtn);

        // Button to perform signup/registration operation
        signupBtn = new JButton("SIGN UP");
        signupBtn.setFont(new Font("Raleway", Font.PLAIN, 15));
        signupBtn.setBackground(Color.BLACK);
        signupBtn.setForeground(Color.WHITE);
        signupBtn.setBounds(210, 190, 150, 30);
        add(signupBtn);

        // Makes the frame visible
        setVisible(true);

    }

    /*
     * Getter method for Username field.
     * Used by controller to access entered username.
     */
    public JTextField getUsernameField() {
        return usernameField;
    }

    /*
     * Getter method for Password field.
     * Used by controller to access entered password.
     */
    public JPasswordField getPasswordField() {
        return passwordField;
    }

    /*
     * Getter method for Signup button.
     * Used by controller to attach ActionListener.
     */
    public JButton getSignupBtn() {
        return signupBtn;
    }

    /*
     * Getter method for Clear button.
     * Used by controller to attach ActionListener.
     */
    public JButton getClearBtn() {
        return clearBtn;
    }

}