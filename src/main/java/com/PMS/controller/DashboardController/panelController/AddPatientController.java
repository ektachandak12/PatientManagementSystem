package com.PMS.controller.DashboardController.panelController;

import com.PMS.DAO.PatientDAO;
import com.PMS.model.entity.Patient;
import com.PMS.view.dashboard.DashboardFrame;
import com.PMS.view.dashboard.panels.AddPatientPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPatientController implements ActionListener {

    DashboardFrame dashboardFrame;
    AddPatientPanel addPatientPanel;

    public AddPatientController(DashboardFrame dashboardFrame, AddPatientPanel addPatientPanel) {

        this.dashboardFrame = dashboardFrame;
        this.addPatientPanel = addPatientPanel;

        addPatientPanel.getBackBtn().addActionListener(this);
        addPatientPanel.getSaveBtn().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addPatientPanel.getBackBtn()) {

            CardLayout c1 = (CardLayout) dashboardFrame.getContentPanel().getLayout();
            c1.show(dashboardFrame.getContentPanel(), "WELCOME");
        }

        else if (e.getSource() == addPatientPanel.getSaveBtn()) {

            String name = addPatientPanel.getNameTextField().getText().trim();
            String age = addPatientPanel.getAgeTextField().getText().trim();
            String city = addPatientPanel.getCityTextField().getText().trim();
            String height = addPatientPanel.getHeightTextField().getText().trim();
            String weight = addPatientPanel.getWeightTextField().getText().trim();

            String gender = null;

            if (addPatientPanel.getMale().isSelected()) gender = "Male";
            else if (addPatientPanel.getFemale().isSelected()) gender = "Female";
            else if (addPatientPanel.getOther().isSelected()) gender = "Other";

            // ================= VALIDATIONS =================

            if (name.isEmpty() || age.isEmpty() || city.isEmpty()
                    || height.isEmpty() || weight.isEmpty()) {

                JOptionPane.showMessageDialog(addPatientPanel, "Please enter all fields!");
                return;
            }

            if (gender == null) {
                JOptionPane.showMessageDialog(addPatientPanel, "Please select gender!");
                return;
            }

            if (!name.matches("[a-zA-Z ]+")) {
                JOptionPane.showMessageDialog(addPatientPanel, "Invalid name!");
                return;
            }

            if (!city.matches("[a-zA-Z ]+")) {
                JOptionPane.showMessageDialog(addPatientPanel, "Invalid city!");
                return;
            }

            if (!age.matches("\\d+")) {
                JOptionPane.showMessageDialog(addPatientPanel, "Invalid age!");
                return;
            }

            int patientAge = Integer.parseInt(age);

            if (patientAge < 0 || patientAge > 120) {
                JOptionPane.showMessageDialog(addPatientPanel, "Invalid age range!");
                return;
            }

            if (!height.matches("\\d+(\\.\\d+)?")) {
                JOptionPane.showMessageDialog(addPatientPanel, "Invalid height!");
                return;
            }

            double patientHeight = Double.parseDouble(height);

            if (patientHeight < 0.5 || patientHeight > 2.5) {
                JOptionPane.showMessageDialog(addPatientPanel, "Invalid height range!");
                return;
            }

            if (!weight.matches("\\d+(\\.\\d+)?")) {
                JOptionPane.showMessageDialog(addPatientPanel, "Invalid weight!");
                return;
            }

            double patientWeight = Double.parseDouble(weight);

            if (patientWeight < 2 || patientWeight > 300) {
                JOptionPane.showMessageDialog(addPatientPanel, "Invalid weight range!");
                return;
            }

            // ================= CREATE PATIENT OBJECT =================

            Patient patient = new Patient();
            patient.setName(name);
            patient.setAge(patientAge);
            patient.setGender(gender);
            patient.setCity(city);
            patient.setHeight(patientHeight);
            patient.setWeight(patientWeight);

            // ❌ NO BMI LOGIC HERE (handled by DAO)

            // ================= SAVE =================

            PatientDAO patientDAO = new PatientDAO();
            patientDAO.addPatient(patient);

            JOptionPane.showMessageDialog(
                    dashboardFrame,
                    "PATIENT ADDED SUCCESSFULLY"
            );

            // ================= CLEAR FORM =================

            addPatientPanel.getNameTextField().setText("");
            addPatientPanel.getAgeTextField().setText("");
            addPatientPanel.getCityTextField().setText("");
            addPatientPanel.getHeightTextField().setText("");
            addPatientPanel.getWeightTextField().setText("");

            addPatientPanel.getMale().setSelected(false);
            addPatientPanel.getFemale().setSelected(false);
            addPatientPanel.getOther().setSelected(false);
        }
    }
}