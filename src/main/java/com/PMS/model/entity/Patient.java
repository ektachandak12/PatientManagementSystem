package com.PMS.model.entity;

import jakarta.persistence.*;


@Entity
public class Patient {

    @Id
    private int id;
    private String name;
    private int age;
    private String city;
    private String gender;
    private int height;
    private int weight;

    public Patient() {
        super();
    }

    public Patient(int id, String name, int age, String city, String gender, int height, int weight) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.city = city;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
    }

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
}
