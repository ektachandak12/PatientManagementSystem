package com.PMS.model.entity;

import jakarta.persistence.*;

/*
 * Patient Entity Class:
 * Represents a patient record in the system.
 * This class is mapped to the "patient" table using Hibernate/JPA.
 */
@Entity
@Table(name = "patient")
public class Patient {

    // Primary key of the patient table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Stores patient's full name
    private String name;

    // Stores patient's age
    private int age;

    // Stores patient's city
    private String city;

    // Stores patient's gender
    private String gender;

    // Stores patient's height in metres
    private double height;

    // Stores patient's weight in kilograms
    private double weight;

    // Stores calculated BMI value
    private double bmi;

    // Stores BMI category (Underweight, Healthy, Overweight, Obese)
    private String bmiCategory;


    /*
     * Default constructor:
     * Required by Hibernate/JPA to create objects
     * while fetching data from the database.
     */
    public Patient() {
        super();
    }

    /*
     * Parameterized constructor:
     * Creates a Patient object with all required details.
     */
    public Patient(String name, int age,
                   String city, String gender,
                   double height, double weight, double bmi, String bmiCategory) {

        this.name = name;
        this.age = age;
        this.city = city;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
        this.bmiCategory = bmiCategory;
    }

    /*
     * Getter and Setter methods:
     * Used to read and update patient information.
     */

    // Returns patient's id
    public int getId() {
        return id;
    }

    // Returns patient's name
    public String getName() {
        return name;
    }

    // Sets patient's name
    public void setName(String name) {
        this.name = name;
    }

    // Returns patient's age
    public int getAge() {
        return age;
    }

    // Sets patient's age
    public void setAge(int age) {
        this.age = age;
    }

    // Returns patient's city
    public String getCity() {
        return city;
    }

    // Sets patient's city
    public void setCity(String city) {
        this.city = city;
    }

    // Returns patient's gender
    public String getGender() {
        return gender;
    }

    // Sets patient's gender
    public void setGender(String gender) {
        this.gender = gender;
    }

    // Returns patient's height
    public double getHeight() {
        return height;
    }

    // Sets patient's height
    public void setHeight(double height) {
        this.height = height;
    }

    // Returns patient's weight
    public double getWeight() {
        return weight;
    }

    // Sets patient's weight
    public void setWeight(double weight) {
        this.weight = weight;
    }

    // Returns patient's BMI value
    public double getBmi(){
        return bmi;
    }

    // Sets patient's BMI value
    public void setBmi(double bmi){
        this.bmi = bmi;
    }

    // Returns patient's BMI category
    public String getBmiCategory(){
        return bmiCategory;
    }

    // Sets patient's BMI category
    public void setCategory(String bmiCategory){
        this.bmiCategory = bmiCategory;
    }

    /*
     * Returns a string representation of the Patient object.
     * Useful for debugging and logging purposes.
     */
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
                ", bmi=" + bmi +
                ", category='" + bmiCategory + '\'' +
                '}';
    }
}