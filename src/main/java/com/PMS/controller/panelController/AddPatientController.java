package com.PMS.controller.panelController;

import com.PMS.view.dashboard.DashboardFrame;
import com.PMS.view.dashboard.panels.AddPatientPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPatientController implements ActionListener {

    DashboardFrame dashboardFrame;
    AddPatientPanel addPatientPanel;

    public AddPatientController(DashboardFrame dashboardFrame, AddPatientPanel addPatientPanel) {

        System.out.println("AddPatientController Created");

        this.dashboardFrame = dashboardFrame;
        this.addPatientPanel = addPatientPanel;

        addPatientPanel.getBackBtn().addActionListener(this);
        addPatientPanel.getSaveBtn().addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("Action Fired");

        if(e.getSource() == addPatientPanel.getBackBtn()){

            System.out.println("Back button clicked");

            CardLayout c1 = (CardLayout) dashboardFrame.getContentPanel().getLayout();

            c1.show(dashboardFrame.getContentPanel(), "WELCOME");
        }
    }
}
