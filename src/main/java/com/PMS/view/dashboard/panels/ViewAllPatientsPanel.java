package com.PMS.view.dashboard.panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/*
 * ViewAllPatientsPanel:
 * This panel displays all patient records in a table format.
 * It contains:
 * - A heading to indicate the purpose of the screen.
 * - A JTable to show patient details retrieved from the database.
 * - A scroll pane to allow viewing large numbers of records.
 * - A Back button to return to the dashboard welcome screen.
 *
 * The table is read-only, meaning users can view data
 * but cannot directly edit it from the table.
 */
public class ViewAllPatientsPanel extends JPanel {

    // Panel heading
    JLabel heading;

    // Table used to display patient records
    JTable patientTable;

    // Adds scrolling support to the table
    JScrollPane scrollPane;

    // Button used to navigate back
    JButton backBtn;

    public ViewAllPatientsPanel() {
        initialize();
    }

    /*
     * Creates and arranges all UI components
     * required for displaying patient records.
     */
    public void initialize() {

        // Using null layout for custom positioning
        setLayout(null);

        // Set panel background color
        setBackground(Color.WHITE);

        // ================= HEADING =================

        // Main title displayed at the top of the panel
        heading = new JLabel("View All Patients");
        heading.setFont(new Font("Times New Roman", Font.BOLD, 30));
        heading.setBounds(110, 25, 300, 40);
        heading.setForeground(Color.BLACK);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        add(heading);

        // ================= TABLE =================

        // Column names displayed in JTable header
        String[] columns = {
                "ID",
                "Name",
                "Age",
                "Gender"
        };

        /*
         * Create table model with no initial rows.
         * Data will be added dynamically by the controller.
         */
        DefaultTableModel model = new DefaultTableModel(columns, 0) {

            /*
             * Prevent users from editing table cells.
             * Table is used only for viewing records.
             */
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Create JTable using the table model
        patientTable = new JTable(model);

        // Improve table appearance
        patientTable.setRowHeight(25);
        patientTable.setFont(new Font("Times New Roman", Font.BOLD, 14));

        /*
         * Wrap JTable inside JScrollPane.
         * This allows scrolling when many records exist.
         */
        scrollPane = new JScrollPane(patientTable);
        scrollPane.setBounds(10, 90, 480, 250);

        add(scrollPane);

        // ================= BACK BUTTON =================

        // Returns user to previous screen/dashboard
        backBtn = new JButton("Back");
        backBtn.setFont(new Font("Arial", Font.BOLD, 18));
        backBtn.setBounds(180, 370, 130, 40);

        add(backBtn);
    }

    /*
     * Returns JTable reference.
     * Controller uses this to populate patient data.
     */
    public JTable getPatientTable() {
        return patientTable;
    }

    /*
     * Returns Back button reference.
     * Controller attaches action listener to it.
     */
    public JButton getBackBtn() {
        return backBtn;
    }
}