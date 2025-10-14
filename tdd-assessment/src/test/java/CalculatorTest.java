package src.test.java;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import src.main.java.Calculator;
public class CalculatorTest {
    @Test //test 1
    void testAdd(){
        Calculator c = new Calculator();
        assertEquals(5, c.add(2, 3));
    }
    @Test //test 2
    void testSubtract() { 
        Calculator c = new Calculator(); 
        assertEquals(5, c.subtract(8, 3)); 
    } 

}