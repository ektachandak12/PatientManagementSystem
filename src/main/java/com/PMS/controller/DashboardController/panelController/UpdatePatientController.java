package com.PMS.controller.DashboardController.panelController;

import com.PMS.DAO.PatientDAO;
import com.PMS.model.entity.Patient;
import com.PMS.view.dashboard.DashboardFrame;
import com.PMS.view.dashboard.panels.UpdatePatientPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePatientController implements ActionListener {

    DashboardFrame dashboardFrame;

    UpdatePatientPanel updatePatientPanel;

    private Patient originalPatient;

    public UpdatePatientController(DashboardFrame dashboardFrame, UpdatePatientPanel updatePatientPanel) {
        this.dashboardFrame = dashboardFrame;
        this.updatePatientPanel = updatePatientPanel;

        updatePatientPanel.getSearchBtn().addActionListener(this);
        updatePatientPanel.getUpdateBtn().addActionListener(this);
        updatePatientPanel.getBackBtn().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == updatePatientPanel.getBackBtn()) {

            updatePatientPanel.getIdTextField().setText("");
            updatePatientPanel.getNameTextField().setText("");
            updatePatientPanel.getAgeTextField().setText("");
            updatePatientPanel.getGenderTextField().setText("");
            updatePatientPanel.getCityTextField().setText("");
            updatePatientPanel.getHeightTextField().setText("");
            updatePatientPanel.getWeightTextField().setText("");


            // Get CardLayout used by the dashboard content panel
            CardLayout c1 = (CardLayout) dashboardFrame.getContentPanel().getLayout();

            // Navigate back to the welcome screen
            c1.show(dashboardFrame.getContentPanel(), "WELCOME");
        }

        else if(e.getSource() ==  updatePatientPanel.getSearchBtn()) {
            try{

                // Read and convert entered patient ID to integer
                int searchId = Integer.parseInt(
                        updatePatientPanel.getIdTextField().getText()
                );

                // Create DAO object to interact with patient database
                PatientDAO patientDAO = new PatientDAO();

                // Search patient using entered ID
                Patient patient = patientDAO.searchPatient(searchId);

                System.out.println(patient);

                try {

                    // If patient exists, display all retrieved details
                    if (patient != null) {

                        originalPatient = patient;

                        // Display patient name
                        updatePatientPanel.getNameTextField()
                                .setText(patient.getName());

                        // Display patient age
                        updatePatientPanel.getAgeTextField()
                                .setText(String.valueOf(patient.getAge()));

                        // Display patient gender
                        updatePatientPanel.getGenderTextField()
                                .setText(patient.getGender());

                        // Display patient city
                        updatePatientPanel.getCityTextField()
                                .setText(patient.getCity());

                        // Display patient height
                        updatePatientPanel.getHeightTextField()
                                .setText(String.valueOf(patient.getHeight()));

                        // Display patient weight
                        updatePatientPanel.getWeightTextField()
                                .setText(String.valueOf(patient.getWeight()));

                        // Make fields editable after search
                        updatePatientPanel.getNameTextField().setEditable(true);
                        updatePatientPanel.getAgeTextField().setEditable(true);
                        updatePatientPanel.getGenderTextField().setEditable(true);
                        updatePatientPanel.getCityTextField().setEditable(true);
                        updatePatientPanel.getHeightTextField().setEditable(true);
                        updatePatientPanel.getWeightTextField().setEditable(true);

                    } else {

                        // Show message if no patient record is found
                        JOptionPane.showMessageDialog(
                                updatePatientPanel,
                                "Patient not found"
                        );


                        return;
                    }
                } catch (Exception ex) {

                    // Print detailed error in console for debugging
                    ex.printStackTrace();

                    // Show generic error message to user
                    JOptionPane.showMessageDialog(
                            updatePatientPanel,
                            "Something went wrong!"
                    );
                }
            }
            catch(NumberFormatException ex){

                // Handles invalid ID input such as letters or special characters
                JOptionPane.showMessageDialog(
                        updatePatientPanel,
                        "Please enter a valid numeric ID"
                );
            }

        }

        else if (e.getSource() == updatePatientPanel.getUpdateBtn()) {

            try {

                if (originalPatient == null) {
                    JOptionPane.showMessageDialog(
                            updatePatientPanel,
                            "Please search a patient first!"
                    );
                    return;
                }

                Patient updatedPatient = new Patient();

                updatedPatient.setId(originalPatient.getId());

                updatedPatient.setName(updatePatientPanel.getNameTextField().getText());

                updatedPatient.setAge(Integer.parseInt(
                        updatePatientPanel.getAgeTextField().getText()
                ));

                updatedPatient.setGender(updatePatientPanel.getGenderTextField().getText());

                updatedPatient.setCity(updatePatientPanel.getCityTextField().getText());

                updatedPatient.setHeight(Double.parseDouble(
                        updatePatientPanel.getHeightTextField().getText()
                ));

                updatedPatient.setWeight(Double.parseDouble(
                        updatePatientPanel.getWeightTextField().getText()
                ));

                PatientDAO patientDAO = new PatientDAO();

                boolean isUpdated = patientDAO.updatePatient(
                        originalPatient,
                        updatedPatient
                );

                if (isUpdated) {

                    System.out.println(updatedPatient);

                    JOptionPane.showMessageDialog(
                            updatePatientPanel,
                            "Patient updated successfully!"
                    );

                    // refresh original object
                    originalPatient = updatedPatient;

                } else {

                    JOptionPane.showMessageDialog(
                            updatePatientPanel,
                            "No changes detected!"
                    );
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        updatePatientPanel,
                        "Please enter valid numeric values!"
                );

            } catch (Exception ex) {
                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                        updatePatientPanel,
                        "Something went wrong!"
                );
            }
        }
    }
}
