package com.PMS.controller.DashboardController;

import com.PMS.controller.authController.LoginController;
import com.PMS.view.auth.LoginFrame;
import com.PMS.view.dashboard.DashboardFrame;
import com.PMS.view.dashboard.panels.AddPatientPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * DashboardController:
 *
 * Handles all navigation actions inside the Dashboard.
 *
 * Responsibilities:
 * 1. Listen for clicks on sidebar buttons.
 * 2. Switch between different dashboard panels using CardLayout.
 * 3. Open the Login screen when the user logs out.
 *
 * This controller does NOT perform database operations.
 * It only manages screen navigation.
 *
 * Workflow:
 * User clicks sidebar button
 * -> Controller detects button click
 * -> CardLayout displays corresponding panel
 */
public class DashboardController implements ActionListener {

    // Reference to the main dashboard window
    DashboardFrame dashboardFrame;

    // Reference to Add Patient panel (currently unused)
    private AddPatientPanel addPatientPanel;

    /*
     * Constructor:
     * Registers this controller as the listener
     * for all dashboard sidebar buttons.
     */
    public DashboardController(DashboardFrame dashboardFrame) {

        this.dashboardFrame = dashboardFrame;

        // Register listeners for all dashboard menu buttons
        dashboardFrame.getAddPatientBtn().addActionListener(this);
        dashboardFrame.getDeletePatientBtn().addActionListener(this);
        dashboardFrame.getUpdatePatientBtn().addActionListener(this);
        dashboardFrame.getViewAllPatientsBtn().addActionListener(this);
        dashboardFrame.getSearchPatientBtn().addActionListener(this);
        dashboardFrame.getLogoutBtn().addActionListener(this);
    }

    /*
     * actionPerformed():
     * Automatically called whenever a registered
     * dashboard button is clicked.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        // ==================================================
        // ADD PATIENT PANEL
        // ==================================================
        if(e.getSource() == dashboardFrame.getAddPatientBtn()) {

            /*
             * Get the CardLayout managing all dashboard panels.
             */

            /*
             * CardLayout works like a deck of cards.
             *
             * Multiple panels are added to the same container,
             * but only one panel is visible at a time.
             *
             * show(container, panelName) hides the current panel
             * and displays the requested panel.
             */

            CardLayout add =
                    (CardLayout) dashboardFrame.getContentPanel().getLayout();

            /*
             * Display Add Patient panel.
             *
             * "ADD_PATIENT" is the name assigned when
             * the panel was added to CardLayout.
             */
            add.show(
                    dashboardFrame.getContentPanel(),
                    "ADD_PATIENT"
            );

            /*
             * Refresh the content panel to ensure
             * newly displayed components appear correctly.
             */
            dashboardFrame.getContentPanel().revalidate();
            dashboardFrame.getContentPanel().repaint();

        }

        // ==================================================
        // UPDATE PATIENT PANEL
        // ==================================================
        else if(e.getSource() == dashboardFrame.getUpdatePatientBtn()) {

            CardLayout update =
                    (CardLayout) dashboardFrame.getContentPanel().getLayout();

            update.show(
                    dashboardFrame.getContentPanel(),
                    "UPDATE_PATIENT"
            );

            dashboardFrame.getContentPanel().revalidate();
            dashboardFrame.getContentPanel().repaint();

        }

        // ==================================================
        // SEARCH PATIENT PANEL
        // ==================================================
        else if(e.getSource() == dashboardFrame.getSearchPatientBtn()) {

            CardLayout search =
                    (CardLayout) dashboardFrame.getContentPanel().getLayout();

            search.show(
                    dashboardFrame.getContentPanel(),
                    "SEARCH_PATIENT"
            );

            dashboardFrame.getContentPanel().revalidate();
            dashboardFrame.getContentPanel().repaint();

        }

        // ==================================================
        // VIEW ALL PATIENTS PANEL
        // ==================================================
        else if(e.getSource() == dashboardFrame.getViewAllPatientsBtn()) {

            CardLayout view =
                    (CardLayout) dashboardFrame.getContentPanel().getLayout();

            view.show(
                    dashboardFrame.getContentPanel(),
                    "VIEW_PATIENTS"
            );

            dashboardFrame.getContentPanel().revalidate();
            dashboardFrame.getContentPanel().repaint();

        }

        // ==================================================
        // DELETE PATIENT PANEL
        // ==================================================
        else if(e.getSource() == dashboardFrame.getDeletePatientBtn()) {

            CardLayout delete =
                    (CardLayout) dashboardFrame.getContentPanel().getLayout();

            delete.show(
                    dashboardFrame.getContentPanel(),
                    "DELETE_PATIENT"
            );

            dashboardFrame.getContentPanel().revalidate();
            dashboardFrame.getContentPanel().repaint();

        }

        // ==================================================
        // LOGOUT FUNCTIONALITY
        // ==================================================
        else if(e.getSource() == dashboardFrame.getLogoutBtn()) {

            /*
             * Close current dashboard window.
             */
            dashboardFrame.dispose();

            /*
             * Create Login screen again so user
             * can log in with another account.
             */
            LoginFrame loginFrame = new LoginFrame();

            /*
             * Connect Login screen with its controller.
             */
            new LoginController(loginFrame);

            /*
             * Display Login window.
             */
            loginFrame.setVisible(true);
        }
    }
}