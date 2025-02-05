import { celsiusToFahrenheit } from '../src/index';

describe('celsiusToFahrenheit', () => {
  it('should convert 0°C to 32°F', () => {
    expect(celsiusToFahrenheit(0)).toBe(32);
  });

  it('should convert 100°C to 212°F', () => {
    expect(celsiusToFahrenheit(100)).toBe(212);
  });

  it('should convert -40°C to -40°F', () => {
    expect(celsiusToFahrenheit(-40)).toBe(-40);
  });
}); 