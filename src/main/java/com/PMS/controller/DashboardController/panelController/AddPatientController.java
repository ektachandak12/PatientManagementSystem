package com.PMS.controller.DashboardController.panelController;

import com.PMS.DAO.PatientDAO;
import com.PMS.model.entity.Patient;
import com.PMS.view.dashboard.DashboardFrame;
import com.PMS.view.dashboard.panels.AddPatientPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * AddPatientController:
 * Handles all actions related to adding a new patient.
 * It validates user input, calculates BMI, creates a Patient object,
 * and saves the patient record into the database.
 */
public class AddPatientController implements ActionListener {

    // Reference to the main dashboard window
    DashboardFrame dashboardFrame;

    // Reference to the Add Patient panel
    AddPatientPanel addPatientPanel;

    public AddPatientController(DashboardFrame dashboardFrame, AddPatientPanel addPatientPanel) {

        this.dashboardFrame = dashboardFrame;
        this.addPatientPanel = addPatientPanel;

        // Register action listeners for panel buttons
        addPatientPanel.getBackBtn().addActionListener(this);
        addPatientPanel.getSaveBtn().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Handles Back button click
        if (e.getSource() == addPatientPanel.getBackBtn()) {

            // Get CardLayout used by dashboard content panel
            CardLayout c1 = (CardLayout) dashboardFrame.getContentPanel().getLayout();

            // Navigate back to welcome screen
            c1.show(dashboardFrame.getContentPanel(), "WELCOME");
        }

        // Handles Save button click
        else if (e.getSource() == addPatientPanel.getSaveBtn()) {

            // Read all user inputs from form fields
            String name = addPatientPanel.getNameTextField().getText().trim();
            String age = addPatientPanel.getAgeTextField().getText().trim();

            // Variable to store selected gender
            String gender = null;

            // Determine which gender radio button is selected
            if (addPatientPanel.getMale().isSelected()) {
                gender = "Male";
            }
            else if (addPatientPanel.getFemale().isSelected()) {
                gender = "Female";
            }
            else if (addPatientPanel.getOther().isSelected()) {
                gender = "Other";
            }

            String city = addPatientPanel.getCityTextField().getText().trim();
            String height = addPatientPanel.getHeightTextField().getText().trim();
            String weight = addPatientPanel.getWeightTextField().getText().trim();

            // ================= EMPTY FIELD VALIDATION =================
            // Ensure all mandatory fields are filled

            if (name.isEmpty() || age.isEmpty() || city.isEmpty()
                    || height.isEmpty() || weight.isEmpty()) {

                JOptionPane.showMessageDialog(
                        addPatientPanel,
                        "Please enter all fields!"
                );
                return;
            }

            // ================= GENDER VALIDATION =================
            // Ensure a gender option is selected

            if (gender == null) {

                JOptionPane.showMessageDialog(
                        addPatientPanel,
                        "Please select gender!"
                );
                return;
            }

            // ================= NAME VALIDATION =================
            // Name should contain only alphabets and spaces

            if (!name.matches("[a-zA-Z ]+")) {

                JOptionPane.showMessageDialog(
                        addPatientPanel,
                        "Name should contain only letters!"
                );
                return;
            }

            // ================= CITY VALIDATION =================
            // City should contain only alphabets and spaces

            if (!city.matches("[a-zA-Z ]+")) {

                JOptionPane.showMessageDialog(
                        addPatientPanel,
                        "City should contain only letters!"
                );
                return;
            }

            // ================= AGE VALIDATION =================
            // Age should contain only numeric digits

            if (!age.matches("\\d+")) {

                JOptionPane.showMessageDialog(
                        addPatientPanel,
                        "Age should contain only digits!"
                );
                return;
            }

            // Convert validated age string to integer
            int patientAge = Integer.parseInt(age);

            // Accept age only within a realistic human age range
            if (patientAge < 0 || patientAge > 120) {

                JOptionPane.showMessageDialog(
                        addPatientPanel,
                        "Please enter a valid age!"
                );
                return;
            }

            // ================= HEIGHT VALIDATION =================
            // Height should be a valid decimal number

            if (!height.matches("\\d+(\\.\\d+)?")) {

                JOptionPane.showMessageDialog(
                        addPatientPanel,
                        "Height should be a valid number in metres!"
                );
                return;
            }

            // Convert validated height string to double
            double patientHeight = Double.parseDouble(height);

            // Restrict height to realistic values
            if (patientHeight < 0.5 || patientHeight > 2.5) {

                JOptionPane.showMessageDialog(
                        addPatientPanel,
                        "Height should be between 0.5 m and 2.5 m!"
                );
                return;
            }

            // ================= WEIGHT VALIDATION =================
            // Weight should be a valid decimal number

            if (!weight.matches("\\d+(\\.\\d+)?")) {

                JOptionPane.showMessageDialog(
                        addPatientPanel,
                        "Weight should be a valid number!"
                );
                return;
            }

            // Convert validated weight string to double
            double patientWeight = Double.parseDouble(weight);

            // Restrict weight to realistic values
            if (patientWeight < 2 || patientWeight > 300) {

                JOptionPane.showMessageDialog(
                        addPatientPanel,
                        "Weight should be between 2 kg and 300 kg!"
                );
                return;
            }

            // ================= CREATE PATIENT OBJECT =================
            // Create a Patient object and store all validated values

            Patient patient = new Patient();

            patient.setName(name);
            patient.setAge(patientAge);
            patient.setGender(gender);
            patient.setCity(city);
            patient.setHeight(patientHeight);
            patient.setWeight(patientWeight);

            // ================= BMI CALCULATION =================
            // BMI Formula = Weight / Height²

            double bmi = patientWeight / Math.pow(patientHeight, 2);

            // Round BMI value to 2 decimal places
            bmi = Math.round(bmi * 100.0) / 100.0;

            // Store BMI in patient object
            patient.setBmi(bmi);

            // Determine BMI category based on BMI value
            String bmiCategory;

            if (bmi < 18.5) {
                bmiCategory = "Underweight";
            }
            else if (bmi >= 18.5 && bmi <= 24.9) {
                bmiCategory = "Healthy";
            }
            else if (bmi >= 25.0 && bmi <= 29.9) {
                bmiCategory = "Overweight";
            }
            else {
                bmiCategory = "Obese";
            }

            // Store BMI category in patient object
            patient.setCategory(bmiCategory);

            // ================= SAVE TO DATABASE =================
            // Save patient record into database

            PatientDAO patientDAO = new PatientDAO();

            patientDAO.addPatient(patient);

            // Print success message in console for debugging
            System.out.println("Patient saved successfully!");

            // Show success message to user
            JOptionPane.showMessageDialog(
                    dashboardFrame,
                    "PATIENT ADDED SUCCESSFULLY"
            );

            // ================= CLEAR FIELDS =================
            // Reset form after successful save

            addPatientPanel.getNameTextField().setText("");
            addPatientPanel.getAgeTextField().setText("");
            addPatientPanel.getCityTextField().setText("");
            addPatientPanel.getHeightTextField().setText("");
            addPatientPanel.getWeightTextField().setText("");

            // Clear selected gender option
            addPatientPanel.getMale().setSelected(false);
            addPatientPanel.getFemale().setSelected(false);
            addPatientPanel.getOther().setSelected(false);
        }
    }
}