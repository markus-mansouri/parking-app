package com.markus.parkingapp.controller;

import se.lexicon.dao.CustomerDao;
import se.lexicon.dao.ParkingSpotDao;
import se.lexicon.dao.ReservationDao;
import se.lexicon.dao.impl.CustomerDaoImpl;
import se.lexicon.dao.impl.ParkingSpotDaoImpl;
import se.lexicon.dao.impl.ReservationDaoImpl;
import se.lexicon.model.Customer;
import se.lexicon.model.ParkingSpot;
import se.lexicon.model.Reservation;
import se.lexicon.model.Vehicle;
import se.lexicon.schedule.ReservationScheduler;
import se.lexicon.view.ConsoleUI;

import java.util.List;
import java.util.Optional;

/**
 * The main controller for managing parking-related operations in the application.
 * Handles user input, interacts with DAOs, and coordinates actions across the model and view layers.
 */
public class ParkingController {

    private final ConsoleUI consoleUI;
    private final CustomerDao customerDao;
    private final ParkingSpotDao parkingSpotDao;
    private final ReservationDao reservationDao;

    /**
     * Initializes the controller, DAOs, and scheduler.
     * Automatically creates 10 parking spots for a specific area.
     *
     * @param consoleUI The console-based user interface.
     */
    public ParkingController(ConsoleUI consoleUI) {
        this.consoleUI = consoleUI;
        this.customerDao = CustomerDaoImpl.getInstance(); // Singleton instance for Customer DAO
        this.parkingSpotDao = new ParkingSpotDaoImpl();   // Instance of ParkingSpot DAO
        this.reservationDao = new ReservationDaoImpl();   // Instance of Reservation DAO

        // Automatically create parking spots for the specified area code
        initializeParkingSpots(101);

        // Start the scheduler for checking and updating reservations
        ReservationScheduler scheduler = new ReservationScheduler(reservationDao, parkingSpotDao);
        scheduler.startScheduler();
    }

