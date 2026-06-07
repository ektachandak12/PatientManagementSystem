package com.PMS.view.dashboard.panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewAllPatientsPanel extends JPanel {

    JLabel heading;

    JTable patientTable;
    JScrollPane scrollPane;

    JButton backBtn;

    public ViewAllPatientsPanel() {
        initialize();
    }

    public void initialize() {

        // Using null layout for custom positioning
        setLayout(null);

        // Set panel background color
        setBackground(Color.WHITE);

        // ================= HEADING =================

        heading = new JLabel("View All Patients");
        heading.setFont(new Font("Times New Roman", Font.BOLD, 30));
        heading.setBounds(110, 25, 300, 40);
        heading.setForeground(Color.BLACK);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        add(heading);

        // ================= TABLE =================

        String[] columns = {
                "ID",
                "Name",
                "Age",
                "Gender"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        patientTable = new JTable(model);

        patientTable.setRowHeight(25);
        patientTable.setFont(new Font("Times New Roman", Font.BOLD, 14));

        scrollPane = new JScrollPane(patientTable);
        scrollPane.setBounds(10, 90, 480, 250);

        add(scrollPane);

        // ================= BACK BUTTON =================

        backBtn = new JButton("Back");
        backBtn.setFont(new Font("Arial", Font.BOLD, 18));
        backBtn.setBounds(180, 370, 130, 40);

        add(backBtn);
    }

    public JTable getPatientTable() {
        return patientTable;
    }

    public JButton getBackBtn() {
        return backBtn;
    }
}