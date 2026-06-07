package com.PMS.controller.authController;

import com.PMS.DAO.DoctorDAO;
import com.PMS.controller.DashboardController.DashboardController;
import com.PMS.model.entity.Doctor;
import com.PMS.view.auth.LoginFrame;
import com.PMS.view.auth.SignUpFrame;
import com.PMS.view.dashboard.DashboardFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * LoginController:
 * Acts as the controller for the LoginFrame.
 * Handles user interactions such as Login, Signup navigation,
 * and clearing input fields.
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
     * Determines which button triggered the event and performs
     * the corresponding action.
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
            // Restricts username to a maximum of 8 characters
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
            // After successful validation, verify credentials from database

            try {

                // Create DAO object to interact with doctor table
                DoctorDAO doctorDAO = new DoctorDAO();

                // Check whether entered username and password exist in database
                Doctor doctor = doctorDAO.loginDoctor(username, password);

                // Login successful if a matching doctor record is found
                if (doctor != null) {

                    JOptionPane.showMessageDialog(
                            loginFrame,
                            "Login Successful!"
                    );

                    // Close login window after successful authentication
                    loginFrame.dispose();

                    // Open dashboard screen
                    DashboardFrame dashboardFrame = new DashboardFrame();

                    // Attach controller to dashboard
                    new DashboardController(dashboardFrame);

                    // Make dashboard visible to user
                    dashboardFrame.setVisible(true);

                } else {

                    // Display error if credentials do not match any record
                    JOptionPane.showMessageDialog(
                            loginFrame,
                            "Invalid Username or Password!"
                    );
                }

            } catch (Exception ex) {

                // Print detailed error in console for debugging
                ex.printStackTrace();

                // Display generic error message to user
                JOptionPane.showMessageDialog(
                        loginFrame,
                        "Something went wrong!"
                );
            }

        }

        // Checks if Signup button is clicked
        else if(e.getSource() == loginFrame.getSignupBtn()){

            // Close login screen before opening signup screen
            loginFrame.dispose();

            // Create Signup window
            SignUpFrame signUpFrame = new SignUpFrame();

            // Connect Signup window with its controller
            new SignUpController(signUpFrame);

            // Display Signup window
            signUpFrame.setVisible(true);

        }

        // Checks if Clear button is clicked
        else if(e.getSource() == loginFrame.getClearBtn()){

            // Reset username field
            loginFrame.getUsernameField().setText("");

            // Reset password field
            loginFrame.getPasswordField().setText("");
        }
    }

}