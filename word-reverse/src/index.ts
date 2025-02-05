/**
 * Verilen kelimeyi ters çevirir
 * @param word - Ters çevrilecek kelime
 * @returns Ters çevrilmiş kelime
 */
export function reverseWord(word: string): string {
  return word.split('').reverse().join('');
} 