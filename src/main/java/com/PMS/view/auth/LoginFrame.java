package com.PMS.view.auth;

import javax.swing.*;
import java.awt.*;


public class LoginFrame extends JFrame{

    JLabel  heading, username, password;
    public JTextField usernameField;
    public JPasswordField passwordField;
    JButton  signupBtn, clearBtn, loginBtn;

    public LoginFrame() {
        initialize();
    }

    private void initialize() {
        new JFrame();
        setTitle("Patient Management System");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(Color.white);

        heading = new JLabel("Patient Management System");
        heading.setFont(new Font("Osward", Font.BOLD, 25));
        heading.setBounds(75, 20, 350, 30);
        heading.setForeground(Color.BLACK);
        add(heading);

        username = new JLabel("Username:");
        username.setFont(new Font("Raleway", Font.BOLD, 20));
        username.setBounds(50, 80, 150, 30);
        username.setForeground(Color.BLACK);
        add(username);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Raleway", Font.BOLD, 20));
        usernameField.setBounds(200, 80, 200, 30);
        usernameField.setFont(new java.awt.Font("Raleway", Font.BOLD, 20));
        add(usernameField);

        password = new JLabel("Password:");
        password.setFont(new Font("Raleway", Font.BOLD, 20));
        password.setBounds(50, 130, 150, 30);
        password.setForeground(Color.BLACK);
        add(password);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Raleway", Font.BOLD, 20));
        passwordField.setBounds(200, 130, 200, 30);
        passwordField.setForeground(Color.BLACK);
        add(passwordField);

        clearBtn = new JButton("CLEAR");
        clearBtn.setFont(new Font("Raleway", Font.PLAIN, 15));
        clearBtn.setBackground(Color.BLACK);
        clearBtn.setForeground(Color.WHITE);
        clearBtn.setBounds(75, 180, 150, 30);
        add(clearBtn);

        signupBtn = new JButton("SIGN UP");
        signupBtn.setFont(new Font("Raleway", Font.PLAIN, 15));
        signupBtn.setBackground(Color.BLACK);
        signupBtn.setForeground(Color.WHITE);
        signupBtn.setBounds(245, 180, 150, 30);
        add(signupBtn);

        loginBtn = new JButton("LOGIN");
        loginBtn.setFont(new Font("Raleway", Font.PLAIN, 15));
        loginBtn.setBackground(Color.BLACK);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setBounds(75, 220, 320, 30);
        add(loginBtn);

        setVisible(true);
    }

    public JButton getLoginBtn() {
        return loginBtn;
    }

    public JButton getSignupBtn() {
        return signupBtn;
    }

    public JButton getClearBtn() {
        return clearBtn;
    }

}
