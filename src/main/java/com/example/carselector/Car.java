package com.example.carselector;

// Importing Serializable class from java.io package to make the Car class serializable. Which 
// means that the Car object can be converted into a sequence of bytes and stored in a file or
// sent over the network.
import java.io.Serializable;

public class Car implements Serializable {
    private static final long serialVersionUID = 1L; // Required for Serializable class
    private String make;
    private String model;
    private int year;
    private String engineType;
    private String chassisType;
    private int horsepower;


    // Constructor
    public Car(String make, String model, int year, String engineType, String chassisType, int horsepower) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.engineType = engineType;
        this.chassisType = chassisType;
        this.horsepower = horsepower;
    }

    // Getters and Setters
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getChassisType() {
        return chassisType;
    }

    public void setChassisType(String chassisType) {
        this.chassisType = chassisType;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    //Override toString method for easy printing
    @Override
    public String toString() {
        return String.format("%s %s (%d) - Engine: %s, Chassis: %s, %d Horsepower", make, model, year, engineType, chassisType, horsepower);
    }

}