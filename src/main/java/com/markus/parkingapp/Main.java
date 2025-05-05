package com.markus.parkingapp;

import se.lexicon.controller.ParkingController;
import se.lexicon.dao.CustomerDao;
import se.lexicon.dao.impl.CustomerDaoImpl;
import se.lexicon.model.Customer;
import se.lexicon.model.Vehicle;
import se.lexicon.view.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        ConsoleUI consoleUI = new ConsoleUI();
        ParkingController controller = new ParkingController(consoleUI);
        controller.run();
    }


}
