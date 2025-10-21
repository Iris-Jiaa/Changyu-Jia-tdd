package src.test.java;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import src.main.java.*;


public class ReservationServiceTest {
    private IBookRepository bookRepo;
    private IReservationRepository reservationRepo;
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        bookRepo = new MemoryBookRepository();
        reservationRepo = new MemoryReservationRepository();
        reservationService = new ReservationService(bookRepo, reservationRepo);
    }
    
    @Test // 1. happy path
    void reserve_succeeds_whenCopiesAvailable() {
        Book book = new Book("book1", "Some Title", 2);
        bookRepo.save(book);
        reservationService.reserve("user1", "book1");
        assertEquals(1, bookRepo.findById("book1").getCopies());
        assertTrue(reservationRepo.existsByUserAndBook("user1", "book1"));
    }
    @Test // 2. no copies available
    void reserve_fails_whenNoCopiesAvailable() {
        Book book = new Book("book2", "Title", 0);
        bookRepo.save(book);
        assertThrows(IllegalStateException.class,
            () -> reservationService.reserve("user2", "book2"));
    }
    @Test // 3. book not found
    void reserve_whenBookNotFound() {
        assertThrows(IllegalArgumentException.class, 
            () -> reservationService.reserve("user1", "nonexistent"));
    }
    @Test // 4.  A user cannot reserve the same book twice. 
    void reserve_whenUserAlreadyReserved() {
        Book book = new Book("book1", "Test Book", 2);
        bookRepo.save(book);
        reservationService.reserve("user1", "book1");
        assertThrows(IllegalStateException.class, 
            () -> reservationService.reserve("user1", "book1"));
    }
    @Test // 5. cancel reservation successfully
    void cancel_whenReservationExists() {
        String userId = "user1";
        Book book = new Book("book1", "Test Book", 2);
        bookRepo.save(book);

        reservationService.reserve(userId, "book1");
        assertEquals(1, bookRepo.findById("book1").getCopies());
        reservationService.cancel(userId, "book1");
        assertEquals(2, bookRepo.findById("book1").getCopies());
    }
    @Test // 6. cancel reservation fails when no such reservation exists
    void cancel_whenUserExistsButNoReservation() {
        Book book = new Book("book1", "Test Book", 1);
        bookRepo.save(book);
    
        assertThrows(IllegalArgumentException.class, 
            () -> reservationService.cancel("user1", "book1"));
    }
    @Test // 7. list reservations for a user
    void listReservations_userReservations() {
        Book book1 = new Book("book1", "Book 1", 2);
        Book book2 = new Book("book2", "Book 2", 1);
        bookRepo.save(book1);
        bookRepo.save(book2);
        reservationService.reserve("user1", "book1");
        reservationService.reserve("user1", "book2");
        List<Reservation> user1Reservations = reservationService.listReservations("user1");
        assertEquals(2, user1Reservations.size());
        assertTrue(user1Reservations.stream().allMatch(r -> r.getUserId().equals("user1")));
    }
    @Test // 8. list reservations for a book
    void listReservations_bookReservations() {
        Book book = new Book("book1", "Book 1", 2);
        bookRepo.save(book);
        reservationService.reserve("user1", "book1");
        reservationService.reserve("user2", "book1");
        List<Reservation> bookReservations = reservationService.listReservationsForBook("book1");
        assertEquals(2, bookReservations.size());
        assertTrue(bookReservations.stream().allMatch(r -> r.getBookId().equals("book1")));
    }
    @Test // boundary test: 9. reserve the last copy
    void reserve_whenReserveLastCopy() {
        Book book = new Book("book1", "Test Book", 1);
        bookRepo.save(book);
        reservationService.reserve("user1", "book1");
        assertTrue(reservationRepo.existsByUserAndBook("user1", "book1"));
        assertEquals(0, bookRepo.findById("book1").getCopies());
        assertThrows(IllegalStateException.class,
            () -> reservationService.reserve("user2", "book1"));
    }
    @Test // boundary test: 10. list reservations for a user with no reservations
    void listReservations_userWithNoReservations() {
        List<Reservation> reservations = reservationService.listReservations("U999");
        assertTrue(reservations.isEmpty());
    }
    @Test // boundary test: 11. list reservations for a book with no reservations
    void listReservations_bookWithNoReservations() {
        List<Reservation> reservations = reservationService.listReservationsForBook("B001");
        assertTrue(reservations.isEmpty());
    }
}

