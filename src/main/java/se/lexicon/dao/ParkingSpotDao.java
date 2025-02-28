package se.lexicon.dao;

import se.lexicon.model.ParkingSpot;

import java.util.List;
import java.util.Optional;

/**
 * Data Access Object (DAO) interface for ParkingSpot management.
 */
public interface ParkingSpotDao {

    ParkingSpot create(ParkingSpot parkingSpot); // Add a new parking spot

    List<ParkingSpot> findAll(); // Retrieve all parking spots

    Optional<ParkingSpot> findBySpotNumber(Integer spotNumber); // Find a parking spot by its spot number

    List<ParkingSpot> findAvailableSpots(); // Retrieve all available (vacant) parking spots

    void update(ParkingSpot parkingSpot); // Update a parking spot's details

    boolean delete(Integer spotNumber); // Remove a parking spot by its spot number
}
