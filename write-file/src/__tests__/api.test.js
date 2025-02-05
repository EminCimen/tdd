const request = require('supertest');
const app = require('../index');
const { writeBookToFile } = require('../bookWriter');

// bookWriter modülünü mock'luyoruz
jest.mock('../bookWriter');

describe('API Tests', () => {
    beforeEach(() => {
        jest.clearAllMocks();
    });

    test('POST /api/books geçerli veri ile 201 döndürmeli', async () => {
        writeBookToFile.mockResolvedValue();

        const response = await request(app)
            .post('/api/books')
            .send({
                title: 'Test Kitabı',
                author: 'Test Yazarı'
            });

        expect(response.status).toBe(201);
        expect(writeBookToFile).toHaveBeenCalledWith('Test Kitabı', 'Test Yazarı');
    });

    test('POST /api/books eksik veri ile 400 döndürmeli', async () => {
        const response = await request(app)
            .post('/api/books')
            .send({
                title: 'Test Kitabı'
            });

        expect(response.status).toBe(400);
        expect(writeBookToFile).not.toHaveBeenCalled();
    });

    test('title ve author string olmalı', async () => {
        const response = await request(app)
            .post('/api/books')
            .send({
                title: 123,
                author: 123 
            });

        expect(response.status).toBe(400);
        expect(response.body.error).toBe('title ve author string olmalı');
        expect(writeBookToFile).not.toHaveBeenCalled();
    });
    
    
}); 