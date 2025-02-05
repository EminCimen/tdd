import { StringMerge } from "../index.js";

describe('StringMerge', () => {
    it('should merge two strings correctly', () => {
        const stringMerge = new StringMerge();
        const result = stringMerge.merge('Hello', 'World');
        expect(result).toBe('Hello World');
    });
});