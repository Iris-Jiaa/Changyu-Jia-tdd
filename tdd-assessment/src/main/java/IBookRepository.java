package src.main.java;
import java.util.*; 

public interface IBookRepository { 
    Book findById(String id); 
    void save(Book book); 
    List<Book> findAll(); 
} 