import { reverseWord } from '../src/index';

describe('reverseWord function', () => {
  test('Empty string', () => {
    expect(reverseWord('')).toBe('');
  });

  test('Single letter word', () => {
    expect(reverseWord('a')).toBe('a');
  });

  test('Long word', () => {
    expect(reverseWord('typescript')).toBe('tpircsepyt');
  });

  test('Turkish characters', () => {
    expect(reverseWord('ışık')).toBe('kışı');
  });
}); 