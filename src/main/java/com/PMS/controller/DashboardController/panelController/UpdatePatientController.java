package com.PMS.controller.DashboardController.panelController;

import com.PMS.DAO.PatientDAO;
import com.PMS.model.entity.Patient;
import com.PMS.view.dashboard.DashboardFrame;
import com.PMS.view.dashboard.panels.UpdatePatientPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * UpdatePatientController:
 *
 * Handles all operations related to updating an existing patient record.
 *
 * Main responsibilities:
 * 1. Search a patient using Patient ID.
 * 2. Display the patient's existing details.
 * 3. Allow the user to edit those details.
 * 4. Save the updated information to the database.
 * 5. Navigate back to the dashboard when required.
 *
 * This controller acts as a bridge between:
 * - UpdatePatientPanel (View)
 * - PatientDAO (Database operations)
 * - Patient Entity (Data object)
 *
 * Workflow:
 * Search Patient -> Load Details -> Enable Editing
 * -> Update Details -> Save Changes to Database
 */
public class UpdatePatientController implements ActionListener {

    // Reference to main dashboard window
    DashboardFrame dashboardFrame;

    // Reference to update patient form
    UpdatePatientPanel updatePatientPanel;

    /*
     * Stores the original patient retrieved from the database.
     *
     * This is important because:
     * - It allows comparison between old and new values.
     * - It preserves the original patient ID.
     * - It ensures the correct database record is updated.
     */
    private Patient originalPatient;

