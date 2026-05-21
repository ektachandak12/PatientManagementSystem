package com.PMS.controller.authController;

import com.PMS.view.auth.LoginFrame;
import com.PMS.view.auth.SignUpFrame;

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
        if(e.getSource() == loginFrame.getLoginBtn()){

            // Login authentication logic will be added here

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