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
 *
 * Handles all operations related to adding a new patient.
 *
 * Responsibilities:
 * 1. Read patient details entered by the user.
 * 2. Validate all input fields.
 * 3. Create a Patient object.
 * 4. Save the patient record into the database.
 * 5. Clear the form after successful insertion.
 * 6. Navigate back to the dashboard when required.
 *
 * This controller acts as a bridge between:
 * - AddPatientPanel (View)
 * - Patient Entity (Data Object)
 * - PatientDAO (Database Layer)
 *
 * Workflow:
 * User enters details
 * -> Validations performed
 * -> Patient object created
 * -> Data saved to database
 * -> Success message displayed
 * -> Form cleared
 */
public class AddPatientController implements ActionListener {

    // Reference to main dashboard window
    DashboardFrame dashboardFrame;

    // Reference to Add Patient form
    AddPatientPanel addPatientPanel;

    /*
     * Constructor:
     * Connects controller with panel and registers
     * action listeners for buttons.
     */
    public AddPatientController(
            DashboardFrame dashboardFrame,
            AddPatientPanel addPatientPanel) {

        this.dashboardFrame = dashboardFrame;
        this.addPatientPanel = addPatientPanel;

        // Register button listeners
        addPatientPanel.getBackBtn().addActionListener(this);
        addPatientPanel.getSaveBtn().addActionListener(this);
    }

    /*
     * actionPerformed():
     * Automatically executes whenever a registered
     * button is clicked.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        // ==================================================
        // BACK BUTTON LOGIC
        // ==================================================
        if (e.getSource() == addPatientPanel.getBackBtn()) {

            /*
             * Get CardLayout controlling dashboard panels.
             */
            CardLayout c1 =
                    (CardLayout) dashboardFrame
                            .getContentPanel()
                            .getLayout();

            /*
             * Switch back to welcome screen.
             */
            c1.show(
                    dashboardFrame.getContentPanel(),
                    "WELCOME"
            );
        }

        // ==================================================
        // SAVE BUTTON LOGIC
        // ==================================================
        else if (e.getSource() == addPatientPanel.getSaveBtn()) {

            /*
             * Read values entered by the user.
             *
             * trim() removes leading and trailing spaces.
             */
            String name =
                    addPatientPanel.getNameTextField()
                            .getText()
                            .trim();

            String age =
                    addPatientPanel.getAgeTextField()
                            .getText()
                            .trim();

            String city =
                    addPatientPanel.getCityTextField()
                            .getText()
                            .trim();

            String height =
                    addPatientPanel.getHeightTextField()
                            .getText()
                            .trim();

            String weight =
                    addPatientPanel.getWeightTextField()
                            .getText()
                            .trim();

            /*
             * Determine selected gender.
             *
             * Only one radio button can be selected
             * because they belong to the same ButtonGroup.
             */
            String gender = null;

            if (addPatientPanel.getMale().isSelected())
                gender = "Male";

            else if (addPatientPanel.getFemale().isSelected())
                gender = "Female";

            else if (addPatientPanel.getOther().isSelected())
                gender = "Other";

            // ==================================================
            // VALIDATIONS
            // ==================================================

            /*
             * Ensure all mandatory fields are filled.
             */
            if (name.isEmpty() || age.isEmpty() || city.isEmpty()
                    || height.isEmpty() || weight.isEmpty()) {

                JOptionPane.showMessageDialog(
                        addPatientPanel,
                        "Please enter all fields!"
                );

                return;
            }

            /*
             * Ensure gender is selected.
             */
            if (gender == null) {

                JOptionPane.showMessageDialog(
                        addPatientPanel,
                        "Please select gender!"
                );

                return;
            }

            /*
             * Name should contain only alphabets and spaces.
             *
             * Valid:
             * John
             * Mary Jane
             *
             * Invalid:
             * John123
             * John@
             */
            if (!name.matches("[a-zA-Z ]+")) {

                JOptionPane.showMessageDialog(
                        addPatientPanel,
                        "Invalid name!"
                );

                return;
            }

            /*
             * City should contain only alphabets and spaces.
             */
            if (!city.matches("[a-zA-Z ]+")) {

                JOptionPane.showMessageDialog(
                        addPatientPanel,
                        "Invalid city!"
                );

                return;
            }

            /*
             * Age must contain only digits.
             */
            if (!age.matches("\\d+")) {

                JOptionPane.showMessageDialog(
                        addPatientPanel,
                        "Invalid age!"
                );

                return;
            }

            /*
             * Convert age to integer after validation.
             */
            int patientAge = Integer.parseInt(age);

            /*
             * Restrict age to realistic human values.
             */
            if (patientAge < 0 || patientAge > 120) {

                JOptionPane.showMessageDialog(
                        addPatientPanel,
                        "Invalid age range!"
                );

                return;
            }

            /*
             * Height validation.
             *
             * Accepts:
             * 1
             * 1.75
             * 2.0
             */
            if (!height.matches("\\d+(\\.\\d+)?")) {

                JOptionPane.showMessageDialog(
                        addPatientPanel,
                        "Invalid height!"
                );

                return;
            }

            double patientHeight =
                    Double.parseDouble(height);

            /*
             * Restrict height to realistic values.
             */
            if (patientHeight < 0.5 || patientHeight > 2.5) {

                JOptionPane.showMessageDialog(
                        addPatientPanel,
                        "Invalid height range!"
                );

                return;
            }

            /*
             * Weight validation.
             */
            if (!weight.matches("\\d+(\\.\\d+)?")) {

                JOptionPane.showMessageDialog(
                        addPatientPanel,
                        "Invalid weight!"
                );

                return;
            }

            double patientWeight =
                    Double.parseDouble(weight);

            /*
             * Restrict weight to realistic values.
             */
            if (patientWeight < 2 || patientWeight > 300) {

                JOptionPane.showMessageDialog(
                        addPatientPanel,
                        "Invalid weight range!"
                );

                return;
            }

            // ==================================================
            // CREATE PATIENT OBJECT
            // ==================================================

            /*
             * Create a Patient object and store
             * validated user input.
             */
            Patient patient = new Patient();

            patient.setName(name);
            patient.setAge(patientAge);
            patient.setGender(gender);
            patient.setCity(city);
            patient.setHeight(patientHeight);
            patient.setWeight(patientWeight);

            /*
             * BMI is NOT calculated here.
             *
             * BMI calculation and BMI category assignment
             * are handled inside the DAO layer before
             * saving data to the database.
             */
            // NO BMI LOGIC HERE

            // ==================================================
            // SAVE PATIENT
            // ==================================================

            /*
             * Create DAO object responsible for
             * database operations.
             */
            PatientDAO patientDAO = new PatientDAO();

            /*
             * Save patient record into database.
             */
            patientDAO.addPatient(patient);

            JOptionPane.showMessageDialog(
                    dashboardFrame,
                    "PATIENT ADDED SUCCESSFULLY"
            );

            // ==================================================
            // CLEAR FORM
            // ==================================================

            /*
             * Clear all text fields so that
             * next patient can be entered easily.
             */
            addPatientPanel.getNameTextField().setText("");
            addPatientPanel.getAgeTextField().setText("");
            addPatientPanel.getCityTextField().setText("");
            addPatientPanel.getHeightTextField().setText("");
            addPatientPanel.getWeightTextField().setText("");

            /*
             * Remove gender selection.
             */
            addPatientPanel.getMale().setSelected(false);
            addPatientPanel.getFemale().setSelected(false);
            addPatientPanel.getOther().setSelected(false);
        }
    }
}