package com.PMS.controller.panelController;

import com.PMS.DAO.PatientDAO;;
import com.PMS.view.dashboard.DashboardFrame;
import com.PMS.view.dashboard.panels.DeletePatientPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * DeletePatientController:
 * Handles all actions related to deleting a patient record.
 * It validates the entered patient ID, deletes the corresponding
 * record from the database, and displays appropriate messages.
 */
public class DeletePatientController implements ActionListener {

    // Reference to the main dashboard window
    DashboardFrame dashboardFrame;

    // Reference to the Delete Patient panel
    DeletePatientPanel deletePatientPanel;

    public DeletePatientController(DashboardFrame dashboardFrame, DeletePatientPanel deletePatientPanel) {
        this.dashboardFrame = dashboardFrame;
        this.deletePatientPanel = deletePatientPanel;

        // Register action listeners for panel buttons
        deletePatientPanel.getDeleteBtn().addActionListener(this);
        deletePatientPanel.getBackBtn().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Handles Back button click
        if(e.getSource() == deletePatientPanel.getBackBtn()) {

            // Get CardLayout used by dashboard content panel
            CardLayout c1 = (CardLayout) dashboardFrame.getContentPanel().getLayout();

            // Navigate back to welcome screen
            c1.show(dashboardFrame.getContentPanel(), "WELCOME");
        }

        // Handles Delete button click
        else if(e.getSource() == deletePatientPanel.getDeleteBtn()) {

            try {

                // Read patient ID entered by user
                String idText = deletePatientPanel.getIdTextField()
                        .getText()
                        .trim();

                // Check if ID field is empty
                if(idText.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            deletePatientPanel,
                            "Please enter Patient ID"
                    );
                    return;
                }

                // Convert entered ID to integer
                int deleteID = Integer.parseInt(idText);

                // Create DAO object to perform database operation
                PatientDAO patientDAO = new PatientDAO();

                // Delete patient record using the entered ID
                int rowsAffected = patientDAO.deletePatient(deleteID);

                // If rows affected is greater than zero,
                // patient record was successfully deleted
                if(rowsAffected > 0) {

                    JOptionPane.showMessageDialog(
                            dashboardFrame,
                            "PATIENT DELETED SUCCESSFULLY"
                    );

                    // Clear text field after successful deletion
                    deletePatientPanel.getIdTextField().setText("");
                }
                else {

                    // No matching patient record found
                    JOptionPane.showMessageDialog(
                            dashboardFrame,
                            "No patient found with ID : " + deleteID
                    );
                }

            }
            catch(NumberFormatException ex) {

                // Handles invalid input such as letters or symbols
                JOptionPane.showMessageDialog(
                        deletePatientPanel,
                        "Please enter a valid numeric ID"
                );
            }
            catch(Exception ex) {

                // Display database or unexpected errors
                JOptionPane.showMessageDialog(
                        dashboardFrame,
                        "Error deleting patient : " + ex.getMessage()
                );

                // Print detailed error in console for debugging
                ex.printStackTrace();
            }

            // Clear ID field after operation completes
            deletePatientPanel.getIdTextField().setText("");
        }
    }
}