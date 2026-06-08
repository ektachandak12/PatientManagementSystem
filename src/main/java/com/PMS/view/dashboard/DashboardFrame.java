package com.PMS.view.dashboard;

import com.PMS.controller.DashboardController.panelController.*;
import com.PMS.view.dashboard.panels.*;

import javax.swing.*;
import java.awt.*;

/*
 * DashboardFrame:
 * Main dashboard window displayed after successful login.
 * Contains sidebar navigation and content display area.
 */
public class DashboardFrame extends JFrame {

    // Labels for headings and image
    JLabel heading;
    JLabel subHeading;
    JLabel imageLabel;

    // Sidebar navigation buttons
    JButton addPatientBtn;
    JButton updatePatientBtn;
    JButton searchPatientBtn;
    JButton viewAllPatientsBtn;
    JButton deletePatientBtn;
    JButton logoutBtn;

    // Main panels used in dashboard layout
    JPanel sidebarPanel;
    JPanel contentPanel;
    JPanel welcomePanel;

    /*
     * Constructor:
     * Calls initialize() to set up dashboard UI.
     */
    public DashboardFrame() {
        initialize();
    }

    public JButton getUpdatePatientBtn() {
        return updatePatientBtn;
    }

    public JButton getAddPatientBtn() {
        return addPatientBtn;
    }

    public JButton getSearchPatientBtn() {
        return searchPatientBtn;
    }

    public JButton getViewAllPatientsBtn() {
        return viewAllPatientsBtn;
    }

    public JButton getDeletePatientBtn() {
        return deletePatientBtn;
    }

    public JButton getLogoutBtn() {
        return logoutBtn;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    /*
     * initialize():
     * Configures frame and adds all dashboard components.
     */
    private void initialize() {

        // ================= FRAME SETTINGS =================

        setTitle("Patient Management System");
        setSize(700, 500);
        setLocationRelativeTo(null); // Centers frame on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // BorderLayout divides frame into regions
        setLayout(new BorderLayout());

        // ================= SIDEBAR PANEL =================

        sidebarPanel = new JPanel();

        /*
         * GridLayout:
         * Arranges buttons vertically in 6 rows and 1 column.
         */
        sidebarPanel.setLayout(new GridLayout(6, 1, 5, 2));

        // Fixed width for sidebar
        sidebarPanel.setPreferredSize(new Dimension(180, 0));

        // ================= SIDEBAR BUTTONS =================

        addPatientBtn = new JButton("Add Patient");
        updatePatientBtn = new JButton("Update Patient");
        searchPatientBtn = new JButton("Search Patient");
        viewAllPatientsBtn = new JButton("View All Patients");
        deletePatientBtn = new JButton("Delete Patient");
        logoutBtn = new JButton("Logout");

        // Add buttons to sidebar panel
        sidebarPanel.add(addPatientBtn);
        sidebarPanel.add(updatePatientBtn);
        sidebarPanel.add(searchPatientBtn);
        sidebarPanel.add(viewAllPatientsBtn);
        sidebarPanel.add(deletePatientBtn);
        sidebarPanel.add(logoutBtn);

        // Add sidebar panel to left side of frame
        add(sidebarPanel, BorderLayout.WEST);

        AddPatientPanel addPatientPanel;
        UpdatePatientPanel updatePatientPanel;
        SearchPatientPanel searchPatientPanel;
        ViewAllPatientsPanel viewAllPatientsPanel;
        DeletePatientPanel deletePatientPanel;

        // ================= CONTENT PANEL =================

        contentPanel = new JPanel();

        /*
         * CardLayout:
         * Allows switching between multiple panels dynamically.
         */
        contentPanel.setLayout(new CardLayout());



        // ================= WELCOME PANEL =================

        welcomePanel = new JPanel();

        /*
         * BoxLayout:
         * Arranges components vertically.
         */
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));

        // ================= HEADING =================

        heading = new JLabel("Welcome to Patient Management System");

        heading.setFont(new Font("Arial", Font.BOLD, 24));

        // Aligns component to center horizontally
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ================= SUB HEADING =================

        subHeading = new JLabel("Manage patient records efficiently.");

        subHeading.setFont(new Font("Arial", Font.PLAIN, 16));

        subHeading.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ================= IMAGE =================

        /*
         * Loads image from resources/icons folder.
         */
        ImageIcon i1 = new ImageIcon(
                ClassLoader.getSystemResource("icons/hospital.png")
        );

        /*
         * Resizes image smoothly.
         */
        Image i2 = i1.getImage().getScaledInstance(
                170,
                170,
                Image.SCALE_SMOOTH
        );

        // Converts resized image back into ImageIcon
        ImageIcon i3 = new ImageIcon(i2);

        // Label to display image
        imageLabel = new JLabel(i3);

        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ================= ADD COMPONENTS =================

        // Adds spacing from top
        welcomePanel.add(Box.createVerticalStrut(40));

        // Add heading
        welcomePanel.add(heading);

        // Spacing between components
        welcomePanel.add(Box.createVerticalStrut(15));

        // Add sub heading
        welcomePanel.add(subHeading);

        // More spacing before image
        welcomePanel.add(Box.createVerticalStrut(30));

        // Add image
        welcomePanel.add(imageLabel);

        /*
         * Adds welcome panel inside content panel.
         * "WELCOME" acts as card name for CardLayout.
         */
        contentPanel.add(welcomePanel, "WELCOME");

        // Add content panel to center of frame
        add(contentPanel, BorderLayout.CENTER);

        addPatientPanel = new AddPatientPanel();
        new AddPatientController(this, addPatientPanel);
        contentPanel.add(addPatientPanel, "ADD_PATIENT");

        updatePatientPanel = new UpdatePatientPanel();
        new UpdatePatientController(this,updatePatientPanel);
        contentPanel.add(updatePatientPanel, "UPDATE_PATIENT");

        searchPatientPanel = new SearchPatientPanel();
        contentPanel.add(searchPatientPanel, "SEARCH_PATIENT");
        new SearchPatientController(this, searchPatientPanel);

        viewAllPatientsPanel = new ViewAllPatientsPanel();
        new ViewAllPatientsController(this, viewAllPatientsPanel);
        contentPanel.add(viewAllPatientsPanel, "VIEW_PATIENTS");

        deletePatientPanel = new DeletePatientPanel();
        new DeletePatientController(this, deletePatientPanel);
        contentPanel.add(deletePatientPanel, "DELETE_PATIENT");


        // Makes dashboard visible
        setVisible(true);
    }

}