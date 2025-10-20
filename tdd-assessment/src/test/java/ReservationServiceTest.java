package src.test.java;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        assertThrows(NoAvailableCopiesException.class, () -> {
            reservationService.reserve("user2", "book2");
        });
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
    



}

