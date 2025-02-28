package se.lexicon.dao;


import se.lexicon.model.Customer;

import java.util.Optional;


public interface CustomerDao {

    Customer create(Customer customer);

    Optional<Customer> findById(int id);

}
