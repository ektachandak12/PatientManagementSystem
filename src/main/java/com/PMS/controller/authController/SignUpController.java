package com.PMS.controller.authController;

import com.PMS.view.auth.LoginFrame;
import com.PMS.view.auth.SignUpFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * SignUpController:
 * Handles all button actions of the SignUpFrame.
 * Implements ActionListener to listen for button click events.
 */
public class SignUpController implements ActionListener {

    // Reference of SignUpFrame to access UI components
    SignUpFrame signUpFrame;

    /*
     * Constructor:
     * Connects the controller with SignUpFrame
     * and registers action listeners on buttons.
     */
    public SignUpController(SignUpFrame signUpFrame) {
        this.signUpFrame = signUpFrame;

        // Registering controller as listener for buttons
        signUpFrame.getClearBtn().addActionListener(this);
        signUpFrame.getSignupBtn().addActionListener(this);
    }

    /*
     * actionPerformed():
     * Automatically called whenever any registered button is clicked.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        // Checks if Signup button is clicked
        if(e.getSource() == signUpFrame.getSignupBtn()) {

            // Closes current signup window
            signUpFrame.dispose();

            // Opens LoginFrame after successful signup
            LoginFrame loginFrame = new LoginFrame();

            // Connects LoginFrame with its controller
            new LoginController(loginFrame);

            // Displays login window
            loginFrame.setVisible(true);
        }

        // Checks if Clear button is clicked
        else if(e.getSource() == signUpFrame.getClearBtn()){

            // Clears username and password input fields
            signUpFrame.getUsernameField().setText("");
            signUpFrame.getPasswordField().setText("");
        }
    }
}