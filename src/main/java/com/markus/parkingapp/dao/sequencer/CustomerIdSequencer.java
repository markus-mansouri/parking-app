package com.markus.parkingapp.dao.sequencer;

public class CustomerIdSequencer {
    // Static variable to hold the current ID
    private static int currentId = 0;

    // Private constructor to prevent instantiation
    private CustomerIdSequencer() {
    }

    // Method to get the next unique ID
    public static int nextId() {
        return ++currentId;
    }


}
