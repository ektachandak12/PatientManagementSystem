package com.PMS.view.dashboard.panels;

import javax.swing.*;
import java.awt.*;

public class UpdatePatientPanel extends JPanel {
    // Labels used to display field names
    JLabel heading, id, name, age, gender, city, height, weight, bmi, bmiCategory;

    // Text field used to enter patient ID for searching
    JTextField idTextField;

    // Buttons for searching patient and returning to previous screen
    JButton searchBtn, updateBtn, backBtn;

    // Text fields used to display retrieved patient details
    JTextField nameTextField;
    JTextField ageTextField;
    JTextField cityTextField;
    JTextField genderTextField;
    JTextField heightTextField;
    JTextField weightTextField;
    JTextField bmiTextField;
    JTextField bmiCategoryTextField;

    public UpdatePatientPanel(){
        initialize();
    }

    /*
     * Creates and arranges all UI components of the panel.
     */
    private void initialize(){

        // Using null layout for manual component positioning
        setLayout(null);

        // Set panel background color
        setBackground(Color.WHITE);

        // Main heading of the panel
        heading = new JLabel("Update Patient Record");
        heading.setFont(new Font("Times New Roman", Font.BOLD, 30));
        heading.setBounds(110, 20, 320, 40);
        heading.setForeground(Color.BLACK);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        add(heading);

        // Common positioning variables used for alignment
        int labelX = 80;
        int textFieldX = 240;
        int labelWidth = 160;
        int textFieldWidth = 220;
        int componentHeight = 32;
        int y = 90;
        int verticalGap = 40;

        // Common fonts used throughout the form
        Font labelFont = new Font("Arial", Font.BOLD, 18);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 16);

        // ================= ID =================
        // User enters patient ID here

        id = new JLabel("ID:");
        id.setBounds(100, y, 50, componentHeight);
        id.setFont(labelFont);
        add(id);

        idTextField = new JTextField();
        idTextField.setBounds(textFieldX, y, 90, componentHeight);
        idTextField.setFont(textFieldFont);
        add(idTextField);

        // ================= NAME =================
        // Displays patient name after search

        y += verticalGap;

        name = new JLabel("Name:");
        name.setBounds(labelX, y, labelWidth, componentHeight);
        name.setFont(labelFont);
        add(name);

        nameTextField = new JTextField();
        nameTextField.setBounds(textFieldX, y, textFieldWidth, componentHeight);
        nameTextField.setFont(textFieldFont);

        // Prevent user from editing retrieved data
        nameTextField.setEditable(false);
        add(nameTextField);

        // ================= AGE =================
        // Displays patient age

        y += verticalGap;

        age = new JLabel("Age:");
        age.setBounds(labelX, y, labelWidth, componentHeight);
        age.setFont(labelFont);
        add(age);

        ageTextField = new JTextField();
        ageTextField.setBounds(textFieldX, y, textFieldWidth, componentHeight);
        ageTextField.setFont(textFieldFont);
        ageTextField.setEditable(false);
        add(ageTextField);

        // ================= GENDER =================
        // Displays patient gender

        y += verticalGap;

        gender = new JLabel("Gender:");
        gender.setBounds(labelX, y, labelWidth, componentHeight);
        gender.setFont(labelFont);
        add(gender);

        genderTextField = new JTextField();
        genderTextField.setBounds(textFieldX, y, textFieldWidth, componentHeight);
        genderTextField.setFont(textFieldFont);
        genderTextField.setEditable(false);
        add(genderTextField);

        // ================= CITY =================
        // Displays patient city

        y += verticalGap;

        city = new JLabel("City:");
        city.setBounds(labelX, y, labelWidth, componentHeight);
        city.setFont(labelFont);
        add(city);

        cityTextField = new JTextField();
        cityTextField.setBounds(textFieldX, y, textFieldWidth, componentHeight);
        cityTextField.setFont(textFieldFont);
        cityTextField.setEditable(false);
        add(cityTextField);

        // ================= HEIGHT =================
        // Displays patient height in metres

        y += verticalGap;

        height = new JLabel("Height (in m):");
        height.setBounds(labelX, y, labelWidth, componentHeight);
        height.setFont(labelFont);
        add(height);

        heightTextField = new JTextField();
        heightTextField.setBounds(textFieldX, y, textFieldWidth, componentHeight);
        heightTextField.setFont(textFieldFont);
        heightTextField.setEditable(false);
        add(heightTextField);

        // ================= WEIGHT =================
        // Displays patient weight in kilograms

        y += verticalGap;

        weight = new JLabel("Weight (in kg):");
        weight.setBounds(labelX, y, labelWidth, componentHeight);
        weight.setFont(labelFont);
        add(weight);

        weightTextField = new JTextField();
        weightTextField.setBounds(textFieldX, y, textFieldWidth, componentHeight);
        weightTextField.setFont(textFieldFont);
        weightTextField.setEditable(false);
        add(weightTextField);


        // Back Button (Left Side)
        // Returns user to the welcome screen

        backBtn = new JButton("Back");
        backBtn.setFont(new Font("Arial", Font.BOLD, 18));
        backBtn.setBounds(110, 390, 130, 40);
        add(backBtn);

        // Search Button (Right Side)
        // Searches patient using entered ID

        searchBtn = new JButton("Search");
        searchBtn.setFont(new Font("Arial", Font.BOLD, 18));
        searchBtn.setBounds(340, 90, 120, 32);
        add(searchBtn);

        updateBtn = new JButton("Update");
        updateBtn.setFont(new Font("Arial", Font.BOLD, 18));
        updateBtn.setBounds(290, 390, 130, 40);
        add(updateBtn);

    }

    // Returns entered patient ID field
    public JTextField getIdTextField() {
        return idTextField;
    }

    // Returns name display field
    public JTextField getNameTextField() {
        return nameTextField;
    }

    // Returns age display field
    public JTextField getAgeTextField() {
        return ageTextField;
    }

    // Returns gender display field
    public JTextField getGenderTextField() {
        return genderTextField;
    }

    // Returns city display field
    public JTextField getCityTextField() {
        return cityTextField;
    }

    // Returns height display field
    public JTextField getHeightTextField() {
        return heightTextField;
    }

    // Returns weight display field
    public JTextField getWeightTextField() {
        return weightTextField;
    }

    // Setter methods used to update UI components if required

    public void setNameTextField(JTextField nameTextField) {
        this.nameTextField = nameTextField;
    }

    public void setAgeTextField(JTextField ageTextField) {
        this.ageTextField = ageTextField;
    }

    public void setCityTextField(JTextField cityTextField) {
        this.cityTextField = cityTextField;
    }

    public void setGenderTextField(JTextField genderTextField) {
        this.genderTextField = genderTextField;
    }

    public void setHeightTextField(JTextField heightTextField) {
        this.heightTextField = heightTextField;
    }

    public void setWeightTextField(JTextField weightTextField) {
        this.weightTextField = weightTextField;
    }

    public JButton getSearchBtn(){
        return searchBtn;
    }

    // Returns Save button reference
    public JButton getUpdateBtn() {
        return updateBtn;
    }

    // Returns Back button reference
    public JButton getBackBtn() {
        return backBtn;
    }
}
