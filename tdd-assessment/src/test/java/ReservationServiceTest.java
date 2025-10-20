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
}

