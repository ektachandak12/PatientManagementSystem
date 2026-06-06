package com.PMS.controller.authController;

import com.PMS.controller.panelController.AddPatientController;
import com.PMS.view.auth.LoginFrame;
import com.PMS.view.dashboard.DashboardFrame;
import com.PMS.view.dashboard.panels.AddPatientPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardController implements ActionListener {

    DashboardFrame dashboardFrame;
    private AddPatientPanel addPatientPanel;

    public DashboardController(DashboardFrame dashboardFrame) {
        this.dashboardFrame = dashboardFrame;

        dashboardFrame.getAddPatientBtn().addActionListener(this);
        dashboardFrame.getDeletePatientBtn().addActionListener(this);
        dashboardFrame.getUpdatePatientBtn().addActionListener(this);
        dashboardFrame.getViewPatientsBtn().addActionListener(this);
        dashboardFrame.getSearchPatientBtn().addActionListener(this);
        dashboardFrame.getLogoutBtn().addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == dashboardFrame.getAddPatientBtn()) {

            CardLayout add = (CardLayout) dashboardFrame.getContentPanel().getLayout();

            add.show(dashboardFrame.getContentPanel(), "ADD_PATIENT");

            dashboardFrame.getContentPanel().revalidate();
            dashboardFrame.getContentPanel().repaint();

        }else if(e.getSource() == dashboardFrame.getUpdatePatientBtn()) {

            CardLayout update = (CardLayout) dashboardFrame.getContentPanel().getLayout();
            update.show(dashboardFrame.getContentPanel(), "UPDATE_PATIENT");

            dashboardFrame.getContentPanel().revalidate();
            dashboardFrame.getContentPanel().repaint();

        }else if(e.getSource() == dashboardFrame.getSearchPatientBtn()){

            CardLayout search = (CardLayout) dashboardFrame.getContentPanel().getLayout();
            search.show(dashboardFrame.getContentPanel(), "SEARCH_PATIENT");

            dashboardFrame.getContentPanel().revalidate();
            dashboardFrame.getContentPanel().repaint();


        }else if(e.getSource() == dashboardFrame.getViewPatientsBtn()) {

            CardLayout view = (CardLayout) dashboardFrame.getContentPanel().getLayout();
            view.show(dashboardFrame.getContentPanel(), "VIEW_PATIENTS");

            dashboardFrame.getContentPanel().revalidate();
            dashboardFrame.getContentPanel().repaint();

        }else if(e.getSource() == dashboardFrame.getDeletePatientBtn()) {

            CardLayout delete = (CardLayout) dashboardFrame.getContentPanel().getLayout();
            delete.show(dashboardFrame.getContentPanel(), "DELETE_PATIENT");

            dashboardFrame.getContentPanel().revalidate();
            dashboardFrame.getContentPanel().repaint();

        }else if(e.getSource() == dashboardFrame.getLogoutBtn()) {

            dashboardFrame.dispose();

            LoginFrame loginFrame = new LoginFrame();
            new LoginController(loginFrame);

            loginFrame.setVisible(true);


        }
    }
}
