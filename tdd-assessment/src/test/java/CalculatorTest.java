package src.test.java;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import src.main.java.Calculator;
public class CalculatorTest {
    @Test
    void testAdd(){
        Calculator c = new Calculator();
        assertEquals(5, c.add(2, 3));
    }
}