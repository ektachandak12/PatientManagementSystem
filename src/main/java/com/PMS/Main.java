package com.PMS;

import com.PMS.controller.authController.LoginController;
import com.PMS.view.auth.LoginFrame;

public class Main {
    public static void main(String[] args) {
        LoginFrame loginframe = new LoginFrame();

        new LoginController(loginframe);
    }
}
