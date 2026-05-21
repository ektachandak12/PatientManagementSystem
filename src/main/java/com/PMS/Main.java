package com.PMS;

import com.PMS.controller.authController.LoginController;
import com.PMS.controller.authController.SignUpController;
import com.PMS.view.auth.LoginFrame;
import com.PMS.view.auth.SignUpFrame;

/*
 * Main Class:
 * Entry point of the Patient Management System application.
 */
public class Main {

    public static void main(String[] args) {

        // Creates and displays the LoginFrame UI
        LoginFrame loginframe = new LoginFrame();

        // Connects LoginFrame with LoginController
        // to handle button actions and events
        new LoginController(loginframe);



    }
}