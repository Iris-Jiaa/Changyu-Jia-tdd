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


}

