package com.markus.parkingapp.view;

import se.lexicon.model.Customer;
import se.lexicon.model.ParkingSpot;
import se.lexicon.model.Vehicle;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Provides a console-based user interface for interacting with the parking management system.
 */
public class ConsoleUI {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the main menu options to the user.
     * Users can select options such as registering customers, viewing parking spots,
     * reserving or vacating parking spots, and exiting the application.
     */
    public void displayMenu() {
        System.out.println("1. Register Customer");
        System.out.println("2. Display Parking Spots");
        System.out.println("3. Reserve a Parking Spot");
        System.out.println("4. Vacate Parking Spot");
        System.out.println("5. Exit");
    }

    /**
     * Prompts the user for input with a specified message.
     *
     * @param prompt The message to display to the user.
     * @return The user's input as a string.
     */
    public String getInput(String prompt) {
        String input;

        while (true) {
            System.out.println(prompt);
            input = scanner.next().trim();

            // Check if input is empty
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
            } else {
                break; // Exit loop if input is valid
            }
        }

        return input;
    }

    /**
     * Collects customer information along with their vehicle details.
     *
     * @return A {@link Customer} object containing the customer's details and their associated vehicle.
     */
    public Customer getCustomerInfoWithVehicle() {
        scanner.nextLine(); // Consume the leftover newline from previous input
        System.out.println("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.println("Enter phone number: ");
        String phone = scanner.nextLine();
        System.out.println("Enter vehicle plate number: ");
        String regNumber = scanner.nextLine();
        System.out.println("Enter vehicle type (Car, Motorcycle, Truck, and ...): ");
        String type = scanner.nextLine();

        // Create the vehicle and associate it with the customer
        Vehicle vehicle = new Vehicle(regNumber, type);
        Customer customerData = new Customer(name, phone, vehicle);
        return customerData;
    }

    /**
     * Displays a success message in green text.
     * Used to provide feedback for successful operations.
     *
     * @param message The success message to display.
     */
    public void displaySuccessMessage(String message) {
        final String GREEN_COLOR = "\u001B[32m"; // ANSI escape code for green color
        final String RESET_COLOR = "\u001B[0m";  // Reset text color to default

        System.out.println(GREEN_COLOR + message + RESET_COLOR);
    }

    /**
     * Displays a general informational message.
     *
     * @param message The message to display.
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays an error message in red text.
     * Used to notify users of invalid inputs or system errors.
     *
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        final String RED_COLOR = "\u001B[31m"; // ANSI escape code for red color
        final String RESET_COLOR = "\u001B[0m"; // Reset text color to default

        System.out.println(RED_COLOR + message + RESET_COLOR);
    }

    /**
     * Displays a list of parking spots in a formatted and user-friendly manner.
     * Each parking spot shows its ID, area code, and status (✓ for available, X for occupied).
     *
     * @param parkingSpots The list of parking spots to display.
     */
    public void displayParkingSpots(List<ParkingSpot> parkingSpots) {
        System.out.println("===== Parking Area =====");
        if (parkingSpots.isEmpty()) {
            System.out.println("No parking spots available.");
        } else {
            for (ParkingSpot spot : parkingSpots) {
                // Determine the status icon based on occupancy
                String statusIcon = spot.isOccupied() ? "X" : "✓";

                // Display parking spot details
                System.out.printf(
                        "Spot ID: %-3d | Area Code: %-3d | Status: %s\n",
                        spot.getSpotNumber(),
                        spot.getAreaCode(),
                        statusIcon
                );
            }
        }
        System.out.println("=========================");
    }
}