    /*
     * Constructor:
     * Connects controller with panel and registers button listeners.
     */
    public UpdatePatientController(DashboardFrame dashboardFrame, UpdatePatientPanel updatePatientPanel) {

        this.dashboardFrame = dashboardFrame;
        this.updatePatientPanel = updatePatientPanel;

        // Register controller as listener for all buttons
        updatePatientPanel.getSearchBtn().addActionListener(this);
        updatePatientPanel.getUpdateBtn().addActionListener(this);
        updatePatientPanel.getBackBtn().addActionListener(this);
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
        if (e.getSource() == updatePatientPanel.getBackBtn()) {

            /*
             * Clear all text fields before leaving the screen.
             * This prevents old patient data from appearing
             * when the panel is opened again.
             */
            updatePatientPanel.getIdTextField().setText("");
            updatePatientPanel.getNameTextField().setText("");
            updatePatientPanel.getAgeTextField().setText("");
            updatePatientPanel.getGenderTextField().setText("");
            updatePatientPanel.getCityTextField().setText("");
            updatePatientPanel.getHeightTextField().setText("");
            updatePatientPanel.getWeightTextField().setText("");

            // Get CardLayout used by dashboard content panel
            CardLayout c1 =
                    (CardLayout) dashboardFrame.getContentPanel().getLayout();

            // Switch back to welcome screen
            c1.show(
                    dashboardFrame.getContentPanel(),
                    "WELCOME"
            );
        }

        // ==================================================
        // SEARCH BUTTON LOGIC
        // ==================================================
        else if(e.getSource() ==  updatePatientPanel.getSearchBtn()) {

            try {

                /*
                 * Read patient ID entered by user and convert it
                 * from String to int.
                 */
                int searchId = Integer.parseInt(
                        updatePatientPanel.getIdTextField().getText()
                );

                // Create DAO object for database operations
                PatientDAO patientDAO = new PatientDAO();

                // Search patient record using ID
                Patient patient =
                        patientDAO.searchPatient(searchId);

                // Print patient details in console (debugging)
                System.out.println(patient);

                try {

                    /*
                     * If patient exists:
                     * - Store original record
                     * - Display data on screen
                     * - Allow editing
                     */
                    if (patient != null) {

                        /*
                         * Store original database record.
                         *
                         * This object is later used during update
                         * to identify which patient record must
                         * be modified.
                         */
                        originalPatient = patient;

                        // Display patient name
                        updatePatientPanel.getNameTextField()
                                .setText(patient.getName());

                        // Display patient age
                        updatePatientPanel.getAgeTextField()
                                .setText(
                                        String.valueOf(patient.getAge())
                                );

                        // Display patient gender
                        updatePatientPanel.getGenderTextField()
                                .setText(patient.getGender());

                        // Display patient city
                        updatePatientPanel.getCityTextField()
                                .setText(patient.getCity());

                        // Display patient height
                        updatePatientPanel.getHeightTextField()
                                .setText(
                                        String.valueOf(patient.getHeight())
                                );

                        // Display patient weight
                        updatePatientPanel.getWeightTextField()
                                .setText(
                                        String.valueOf(patient.getWeight())
                                );

                        /*
                         * Initially fields are non-editable.
                         *
                         * Once a valid patient is found,
                         * allow the user to modify values.
                         */
                        updatePatientPanel.getNameTextField()
                                .setEditable(true);

                        updatePatientPanel.getAgeTextField()
                                .setEditable(true);

                        updatePatientPanel.getGenderTextField()
                                .setEditable(true);

                        updatePatientPanel.getCityTextField()
                                .setEditable(true);

                        updatePatientPanel.getHeightTextField()
                                .setEditable(true);

                        updatePatientPanel.getWeightTextField()
                                .setEditable(true);

                    } else {

                        // No patient found with entered ID
                        JOptionPane.showMessageDialog(
                                updatePatientPanel,
                                "Patient not found"
                        );

                        return;
                    }

                } catch (Exception ex) {

                    // Print detailed error for debugging
                    ex.printStackTrace();

                    // Show generic message to user
                    JOptionPane.showMessageDialog(
                            updatePatientPanel,
                            "Something went wrong!"
                    );
                }

            } catch(NumberFormatException ex){

                /*
                 * Triggered when entered ID is not numeric.
                 * Example:
                 * abc
                 * 12a
                 * @#$%
                 */
                JOptionPane.showMessageDialog(
                        updatePatientPanel,
                        "Please enter a valid numeric ID"
                );
            }
        }

        // ==================================================
        // UPDATE BUTTON LOGIC
        // ==================================================
        else if (e.getSource() == updatePatientPanel.getUpdateBtn()) {

            try {

                /*
                 * Prevent update without searching first.
                 *
                 * If originalPatient is null,
                 * no patient has been loaded yet.
                 */
                if (originalPatient == null) {

                    JOptionPane.showMessageDialog(
                            updatePatientPanel,
                            "Please search a patient first!"
                    );

                    return;
                }

                /*
                 * Create a new Patient object containing
                 * updated values entered by the user.
                 */
                Patient updatedPatient = new Patient();

                /*
                 * Preserve original ID.
                 *
                 * ID is used to identify which database
                 * record must be updated.
                 */
                updatedPatient.setId(originalPatient.getId());

                // Read updated name
                updatedPatient.setName(
                        updatePatientPanel.getNameTextField().getText()
                );

                // Read updated age
                updatedPatient.setAge(
                        Integer.parseInt(
                                updatePatientPanel.getAgeTextField().getText()
                        )
                );

                // Read updated gender
                updatedPatient.setGender(
                        updatePatientPanel.getGenderTextField().getText()
                );

                // Read updated city
                updatedPatient.setCity(
                        updatePatientPanel.getCityTextField().getText()
                );

                // Read updated height
                updatedPatient.setHeight(
                        Double.parseDouble(
                                updatePatientPanel.getHeightTextField().getText()
                        )
                );

                // Read updated weight
                updatedPatient.setWeight(
                        Double.parseDouble(
                                updatePatientPanel.getWeightTextField().getText()
                        )
                );

                // Create DAO object
                PatientDAO patientDAO = new PatientDAO();

                /*
                 * Update database record.
                 *
                 * originalPatient = old values
                 * updatedPatient  = new values
                 */
                boolean isUpdated =
                        patientDAO.updatePatient(
                                originalPatient,
                                updatedPatient
                        );

                if (isUpdated) {

                    // Print updated data for debugging
                    System.out.println(updatedPatient);

                    JOptionPane.showMessageDialog(
                            updatePatientPanel,
                            "Patient updated successfully!"
                    );

                    /*
                     * Refresh originalPatient reference.
                     *
                     * This ensures future updates compare
                     * against latest saved values.
                     */
                    originalPatient = updatedPatient;

                } else {

                    /*
                     * No update occurred because all entered
                     * values are identical to existing values.
                     */
                    JOptionPane.showMessageDialog(
                            updatePatientPanel,
                            "No changes detected!"
                    );
                }

            } catch (NumberFormatException ex) {

                /*
                 * Triggered when numeric fields contain
                 * invalid values.
                 */
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