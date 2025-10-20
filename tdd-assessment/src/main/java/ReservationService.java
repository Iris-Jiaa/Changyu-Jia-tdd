package src.main.java;

import java.util.List; 
public class ReservationService { 
    private final IBookRepository bookRepo; 
    private final IReservationRepository reservationRepo; 
    public ReservationService(IBookRepository bookRepo, 
    IReservationRepository reservationRepo) { 
        this.bookRepo = bookRepo; 
        this.reservationRepo = reservationRepo; 
    }   
/** 
* Reserve a book for a user. 
* Throws IllegalArgumentException if book not found. 
* Throws IllegalStateException if no copies available or user 
already reserved. 
*/ 
    public void reserve(String userId, String bookId) { 
        Book book = bookRepo.findById(bookId);
        if (book == null) {
            throw new IllegalArgumentException("Book not found: " + bookId);
        }
        if (book.getCopies() <= 0) {
            throw new NoAvailableCopiesException("No copies available");
        }
        if (reservationRepo.existsByUserAndBook(userId, bookId)) {
            throw new IllegalStateException("User already reserved this book");
        }
        book.setCopies(book.getCopies() - 1);
        bookRepo.save(book);
        reservationRepo.save(new Reservation(userId, bookId));
    } 
/** 
* Cancel an existing reservation for a user. 
* Throws IllegalArgumentException if no such reservation exists. 
*/ 
    public void cancel(String userId, String bookId) {
        reservationRepo.delete(userId, bookId);
        Book book = bookRepo.findById(bookId);
        book.setCopies(book.getCopies() + 1);
        bookRepo.save(book);
    } 
/** 
* List all active reservations for a given user. 
*/ 
    public List<Reservation> listReservations(String userId) { 
        // TODO: Implement using TDD 
        return null; 
    } 
/** 
* list all reservations for a book. 
*/ 
    public List<Reservation> listReservationsForBook(String bookId) { 
        // TODO: Implement using TDD 
        return null; 
    } 
} 

