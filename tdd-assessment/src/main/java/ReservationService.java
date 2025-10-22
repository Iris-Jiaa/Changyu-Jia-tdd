package src.main.java;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue; 
public class ReservationService { 
    private final IBookRepository bookRepo; 
    private final IReservationRepository reservationRepo; 
    private final IUserRepository userRepo;
    private final Map<String, Queue<String>> waitingLists = new HashMap<>();
    public ReservationService(IBookRepository bookRepo, IUserRepository userRepo,
        IReservationRepository reservationRepo) { 
        this.bookRepo = bookRepo; 
        this.userRepo = userRepo;
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
        if (reservationRepo.existsByUserAndBook(userId, bookId)) {
            throw new IllegalStateException("User already reserved this book");
        }
        if (book.getCopiesAvailable() <= 0) {
            if (userRepo.findById(userId).isPriority()) {
                addToWaitingList(userId, bookId);
                return;
            }else{
                throw new IllegalStateException("No copies available");
            }
        }
            book.setCopiesAvailable(book.getCopiesAvailable() - 1);
            bookRepo.save(book);
            reservationRepo.save(new Reservation(userId, bookId));
    } 
    private void addToWaitingList(String userId, String bookId) {
        waitingLists.putIfAbsent(bookId, new LinkedList<>());
        waitingLists.get(bookId).add(userId);
    }

    public boolean isUserInWaitingList(String userId, String bookId) {
        return waitingLists.containsKey(bookId) && waitingLists.get(bookId).contains(userId);
    }
/** 
* Cancel an existing reservation for a user. 
* Throws IllegalArgumentException if no such reservation exists. 
*/ 
    public void cancel(String userId, String bookId) {
        if (!reservationRepo.existsByUserAndBook(userId, bookId)) {
            throw new IllegalArgumentException("No such reservation exists");
        }
        reservationRepo.delete(userId, bookId);
        Book book = bookRepo.findById(bookId);
        book.setCopiesAvailable(book.getCopiesAvailable() + 1);
        bookRepo.save(book);
    } 
/** 
* List all active reservations for a given user. 
*/ 
    public List<Reservation> listReservations(String userId) { 
        return reservationRepo.findByUser(userId); 
    } 
/** 
* list all reservations for a book. 
*/ 
    public List<Reservation> listReservationsForBook(String bookId) { 
        return reservationRepo.findByBook(bookId); 
    } 
} 

