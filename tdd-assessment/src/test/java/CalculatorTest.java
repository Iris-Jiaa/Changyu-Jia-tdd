package test.java;

import java.beans.Transient;

public class CalculatorTest { 
    //test2
    @Test
    void testAdd(){
        Calculator c = new Calculator(); 
        assertEquals(5, c.add(2, 3)); 
    }
}
