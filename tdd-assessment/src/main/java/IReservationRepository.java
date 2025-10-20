package src.main.java;

import java.util.*;

public interface IReservationRepository {
    void save(Reservation reservation);
    boolean existsByUserAndBook(String userId, String bookId);
    List<Reservation> findByUser(String userId);
    void delete(String userId, String bookId);
}
