package com.PMS.model.entity;

import jakarta.persistence.*;

/*
 * Patient Entity Class:
 * Represents the Patient table in the database.
 * Used by Hibernate/JPA for ORM mapping.
 */
@Entity
@Table(name = "patient")
public class Patient {

    // Primary key of Patient table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Patient basic details
    private String name;
    private int age;
    private String city;
    private String gender;

    // Physical information of patient
    private int height;
    private int weight;

    /*
     * Default constructor:
     * Required by Hibernate/JPA.
     */
    public Patient() {
        super();
    }

    /*
     * Parameterized constructor:
     * Used to create and initialize Patient objects.
     */
    public Patient(String name, int age,
                   String city, String gender,
                   int height, int weight) {

        this.name = name;
        this.age = age;
        this.city = city;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
    }

    /*
     * Getter and Setter methods:
     * Used to access and modify patient data.
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                ", gender='" + gender + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}