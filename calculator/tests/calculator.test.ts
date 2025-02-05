import { Calculator } from '../src/calculator';

describe('Calculator', () => {
  let calculator: Calculator;

  beforeEach(() => {
    calculator = new Calculator();
  });

  test('should add two numbers correctly', () => {
    const result = calculator.add(2, 3);
    expect(result).toBe(5);
  });

  test('should subtract two numbers correctly', () => {
    const result = calculator.subtract(5, 3);
    expect(result).toBe(2);
  });

  test('should multiply two numbers correctly', () => {
    const result = calculator.multiply(2, 3);
    expect(result).toBe(6);
  });

  test('should divide two numbers correctly', () => {
    const result = calculator.divide(6, 3);
    expect(result).toBe(2);
  });

  test('should throw an error when dividing by zero', () => {
    expect(() => calculator.divide(6, 0)).toThrow('Cannot divide by zero');
  });
}); 