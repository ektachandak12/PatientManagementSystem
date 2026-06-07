package com.PMS.controller.DashboardController.panelController;

import com.PMS.DAO.PatientDAO;
import com.PMS.model.entity.Patient;
import com.PMS.view.dashboard.DashboardFrame;
import com.PMS.view.dashboard.panels.SearchPatientPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * SearchPatientController:
 * Handles all actions related to the Search Patient screen.
 * It allows the user to search a patient by ID and display
 * the retrieved patient details on the same panel.
 */
public class SearchPatientController  implements ActionListener{

    // Reference to the main dashboard frame
    DashboardFrame dashboardFrame;

    // Reference to the Search Patient panel
    SearchPatientPanel searchPatientPanel;

    public SearchPatientController(DashboardFrame dashboardFrame, SearchPatientPanel searchPatientPanel) {
        this.dashboardFrame = dashboardFrame;
        this.searchPatientPanel = searchPatientPanel;

        // Register action listeners for buttons
        searchPatientPanel.getSearchBtn().addActionListener(this);
        searchPatientPanel.getBackBtn().addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        // Handles Back button click
        if(e.getSource() == searchPatientPanel.getBackBtn()){

            searchPatientPanel.getIdTextField().setText("");
            searchPatientPanel.getNameTextField().setText("");
            searchPatientPanel.getAgeTextField().setText("");
            searchPatientPanel.getGenderTextField().setText("");
            searchPatientPanel.getCityTextField().setText("");
            searchPatientPanel.getHeightTextField().setText("");
            searchPatientPanel.getWeightTextField().setText("");
            searchPatientPanel.getBmiTextField().setText("");
            searchPatientPanel.getBmiCategoryTextField().setText("");
            

            // Get CardLayout used by the dashboard content panel
            CardLayout c1 = (CardLayout) dashboardFrame.getContentPanel().getLayout();

            // Navigate back to the welcome screen
            c1.show(dashboardFrame.getContentPanel(), "WELCOME");
        }

        // Handles Search button click
        else if(e.getSource() == searchPatientPanel.getSearchBtn()){

            try{

                // Read and convert entered patient ID to integer
                int searchId = Integer.parseInt(
                        searchPatientPanel.getIdTextField().getText()
                );

                // Create DAO object to interact with patient database
                PatientDAO patientDAO = new PatientDAO();

                // Search patient using entered ID
                Patient patient = patientDAO.searchPatient(searchId);

                System.out.println(patient);

                try {

                    // If patient exists, display all retrieved details
                    if (patient != null) {

                        // Display patient name
                        searchPatientPanel.getNameTextField()
                                .setText(patient.getName());

                        // Display patient age
                        searchPatientPanel.getAgeTextField()
                                .setText(String.valueOf(patient.getAge()));

                        // Display patient gender
                        searchPatientPanel.getGenderTextField()
                                .setText(patient.getGender());

                        // Display patient city
                        searchPatientPanel.getCityTextField()
                                .setText(patient.getCity());

                        // Display patient height
                        searchPatientPanel.getHeightTextField()
                                .setText(String.valueOf(patient.getHeight()));

                        // Display patient weight
                        searchPatientPanel.getWeightTextField()
                                .setText(String.valueOf(patient.getWeight()));

                        // Display calculated BMI value
                        searchPatientPanel.getBmiTextField()
                                .setText(String.valueOf(patient.getBmi()));

                        // Display BMI category (Underweight, Normal, etc.)
                        searchPatientPanel.getBmiCategoryTextField()
                                .setText(patient.getBmiCategory());

                    } else {

                        // Show message if no patient record is found
                        JOptionPane.showMessageDialog(
                                searchPatientPanel,
                                "Patient not found"
                        );


                        return;
                    }
                } catch (Exception ex) {

                    // Print detailed error in console for debugging
                    ex.printStackTrace();

                    // Show generic error message to user
                    JOptionPane.showMessageDialog(
                            searchPatientPanel,
                            "Something went wrong!"
                    );
                }
            }
            catch(NumberFormatException ex){

                // Handles invalid ID input such as letters or special characters
                JOptionPane.showMessageDialog(
                        searchPatientPanel,
                        "Please enter a valid numeric ID"
                );
            }

        }
    }
}