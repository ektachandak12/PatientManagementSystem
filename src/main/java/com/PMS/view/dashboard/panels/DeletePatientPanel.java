package com.PMS.view.dashboard.panels;

import javax.swing.*;
import java.awt.*;

/*
 * DeletePatientPanel:
 * Provides the user interface for deleting a patient record.
 * The user enters a patient ID and can either delete the record
 * or return to the previous screen.
 */
public class DeletePatientPanel extends JPanel {

    // Labels used in the panel
    JLabel heading, id;

    // Text field for entering patient ID
    JTextField idTextField;

    // Buttons for deleting record and navigating back
    JButton deleteBtn, backBtn;

    public DeletePatientPanel(){

        // Initialize and arrange all UI components
        initialize();
    }

    /*
     * Creates and arranges all components of the Delete Patient form.
     */
    public void initialize(){

        // Using null layout for manual component positioning
        setLayout(null);

        // Set panel background color
        setBackground(Color.WHITE);

        // Main heading of the panel
        heading = new JLabel("Delete Patient Record by ID");
        heading.setFont(new Font("Times New Roman", Font.BOLD, 25));
        heading.setBounds(80, 30, 400, 40);
        heading.setForeground(Color.BLACK);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        add(heading);

        // Common positioning variables used for alignment
        int labelX = 150;
        int textFieldX = 240;
        int labelWidth = 160;
        int textFieldWidth = 150;
        int componentHeight = 32;
        int y = 100;

        // Common fonts used throughout the form
        Font labelFont = new Font("Arial", Font.BOLD, 20);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 18);

        // ================= ID =================
        // User enters the patient ID to be deleted

        id = new JLabel("ID:");
        id.setBounds(labelX, y, labelWidth, componentHeight);
        id.setFont(labelFont);
        add(id);

        idTextField = new JTextField();
        idTextField.setBounds(textFieldX, y, textFieldWidth, componentHeight);
        idTextField.setFont(textFieldFont);
        add(idTextField);

        // ================= BUTTONS =================

        // Back Button (Left Side)
        // Returns user to the welcome screen

        backBtn = new JButton("Back");
        backBtn.setFont(new Font("Arial", Font.BOLD, 18));
        backBtn.setBounds(110, 405, 130, 40);
        add(backBtn);

        // Delete Button (Right Side)
        // Deletes the patient record associated with the entered ID

        deleteBtn = new JButton("Delete");
        deleteBtn.setFont(new Font("Arial", Font.BOLD, 18));
        deleteBtn.setBounds(290, 405, 130, 40);
        add(deleteBtn);
    }

    // Returns patient ID input field
    public JTextField getIdTextField() {
        return idTextField;
    }

    // Returns Delete button reference
    public JButton getDeleteBtn() {
        return deleteBtn;
    }

    // Returns Back button reference
    public JButton getBackBtn() {
        return backBtn;
    }

}