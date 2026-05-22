package com.PMS.model.entity;

import jakarta.persistence.*;

/*
 * Doctor Entity Class:
 * Represents the doctor table in the database.
 * Used by Hibernate/JPA for ORM mapping.
 */
@Entity
@Table(name = "doctor")
public class Doctor {

    // Primary key of doctor table
    @Id

    // Automatically generates id values
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Doctor login credentials
    private String username;
    private String password;

    /*
     * Default constructor:
     * Required by Hibernate/JPA.
     */
    public Doctor() {
        super();
    }

    /*
     * Parameterized constructor:
     * Used to create and initialize Doctor objects.
     */
    public Doctor(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    /*
     * Getter and Setter methods:
     * Used to access and modify doctor data.
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}