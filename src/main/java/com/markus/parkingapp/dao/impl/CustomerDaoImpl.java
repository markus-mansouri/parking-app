package com.markus.parkingapp.dao.impl;

import se.lexicon.dao.CustomerDao;
import se.lexicon.dao.sequencer.CustomerIdSequencer;
import se.lexicon.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDaoImpl implements CustomerDao {


    // Guideline to Implement a singleton design pattern is to:
    //create an object of CustomerDaoImpl
    private static CustomerDaoImpl instance = new CustomerDaoImpl();

    // make the constructor private so that this class cannot be
    // instantiated
    private CustomerDaoImpl() {
        System.out.println(" CustomerDaoImpl initialized ");
    }

    // Get the only object available
    public static CustomerDaoImpl getInstance() {
        return instance;
    }

    // Data storage
    private List<Customer> customers = new ArrayList<>();

    @Override
    public Customer create(Customer customer) {
        // Assign a unique ID to the customer
        int generatedId = CustomerIdSequencer.nextId();
        customer.setId(generatedId);
        
        customers.add(customer);
        return customer;
    }


    @Override
    public Optional<Customer> findById(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return Optional.of(customer); // Return an Optional
            }
        }
        return Optional.empty(); // Explicitly indicate no value
    }
}
