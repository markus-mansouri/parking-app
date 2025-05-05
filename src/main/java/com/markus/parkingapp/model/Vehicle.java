package com.markus.parkingapp.model;

import java.util.Objects;

/**
 * Represents a vehicle associated with a customer.
 */
public class Vehicle {

    private String plateNumber; // Unique identifier for the vehicle
    private String type;              // Vehicle type (e.g., Car, Motorcycle, Truck)

    /**
     * Constructor to initialize a Vehicle object.
     *
     * @param registrationNumber The unique registration number of the vehicle.
     * @param type               The type of vehicle (e.g., Car, Motorcycle, Truck).
     */
    public Vehicle(String registrationNumber, String type) {
        this.plateNumber = Objects.requireNonNull(registrationNumber, "Plate number cannot be null.");
        this.type = Objects.requireNonNull(type, "Vehicle type cannot be null.");
    }

    // Getters and Setters
    public String getPlateNumber() {
        return plateNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.plateNumber = Objects.requireNonNull(registrationNumber, "Plate number cannot be null.");
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = Objects.requireNonNull(type, "Vehicle type cannot be null.");
    }


    @Override
    public String toString() {
        return "Vehicle{" +
                "plateNumber='" + plateNumber + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
