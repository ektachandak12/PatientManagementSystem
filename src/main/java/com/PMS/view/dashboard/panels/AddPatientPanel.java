package com.PMS.view.dashboard.panels;

import javax.swing.*;
import java.awt.*;

public class AddPatientPanel extends JPanel {

    JLabel heading, name, age, gender, city, height, weight;

    JTextField nameTextField;
    JTextField ageTextField;
    JTextField cityTextField;
    JTextField heightTextField;
    JTextField weightTextField;

    JRadioButton male, female, other;

    JButton saveBtn, backBtn;

    public AddPatientPanel() {

        initialize();
    }

    private void initialize() {

        // Using null layout for custom positioning
        setLayout(null);

        // Background color
        setBackground(Color.WHITE);

        /*
         * Dashboard Size = 700
         * Sidebar Width = 180
         *
         * Remaining Width for content panel ≈ 520
         */

        // ================= HEADING =================

        heading = new JLabel("Add Patient Details");
        heading.setFont(new Font("Times New Roman", Font.BOLD, 30));
        heading.setBounds(110, 25, 320, 40);
        heading.setForeground(Color.BLACK);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        add(heading);

        // ================= COMMON VARIABLES =================

        int labelX = 60;
        int textFieldX = 240;
        int labelWidth = 160;
        int textFieldWidth = 220;
        int componentHeight = 32;
        int y = 100;
        int verticalGap = 48;

        Font labelFont = new Font("Arial", Font.BOLD, 18);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 16);

        // ================= NAME =================

        name = new JLabel("Name:");
        name.setBounds(labelX, y, labelWidth, componentHeight);
        name.setFont(labelFont);
        add(name);

        nameTextField = new JTextField();
        nameTextField.setBounds(textFieldX, y, textFieldWidth, componentHeight);
        nameTextField.setFont(textFieldFont);
        add(nameTextField);

        // ================= AGE =================

        y += verticalGap;

        age = new JLabel("Age:");
        age.setBounds(labelX, y, labelWidth, componentHeight);
        age.setFont(labelFont);
        add(age);

        ageTextField = new JTextField();
        ageTextField.setBounds(textFieldX, y, textFieldWidth, componentHeight);
        ageTextField.setFont(textFieldFont);
        add(ageTextField);

        // ================= GENDER =================

        y += verticalGap;

        gender = new JLabel("Gender:");
        gender.setBounds(labelX, y, labelWidth, componentHeight);
        gender.setFont(labelFont);
        add(gender);

        male = new JRadioButton("Male");
        male.setBounds(textFieldX, y, 70, 30);
        male.setBackground(Color.WHITE);
        male.setFont(new Font("Arial", Font.PLAIN, 15));

        female = new JRadioButton("Female");
        female.setBounds(textFieldX + 75, y, 90, 30);
        female.setBackground(Color.WHITE);
        female.setFont(new Font("Arial", Font.PLAIN, 15));

        other = new JRadioButton("Other");
        other.setBounds(textFieldX + 165, y, 80, 30);
        other.setBackground(Color.WHITE);
        other.setFont(new Font("Arial", Font.PLAIN, 15));

        // Group radio buttons
        ButtonGroup bg = new ButtonGroup();

        bg.add(male);
        bg.add(female);
        bg.add(other);

        add(male);
        add(female);
        add(other);

        // ================= CITY =================

        y += verticalGap;

        city = new JLabel("City:");
        city.setBounds(labelX, y, labelWidth, componentHeight);
        city.setFont(labelFont);
        add(city);

        cityTextField = new JTextField();
        cityTextField.setBounds(textFieldX, y, textFieldWidth, componentHeight);
        cityTextField.setFont(textFieldFont);
        add(cityTextField);

        // ================= HEIGHT =================

        y += verticalGap;

        height = new JLabel("Height (in cm):");
        height.setBounds(labelX, y, labelWidth, componentHeight);
        height.setFont(labelFont);
        add(height);

        heightTextField = new JTextField();
        heightTextField.setBounds(textFieldX, y, textFieldWidth, componentHeight);
        heightTextField.setFont(textFieldFont);
        add(heightTextField);

        // ================= WEIGHT =================

        y += verticalGap;

        weight = new JLabel("Weight (in kg):");
        weight.setBounds(labelX, y, labelWidth, componentHeight);
        weight.setFont(labelFont);
        add(weight);

        weightTextField = new JTextField();
        weightTextField.setBounds(textFieldX, y, textFieldWidth, componentHeight);
        weightTextField.setFont(textFieldFont);
        add(weightTextField);

        // ================= BUTTONS =================

        // Back Button (Left Side)

        backBtn = new JButton("Back");
        backBtn.setFont(new Font("Arial", Font.BOLD, 18));
        backBtn.setBounds(110, 395, 130, 40);
        add(backBtn);

        // Save Button (Right Side)

        saveBtn = new JButton("Save");
        saveBtn.setFont(new Font("Arial", Font.BOLD, 18));
        saveBtn.setBounds(290, 395, 130, 40);
        add(saveBtn);
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JTextField getAgeTextField() {
        return ageTextField;
    }

    public JTextField getCityTextField() {
        return cityTextField;
    }

    public JTextField getHeightTextField() {
        return heightTextField;
    }

    public JTextField getWeightTextField() {
        return weightTextField;
    }

    public JRadioButton getFemale() {
        return female;
    }

    public JRadioButton getMale() {
        return male;
    }

    public JRadioButton getOther() {
        return other;
    }

    public JButton getSaveBtn() {
        return saveBtn;
    }

    public JButton getBackBtn() {
        return backBtn;
    }
}