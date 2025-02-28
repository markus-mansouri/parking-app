package se.lexicon.dao;

import se.lexicon.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationDao {
    Reservation create(Reservation reservation);          // Add a new reservation

    Optional<Reservation> findById(String reservationId); // Find a reservation by its ID

    List<Reservation> findAll();                          // Retrieve all reservations

    void update(Reservation reservation);
}
