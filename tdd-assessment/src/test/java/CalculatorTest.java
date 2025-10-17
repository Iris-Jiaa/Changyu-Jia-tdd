package src.test.java;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import src.main.java.Calculator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
    @ParameterizedTest
    @CsvSource({
        "6, 3, 2",
        "10, 2, 5", 
        "9, 3, 3",
        "7, 2, 3"
    })
    void divide_parameterizedTest(int dividend, int divisor, int expected) {
        assertEquals(expected, c.divide(dividend, divisor));
    }
}


