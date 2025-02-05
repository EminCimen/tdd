import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    
    @Test
    void additionShouldReturnSum() {
        Calculator calculator = new Calculator();
        assertEquals(4, calculator.add(2, 2));
    }
    
    @Test
    void subtractionShouldReturnDifference() {
        Calculator calculator = new Calculator();
        assertEquals(3, calculator.subtract(5, 2));
    }
    
    @Test
    void multiplicationShouldReturnProduct() {
        Calculator calculator = new Calculator();
        assertEquals(6, calculator.multiply(2, 3));
    }
    
    @Test
    void divisionShouldReturnQuotient() {
        Calculator calculator = new Calculator();
        assertEquals(2, calculator.divide(4, 2));
    }
    
    @Test
    void divisionByZeroShouldThrowException() {
        Calculator calculator = new Calculator();
        assertThrows(ArithmeticException.class, () -> calculator.divide(4, 0));
    }
} 