package com.PMS.controller.authController;

import com.PMS.DAO.DoctorDAO;
import com.PMS.model.entity.Doctor;
import com.PMS.view.auth.LoginFrame;
import com.PMS.view.auth.SignUpFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * SignUpController:
 * Handles signup form actions like signup and clear button clicks.
 */
public class SignUpController implements ActionListener {

    // Reference to the signup screen
    SignUpFrame signUpFrame;

    // Connect controller with SignUpFrame and register button listeners
    public SignUpController(SignUpFrame signUpFrame) {
        this.signUpFrame = signUpFrame;

        signUpFrame.getClearBtn().addActionListener(this);
        signUpFrame.getSignupBtn().addActionListener(this);
    }

    // Executes when any registered button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {

        // Signup button clicked
        if(e.getSource() == signUpFrame.getSignupBtn()) {

            // Read username and password entered by user
            String username = signUpFrame.getUsernameField().getText().trim();
            String password = String.valueOf(signUpFrame.getPasswordField().getPassword());

            // Check if any field is empty
            if (username.isEmpty() || password.isEmpty()) {

                JOptionPane.showMessageDialog(
                        signUpFrame,
                        "Please enter all the fields!"
                );
                return;
            }

            // Username should not contain spaces
            if (username.contains(" ")) {

                JOptionPane.showMessageDialog(
                        signUpFrame,
                        "Username should not contain spaces!"
                );
                return;
            }

            // Username can have maximum 8 characters
            if (username.length() > 8) {

                JOptionPane.showMessageDialog(
                        signUpFrame,
                        "Username length should be less than or equal to 8!"
                );
                return;
            }

            // Password must contain exactly 4 digits
            if (password.length() != 4) {

                JOptionPane.showMessageDialog(
                        signUpFrame,
                        "Password must be exactly 4 digits!"
                );
                return;
            }

            // Allow only numeric digits in password
            if (!password.matches("[0-9]+")) {

                JOptionPane.showMessageDialog(
                        signUpFrame,
                        "Password should contain only digits!"
                );
                return;
            }

            // Create Doctor object and store signup details
            Doctor doctor = new Doctor();

            doctor.setUsername(
                    signUpFrame.getUsernameField().getText()
            );

            doctor.setPassword(
                    String.valueOf(
                            signUpFrame.getPasswordField().getPassword()
                    )
            );

            // Save doctor details in database
            DoctorDAO doctorDAO = new DoctorDAO();
            doctorDAO.saveDoctor(doctor);

            // Show success message
            JOptionPane.showMessageDialog(null, "Sign up Successful");

            // Close signup window
            signUpFrame.dispose();

            // Open login screen
            LoginFrame loginFrame = new LoginFrame();

            // Attach controller to login screen
            new LoginController(loginFrame);

            loginFrame.setVisible(true);
        }

        // Clear button clicked
        else if(e.getSource() == signUpFrame.getClearBtn()){

            // Clear all input fields
            signUpFrame.getUsernameField().setText("");
            signUpFrame.getPasswordField().setText("");
        }
    }
}