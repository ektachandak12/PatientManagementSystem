package com.PMS.controller.DashboardController.panelController;

import com.PMS.DAO.PatientDAO;
import com.PMS.model.entity.Patient;
import com.PMS.view.dashboard.DashboardFrame;
import com.PMS.view.dashboard.panels.ViewAllPatientsPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/*
 * ViewAllPatientsController:
 *
 * Handles all functionality related to displaying patient records
 * in a JTable.
 *
 * Main Responsibilities:
 * 1. Fetch all patient records from the database.
 * 2. Populate the JTable with patient data.
 * 3. Display the View All Patients screen.
 * 4. Navigate back to the Welcome screen.
 *
 * This controller acts as a bridge between:
 * - ViewAllPatientsPanel (View)
 * - PatientDAO (Database Layer)
 * - Patient Entity (Patient Data)
 *
 * Workflow:
 * Click "View All Patients"
 * -> Fetch data from database
 * -> Populate JTable
 * -> Display records on screen
 */
public class ViewAllPatientsController implements ActionListener {

    // Reference to the main dashboard frame
    private DashboardFrame dashboardFrame;

    // Reference to the panel containing JTable
    private ViewAllPatientsPanel viewAllPatientsPanel;

    // DAO object used to interact with database
    private PatientDAO patientDAO;

    /*
     * Constructor:
     * Connects controller with panel and registers button listeners.
     */
    public ViewAllPatientsController(
            DashboardFrame dashboardFrame,
            ViewAllPatientsPanel viewAllPatientsPanel) {

        this.dashboardFrame = dashboardFrame;
        this.viewAllPatientsPanel = viewAllPatientsPanel;

        // Create DAO object for database operations
        patientDAO = new PatientDAO();

        // Register action listeners
        viewAllPatientsPanel.getBackBtn().addActionListener(this);
        dashboardFrame.getViewAllPatientsBtn().addActionListener(this);
    }

    /*
     * actionPerformed():
     * Automatically executes whenever a registered button is clicked.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        // ==================================================
        // BACK BUTTON LOGIC
        // ==================================================
        if(e.getSource() == viewAllPatientsPanel.getBackBtn()) {

            // Get CardLayout used by dashboard content panel
            CardLayout cl =
                    (CardLayout) dashboardFrame.getContentPanel().getLayout();

            // Navigate back to welcome screen
            cl.show(
                    dashboardFrame.getContentPanel(),
                    "WELCOME"
            );
        }

        // ==================================================
        // VIEW ALL PATIENTS BUTTON LOGIC
        // ==================================================
        else if(e.getSource() == dashboardFrame.getViewAllPatientsBtn()) {

            try {

                /*
                 * Retrieve all patient records from database.
                 *
                 * getAllPatients() returns a List containing
                 * every Patient object stored in the database.
                 */
                List<Patient> patients =
                        patientDAO.getAllPatients();

                // Display message if database contains no records
                if(patients.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            dashboardFrame,
                            "No patients found."
                    );
                }

                /*
                 * Send retrieved patient list to helper method
                 * which fills the JTable.
                 */
                populateTable(patients);

                /*
                 * After table is populated,
                 * display the View All Patients panel.
                 */
                CardLayout cl =
                        (CardLayout) dashboardFrame.getContentPanel().getLayout();

                cl.show(
                        dashboardFrame.getContentPanel(),
                        "VIEW_ALL_PATIENTS"
                );

            }
            catch(Exception ex) {

                // Print error details for debugging
                ex.printStackTrace();
            }
        }
    }

    /*
     * populateTable():
     *
     * Receives a list of Patient objects and loads them
     * into the JTable.
     *
     * Each Patient object becomes one row in the table.
     */
    private void populateTable(List<Patient> patients) {

        /*
         * Obtain the table model associated with JTable.
         *
         * DefaultTableModel manages table rows and columns.
         */
        DefaultTableModel model =
                (DefaultTableModel) viewAllPatientsPanel
                        .getPatientTable()
                        .getModel();

        /*
         * Remove all existing rows before loading fresh data.
         *
         * This prevents duplicate records from appearing
         * when the button is clicked multiple times.
         */
        model.setRowCount(0);

        /*
         * Loop through every patient object
         * and add it as a new row in the JTable.
         */
        for(Patient patient : patients) {

            model.addRow(new Object[] {

                    // Column 1 - Patient ID
                    patient.getId(),

                    // Column 2 - Patient Name
                    patient.getName(),

                    // Column 3 - Patient Age
                    patient.getAge(),

                    // Column 4 - Patient Gender
                    patient.getGender()
            });
        }
    }
}