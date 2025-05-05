package com.markus.parkingapp.dao.impl;

import se.lexicon.dao.ParkingSpotDao;
import se.lexicon.model.ParkingSpot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingSpotDaoImpl implements ParkingSpotDao {

    private final List<ParkingSpot> parkingSpots = new ArrayList<>(); // In-memory storage for parking spots

    @Override
    public ParkingSpot create(ParkingSpot parkingSpot) {
        parkingSpots.add(parkingSpot);
        return parkingSpot;
    }

    @Override
    public List<ParkingSpot> findAll() {
        return new ArrayList<>(parkingSpots); // Return a copy to avoid modification of the original list
    }

    @Override
    public Optional<ParkingSpot> findBySpotNumber(Integer spotNumber) {
        for (ParkingSpot spot : parkingSpots) {
            if (spot.getSpotNumber().equals(spotNumber)) {
                return Optional.of(spot);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<ParkingSpot> findAvailableSpots() {
        // Filter for vacant spots
        List<ParkingSpot> list = new ArrayList<>();
        for (ParkingSpot spot : parkingSpots) {
            if (!spot.isOccupied()) {
                list.add(spot);
            }
        }
        return list;
    }


    @Override
    public void update(ParkingSpot parkingSpot) {
        // Validate input
        if (parkingSpot == null || parkingSpot.getSpotNumber() == null) {
            throw new IllegalArgumentException("Invalid parking spot: spotNumber cannot be null.");
        }

        // Find the existing parking spot
        Optional<ParkingSpot> existingSpot = findBySpotNumber(parkingSpot.getSpotNumber());
        if (existingSpot.isPresent()) {
            // Update the existing parking spot
            int index = parkingSpots.indexOf(existingSpot.get());
            parkingSpots.set(index, parkingSpot);
        } else {
            throw new IllegalArgumentException("Parking spot not found: " + parkingSpot.getSpotNumber());
        }
    }


    /*@Override
    public void update(ParkingSpot parkingSpot) {
        // Find the existing parking spot
        Optional<ParkingSpot> existingSpot = findBySpotNumber(parkingSpot.getSpotNumber());
        if (existingSpot.isPresent()) {
            // Replace the old entry with the updated one
            parkingSpots.remove(existingSpot.get());
            parkingSpots.add(parkingSpot);
        } else {
            throw new IllegalArgumentException("Parking spot not found: " + parkingSpot.getSpotNumber());
        }
    }*/

    @Override
    public boolean delete(Integer spotNumber) {
        if (spotNumber == null) {
            throw new IllegalArgumentException("Spot number cannot be null.");
        }

        for (ParkingSpot spot : parkingSpots) {
            if (spot.getSpotNumber().equals(spotNumber)) {
                parkingSpots.remove(spot);
                return true; // Successfully deleted
            }
        }

        return false; // Spot not found
    }

}
