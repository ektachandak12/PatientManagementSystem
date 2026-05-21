package com.PMS.controller.authController;

import com.PMS.view.auth.LoginFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {
    LoginFrame loginFrame;

    public LoginController(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;

        loginFrame.getLoginBtn().addActionListener(this);
        loginFrame.getSignupBtn().addActionListener(this);
        loginFrame.getClearBtn().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginFrame.getLoginBtn()){

        }else if(e.getSource() == loginFrame.getSignupBtn()){

        }else if(e.getSource() == loginFrame.getClearBtn()){
            loginFrame.usernameField.setText("");
            loginFrame.passwordField.setText("");
        }
    }


}
