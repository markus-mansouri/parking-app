package com.markus.parkingapp.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

/**
 * Represents a reservation in the parking management system.
 */
public class Reservation {

    /**
     * Enum representing the status of a reservation.
     * - ACTIVE: The reservation is currently in use.
     * - COMPLETED: The reservation has ended.
     */
    public enum Status {
        ACTIVE,
        COMPLETED
    }

    private final String reservationId;   // Unique identifier for the reservation
    private final LocalDateTime startTime; // Start time of the reservation
    private LocalDateTime endTime;  // End time of the reservation
    private Status status;  // Current status of the reservation (ACTIVE or COMPLETED)


    private ParkingSpot parkingSpot;  // The parking spot associated with this reservation

    private Customer customer;  // The customer who made the reservation


    public Reservation(ParkingSpot parkingSpot, Customer customer, int hours) {
        //this.reservationId = UUID.randomUUID().toString();
        this.reservationId = String.valueOf(generateRandomNumber());
        this.startTime = LocalDateTime.now();
        this.setEndTimeByHours(hours);
        this.parkingSpot = Objects.requireNonNull(parkingSpot, "Parking spot cannot be null.");
        this.customer = Objects.requireNonNull(customer, "Customer cannot be null.");
        this.status = Status.ACTIVE; // Default status is ACTIVE
    }

    private int generateRandomNumber() {
        Random random = new Random();
        return 100000 + random.nextInt(900000); // Generates a number between 100000 and 999999
    }

    /**
     * Sets the end time for the reservation by adding a given number of hours to the start time.
     *
     * @param hours The number of hours to add.
     */
    public void setEndTimeByHours(int hours) {
        if (hours <= 0) {
            throw new IllegalArgumentException("Hours must be greater than 0.");
        }
        this.endTime = this.startTime.plusHours(hours);
    }

    // Getters
    public String getReservationId() {
        return reservationId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public Customer getCustomer() {
        return customer;
    }

    // New Method to Mark Reservation as Completed
    public void complete() {
        this.status = Status.COMPLETED;
    }

    // Getter for Status
    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return """
                Reservation Details:
                ----------------------
                Reservation ID : %s
                Start Time     : %s
                End Time       : %s
                Parking Spot   : %s
                Customer        : %s
                ----------------------
                """.formatted(
                reservationId,
                startTime.truncatedTo(ChronoUnit.MINUTES),
                endTime.truncatedTo(ChronoUnit.MINUTES),
                parkingSpot,
                customer

        );
    }


}
