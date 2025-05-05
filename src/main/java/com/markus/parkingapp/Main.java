package com.markus.parkingapp;

import import com.markus.parkingapp.ParkingService;
import com.markus.parkingapp.dao.CustomerDao;
import com.markus.parkingapp.dao.impl.CustomerDaoImpl;
import com.markus.parkingapp.model.Customer;
import com.markus.parkingapp.model.Vehicle;
import com.markus.parkingapp.view.ConsoleUI;


public class Main {
    public static void main(String[] args) {
        ConsoleUI consoleUI = new ConsoleUI();
        ParkingController controller = new ParkingController(consoleUI);
        controller.run();
    }


}
