package src.test.java;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import src.main.java.Calculator;
public class CalculatorTest {
    private Calculator c = new Calculator();
    @Test //test 1
    void testAdd(){
        assertEquals(5, c.add(2, 3));
    }
    @Test //test 2
    void testSubtract() { 
        assertEquals(5, c.subtract(8, 3)); 
    } 

    @Test 
    void testMultiply() { 
        assertEquals(24, c.multiply(8, 3)); 
    }

    @Test 
    void testDivide() { 
        assertEquals(2, c.divide(6, 3)); 
    } 
}


