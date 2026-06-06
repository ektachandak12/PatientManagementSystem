package com.PMS.controller.authController;

import com.PMS.DAO.DoctorDAO;
import com.PMS.model.entity.Doctor;
import com.PMS.view.auth.LoginFrame;
import com.PMS.view.auth.SignUpFrame;
import com.PMS.view.dashboard.DashboardFrame;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * LoginController:
 * Handles all button actions of the LoginFrame.
 * Implements ActionListener to listen for button click events.
 */
public class LoginController implements ActionListener {

    // Reference of LoginFrame to access UI components
    LoginFrame loginFrame;

    /*
     * Constructor:
     * Connects the controller with LoginFrame
     * and registers action listeners on buttons.
     */
    public LoginController(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;

        // Registering controller as listener for all buttons
        loginFrame.getLoginBtn().addActionListener(this);
        loginFrame.getSignupBtn().addActionListener(this);
        loginFrame.getClearBtn().addActionListener(this);
    }

    /*
     * actionPerformed():
     * Automatically called whenever any registered button is clicked.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        // Checks if Login button is clicked
        if (e.getSource() == loginFrame.getLoginBtn()) {

            // Get entered username and password from login form
            String username = loginFrame.getUsernameField().getText().trim();
            String password = String.valueOf(loginFrame.getPasswordField().getPassword());

            // ---------------- VALIDATIONS ----------------

            // Check if any field is empty
            if (username.isEmpty() || password.isEmpty()) {

                JOptionPane.showMessageDialog(
                        loginFrame,
                        "Please enter all the fields!"
                );
                return;
            }

            // Username should not contain spaces
            if (username.contains(" ")) {

                JOptionPane.showMessageDialog(
                        loginFrame,
                        "Username should not contain spaces!"
                );
                return;
            }

            // Username length validation
            if (username.length() > 8) {

                JOptionPane.showMessageDialog(
                        loginFrame,
                        "Username length should be less than or equal to 8!"
                );
                return;
            }

            // Password must contain exactly 4 characters
            if (password.length() != 4) {

                JOptionPane.showMessageDialog(
                        loginFrame,
                        "Password must be exactly 4 digits!"
                );
                return;
            }

            // Password should contain only numeric digits
            if (!password.matches("[0-9]+")) {

                JOptionPane.showMessageDialog(
                        loginFrame,
                        "Password should contain only digits!"
                );
                return;
            }

            // ---------------- DATABASE CHECK ----------------

            try {

                DoctorDAO doctorDAO = new DoctorDAO();

                Doctor doctor = doctorDAO.loginDoctor(username, password);

                if (doctor != null) {

                    JOptionPane.showMessageDialog(
                            loginFrame,
                            "Login Successful!"
                    );

                    loginFrame.dispose();

                    DashboardFrame dashboardFrame = new DashboardFrame();

                    new DashboardController(dashboardFrame);

                    dashboardFrame.setVisible(true);

                } else {

                    JOptionPane.showMessageDialog(
                            loginFrame,
                            "Invalid Username or Password!"
                    );
                }

            } catch (Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                        loginFrame,
                        "Something went wrong!"
                );
            }

        }

        // Checks if Signup button is clicked
        else if(e.getSource() == loginFrame.getSignupBtn()){

            // Closes current login window
            loginFrame.dispose();

            // Creates Signup window
            SignUpFrame signUpFrame = new SignUpFrame();

            // Connects Signup window with its controller
            new SignUpController(signUpFrame);

            // Displays Signup window
            signUpFrame.setVisible(true);

        }

        // Checks if Clear button is clicked
        else if(e.getSource() == loginFrame.getClearBtn()){

            // Clears username and password input fields
            loginFrame.getUsernameField().setText("");
            loginFrame.getPasswordField().setText("");
        }
    }

}