package src.main.java;
public class Calculator { 
    public int add(int a, int b) { 
        int result = a + b;
        return result;
    }
    public int subtract(int minuend, int subtrahend){
        int result = minuend - subtrahend;
        return result;
    }
    public int multiply(int a, int b){
        int result = a * b;
        return result;
    }
    public int divide(int dividend, int divisor) {
        int result = dividend / divisor;
        return result;
    } 
}