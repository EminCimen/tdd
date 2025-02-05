const { fahrenheitToCelsius } = require('./fahreineit');

test('fahrenheitToCelsius should convert 32 to 0', () => {
    expect(fahrenheitToCelsius(32)).toBe(0);
});

test('fahrenheitToCelsius should convert 212 to 100', () => {
    expect(fahrenheitToCelsius(212)).toBe(100);
});

test('fahrenheitToCelsius should convert -40 to -40', () => {
    expect(fahrenheitToCelsius(-40)).toBe(-40);
});