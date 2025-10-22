package src.main.java;
import java.util.List;

public interface IUserRepository {
    User findById(String id);
    void save(User user);
    List<User> findAll();
}