    /**
     * Starts the main loop of the application, handling user input and operations.
     */
    public void run() {
        boolean running = true;

        while (running) {
            consoleUI.displayMenu(); // Display the main menu
            String choice = consoleUI.getInput("Choose an option: "); // Get user choice

            switch (choice) {
                case "1" ->
                        registerCustomer();          // Register a new customer
                case "2" -> displayParkingSpots();       // Display all parking spots
                case "3" -> reserveParkingSpot();        // Reserve a parking spot
                case "4" -> vacateParkingSpot();         // Vacate a parking spot
                case "5" -> {                            // Exit the application
                    consoleUI.displayMessage("Exiting the application. Goodbye!");
                    running = false;
                }
                default -> consoleUI.displayMessage("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Registers a new customer by collecting information from the user.
     */
    private void registerCustomer() {
        Customer customerInfo = consoleUI.getCustomerInfoWithVehicle();
        Customer savedCustomer = customerDao.create(customerInfo);      // Save the customer
        consoleUI.displaySuccessMessage("Customer registered successfully with ID: " + savedCustomer.getId());
    }

    /**
     * Displays all parking spots using the view.
     */
    private void displayParkingSpots() {
        List<ParkingSpot> parkingSpots = parkingSpotDao.findAll(); // Fetch all parking spots
        consoleUI.displayParkingSpots(parkingSpots);              // Display the parking spots in the view
    }

    /**
     * Initializes the parking spots for a specific area code.
     *
     * @param areaCode The area code to assign to the parking spots.
     */
    private void initializeParkingSpots(int areaCode) {
        for (int i = 1; i <= 10; i++) {
            parkingSpotDao.create(new ParkingSpot(i, areaCode)); // Create parking spots
        }
    }

    /**
     * Handles reserving a parking spot for a customer.
     */
    private void reserveParkingSpot() {
        List<ParkingSpot> availableSpots = parkingSpotDao.findAvailableSpots();

        // If no spots are available, exit early
        if (availableSpots.isEmpty()) {
            consoleUI.displayErrorMessage("No available parking spots to reserve.");
            return;
        }

        consoleUI.displayParkingSpots(availableSpots);

        ParkingSpot selectedSpot = null;
        while (selectedSpot == null) {
            //try {
                int spotNumber = Integer.parseInt(consoleUI.getInput("Enter the Spot ID to reserve: "));

                // Validate if the spot exists
                Optional<ParkingSpot> selectedSpotOpt = parkingSpotDao.findBySpotNumber(spotNumber);
                if (selectedSpotOpt.isEmpty()) {
                    consoleUI.displayErrorMessage("Error: The entered parking spot does not exist. Try again.");
                    continue;
                }

                // Check if the spot is actually available
                if (!availableSpots.contains(selectedSpotOpt.get())) {
                    consoleUI.displayErrorMessage("Error: The selected parking spot is already occupied. Try again.");
                    continue;
                }

                selectedSpot = selectedSpotOpt.get(); // Valid spot found
            //} catch (NumberFormatException e) {
            //    consoleUI.displayErrorMessage("Invalid input. Please enter a valid numeric spot number.");
            //}
        }

        // Proceed to customer validation
        int customerId;
        Optional<Customer> customerOpt;
        do {
            customerId = Integer.parseInt(consoleUI.getInput("Enter the Customer ID: "));
            customerOpt = customerDao.findById(customerId);
            if (customerOpt.isEmpty()) {
                consoleUI.displayErrorMessage("Error: Invalid customer ID. Try again.");
            }
        } while (customerOpt.isEmpty());

        // Valid customer retrieved
        Customer customer = customerOpt.get();

        int hours;
        do {
            hours = Integer.parseInt(consoleUI.getInput("Enter the number of hours for the reservation: "));
            if (hours <= 0) {
                consoleUI.displayErrorMessage("Error: Hours must be greater than 0.");
            }
        } while (hours <= 0);

        // Create a new reservation
        Reservation reservation = new Reservation(selectedSpot, customer, hours);
        reservationDao.create(reservation); // Save the reservation

        // Update the parking spot status to occupied
        selectedSpot.occupy();
        parkingSpotDao.update(selectedSpot);

        // Provide success feedback
        consoleUI.displaySuccessMessage("Reservation created successfully!");
        consoleUI.displaySuccessMessage(reservation.toString());
    }


    /**
     * Handles vacating a parking spot by completing its associated reservation.
     */
    /**
     * Handles vacating a parking spot by completing its associated reservation.
     */
    private void vacateParkingSpot() {
        Optional<Reservation> reservationOpt;
        String reservationId;

        do {
            reservationId = consoleUI.getInput("Enter the Reservation ID to vacate: ");
            reservationOpt = reservationDao.findById(reservationId);

            if (reservationOpt.isEmpty()) {
                consoleUI.displayErrorMessage("Error: Reservation ID " + reservationId + " does not exist. Try again.");
            }
        } while (reservationOpt.isEmpty()); // Loop until a valid reservation ID is entered

        Reservation reservation = reservationOpt.get();
        ParkingSpot parkingSpot = reservation.getParkingSpot();

        // Ensure the parking spot is currently occupied
        if (!parkingSpot.isOccupied()) {
            consoleUI.displayErrorMessage("Error: Parking spot ID " + parkingSpot.getSpotNumber() + " is already vacant.");
            return;
        }

        // Vacate the parking spot and mark the reservation as completed
        parkingSpot.vacate();
        parkingSpotDao.update(parkingSpot);
        reservation.complete();
        reservationDao.update(reservation);

        // Provide success feedback
        consoleUI.displaySuccessMessage("Reservation ID " + reservationId + " has been marked as completed.");
        consoleUI.displaySuccessMessage("Parking spot ID " + parkingSpot.getSpotNumber() + " has been vacated.");
    }

}
