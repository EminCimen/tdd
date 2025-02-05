const { writeBookToFile } = require('../bookWriter');
const fs = require('fs').promises;

// fs.promises'i mock'luyoruz
jest.mock('fs', () => ({
    promises: {
        appendFile: jest.fn(),
        mkdir: jest.fn()
    }
}));

describe('Book Writer Tests', () => {
    beforeEach(() => {
        // Her test öncesi mock'ları temizle
        jest.clearAllMocks();
    });

    test('writeBookToFile başarılı şekilde kitap yazmalı', async () => {
        const title = 'Test Kitabı';
        const author = 'Test Yazarı';

        await writeBookToFile(title, author);

        expect(fs.mkdir).toHaveBeenCalled();
        expect(fs.appendFile).toHaveBeenCalledWith(
            expect.any(String),
            'Test Kitabı - Test Yazarı\n'
        );
    });

    test('writeBookToFile hata durumunda exception fırlatmalı', async () => {
        fs.appendFile.mockRejectedValue(new Error('Test hatası'));

        await expect(writeBookToFile('Test', 'Yazar'))
            .rejects
            .toThrow('Dosya yazma hatası');
    });
}); 