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

public class ViewAllPatientsController implements ActionListener {

    private DashboardFrame dashboardFrame;
    private ViewAllPatientsPanel viewAllPatientsPanel;
    private PatientDAO patientDAO;

    public ViewAllPatientsController(
            DashboardFrame dashboardFrame,
            ViewAllPatientsPanel viewAllPatientsPanel) {

        this.dashboardFrame = dashboardFrame;
        this.viewAllPatientsPanel = viewAllPatientsPanel;

        patientDAO = new PatientDAO();

        viewAllPatientsPanel.getBackBtn().addActionListener(this);
        dashboardFrame.getViewAllPatientsBtn().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == viewAllPatientsPanel.getBackBtn()) {

            // Get CardLayout used by dashboard content panel
            CardLayout cl = (CardLayout) dashboardFrame.getContentPanel().getLayout();

            // Navigate back to welcome screen
            cl.show(dashboardFrame.getContentPanel(), "WELCOME");
        }

        else if(e.getSource() == dashboardFrame.getViewAllPatientsBtn()) {

            try {

                // Fetch all patients from database
                List<Patient> patients = patientDAO.getAllPatients();

                if(patients.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            dashboardFrame,
                            "No patients found."
                    );
                }

                // Populate JTable
                populateTable(patients);

                // Show View All Patients panel
                CardLayout cl =
                        (CardLayout) dashboardFrame.getContentPanel().getLayout();

                cl.show(
                        dashboardFrame.getContentPanel(),
                        "VIEW_ALL_PATIENTS"
                );

            }
            catch(Exception ex) {

                ex.printStackTrace();
            }
        }
    }

    private void populateTable(List<Patient> patients) {

        DefaultTableModel model =
                (DefaultTableModel) viewAllPatientsPanel
                        .getPatientTable()
                        .getModel();

        // Clear existing rows
        model.setRowCount(0);

        // Add fresh data
        for(Patient patient : patients) {

            model.addRow(new Object[] {
                    patient.getId(),
                    patient.getName(),
                    patient.getAge(),
                    patient.getGender()
            });
        }
    }
}