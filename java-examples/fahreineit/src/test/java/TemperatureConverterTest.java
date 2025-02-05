import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TemperatureConverterTest {
    
    private final TemperatureConverter converter = new TemperatureConverter();
    
    @Test
    void celsiusToFahrenheitShouldConvertZeroCelsius() {
        assertEquals(32, converter.celsiusToFahrenheit(0));
    }
    
    @Test
    void celsiusToFahrenheitShouldConvertPositiveTemperature() {
        assertEquals(98.6, converter.celsiusToFahrenheit(37), 0.1);
    }
    
    @Test
    void celsiusToFahrenheitShouldConvertNegativeTemperature() {
        assertEquals(-4, converter.celsiusToFahrenheit(-20));
    }
    
    @Test
    void fahrenheitToCelsiusShouldConvertZeroFahrenheit() {
        assertEquals(-17.78, converter.fahrenheitToCelsius(0), 0.01);
    }
    
    @Test
    void fahrenheitToCelsiusShouldConvertPositiveTemperature() {
        assertEquals(37, converter.fahrenheitToCelsius(98.6), 0.1);
    }
    
    @Test
    void fahrenheitToCelsiusShouldConvertNegativeTemperature() {
        assertEquals(-20, converter.fahrenheitToCelsius(-4), 0.1);
    }
} 