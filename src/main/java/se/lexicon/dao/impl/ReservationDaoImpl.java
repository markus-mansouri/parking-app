package se.lexicon.dao.impl;

import se.lexicon.dao.ReservationDao;
import se.lexicon.model.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReservationDaoImpl implements ReservationDao {

    private final List<Reservation> reservations = new ArrayList<>(); // In-memory storage

    @Override
    public Reservation create(Reservation reservation) {
        reservations.add(reservation);
        return reservation;
    }

    @Override
    public Optional<Reservation> findById(String reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId().equals(reservationId)) {
                return Optional.of(reservation);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Reservation> findAll() {
        return new ArrayList<>(reservations); // Return a copy to prevent modification
    }


    @Override
    public void update(Reservation reservation) {
        if (reservation == null || reservation.getReservationId() == null) {
            throw new IllegalArgumentException("Reservation or Reservation ID cannot be null.");
        }

        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getReservationId().equals(reservation.getReservationId())) {
                reservations.set(i, reservation); // Update the reservation in place
                return;
            }
        }

        throw new IllegalArgumentException("Reservation not found: " + reservation.getReservationId());
    }

    /**
     * Finds reservations associated with a specific customer.
     *
     * @param customerId The ID of the customer.
     * @return A list of reservations belonging to the given customer.
     */
    public List<Reservation> findByCustomerId(Integer customerId) {
        List<Reservation> list = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().getId().equals(customerId)) {
                list.add(reservation);
            }
        }
        return list;
    }
}
