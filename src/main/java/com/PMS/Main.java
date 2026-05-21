package com.PMS;

import com.PMS.controller.authController.LoginController;
import com.PMS.model.util.FactoryProvider;
import com.PMS.view.auth.LoginFrame;

/*
 * Main Class:
 * Entry point of the Patient Management System application.
 */
public class Main {

    public static void main(String[] args) {

        // Initialize Hibernate SessionFactory
        FactoryProvider.getFactory();

        System.out.println("Hibernate Started...");
        System.out.println("Database Connected Successfully!");

        // Creates and displays the LoginFrame UI
        LoginFrame loginframe = new LoginFrame();

        // Connects LoginFrame with LoginController
        new LoginController(loginframe);

    }
}