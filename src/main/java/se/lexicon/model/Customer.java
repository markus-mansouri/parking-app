package se.lexicon.model;

import java.util.Objects;
import java.util.regex.Pattern;

public class Customer {
    private Integer id;  // Unique identifier for the customer
    private String name;  // Full name of the customer
    private String phoneNumber;  // Contact number of the customer

    private Vehicle vehicle; // A customer has exactly one vehicle

    // Constructors
    public Customer(Integer id, String name, String phoneNumber, Vehicle vehicle) {
        this(name, phoneNumber, vehicle);
        //this.id = id;
        setId(id);
    }

    public Customer(String name, String phoneNumber, Vehicle vehicle) {
        this.setName(name);
        this.setPhoneNumber(phoneNumber);
        this.setVehicle(vehicle);
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name should not be null or empty.");
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) throw new IllegalArgumentException("PhoneNumber should not be null or empty.");

        final Pattern PHONE_PATTERN = Pattern.compile("^\\+?[0-9]{10,15}$");
        if (!PHONE_PATTERN.matcher(phoneNumber).matches()) {
            throw new IllegalArgumentException("Invalid phone number format.");
        }

        this.phoneNumber = phoneNumber;
    }

    public void setId(Integer id) {
        Objects.requireNonNull(id, "Id should not be null");
        this.id = id;
    }

    public void setVehicle(Vehicle vehicle) {
        Objects.requireNonNull(vehicle, "Vehicle should not be null");
        this.vehicle = vehicle;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", vehicle='" + vehicle + '\'' +
                '}';
    }

}
