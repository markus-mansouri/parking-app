package com.markus.parkingapp.schedule;

import se.lexicon.dao.ParkingSpotDao;
import se.lexicon.dao.ReservationDao;
import se.lexicon.model.ParkingSpot;
import se.lexicon.model.Reservation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ReservationScheduler {

    // DAOs to access reservations and parking spots
    private final ReservationDao reservationDao;
    private final ParkingSpotDao parkingSpotDao;

    // Constructor to initialize the DAOs
    public ReservationScheduler(ReservationDao reservationDao, ParkingSpotDao parkingSpotDao) {
        this.reservationDao = reservationDao;
        this.parkingSpotDao = parkingSpotDao;
    }

    /**
     * Starts the scheduler that checks for expired reservations periodically.
     */
    public void startScheduler() {

        // Create a scheduler with a single thread
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Schedule the task to run every 1 minute
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    checkAndUpdateReservations(); // Task to check and update reservations
                } catch (Exception e) {
                    // Handle any exceptions that occur during task execution
                    System.err.println("Error in scheduler: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }, 0, 1, TimeUnit.MINUTES); // Initial delay: 0, repeat every 1 minute
    }

    /**
     * Checks for expired reservations and updates their status.
     */
    private void checkAndUpdateReservations() {
        // Fetch all reservations from the DAO
        List<Reservation> reservations = reservationDao.findAll();

        // Loop through each reservation
        for (Reservation reservation : reservations) {
            // Check if the reservation has expired and is still active
            if (reservation.getEndTime().isBefore(LocalDateTime.now()) && reservation.getStatus() == Reservation.Status.ACTIVE) {
                // Mark the reservation as completed
                reservation.complete();
                reservationDao.update(reservation); // Update the reservation in the DAO

                // Vacate the associated parking spot
                ParkingSpot parkingSpot = reservation.getParkingSpot(); // Get the parking spot
                parkingSpot.vacate(); // Mark the spot as vacant
                parkingSpotDao.update(parkingSpot); // Update the parking spot in the DAO

                // Log the completion of the reservation and spot vacating
                System.out.println("Reservation " + reservation.getReservationId() + " marked as completed.");
                System.out.println("Parking spot " + parkingSpot.getSpotNumber() + " is now vacant.");
            }
        }
    }
}
