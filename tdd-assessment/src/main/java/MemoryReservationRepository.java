package src.main.java;
import java.util.*; 

public class MemoryReservationRepository implements IReservationRepository { 
    private final List<Reservation> reservations = new ArrayList<>(); 
    @Override 
    public void save(Reservation reservation) { 
        reservations.add(reservation); 
    } 
    @Override 
    public boolean existsByUserAndBook(String userId, String bookId) { 
        return reservations.stream() 
                .anyMatch(r -> r.getUserId().equals(userId) 
                && r.getBookId().equals(bookId)); 
    } 
    @Override 
    public List<Reservation> findByUser(String userId) { 
        return reservations.stream() 
                .filter(r -> r.getUserId().equals(userId)) 
                .toList(); 
    } 
    @Override 
    public void delete(String userId, String bookId) { 
        reservations.removeIf(r -> 
                r.getUserId().equals(userId) && r.getBookId().equals(bookId)); 
    } 
}