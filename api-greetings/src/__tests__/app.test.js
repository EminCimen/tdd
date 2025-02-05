const request = require('supertest');
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');
const app = require('../index');

// Mock modülleri
jest.mock('bcryptjs');
jest.mock('jsonwebtoken');

describe('API Tests', () => {
  beforeEach(() => {
    // Her test öncesi mock'ları temizle
    jest.clearAllMocks();
  });

  describe('POST /login', () => {
    it('doğru kimlik bilgileriyle token döndürmeli', async () => {
      // Mock davranışlarını ayarla
      bcrypt.compare.mockResolvedValue(true);
      jwt.sign.mockReturnValue('mock-token');

      const res = await request(app)
        .post('/login')
        .send({
          username: 'admin',
          password: 'admin123'
        });

      // Mock fonksiyonların doğru parametrelerle çağrıldığını kontrol et
      expect(bcrypt.compare).toHaveBeenCalledWith('admin123', expect.any(String));
      expect(jwt.sign).toHaveBeenCalledWith(
        { username: 'admin' },
        expect.any(String),
        { expiresIn: '1h' }
      );

      expect(res.statusCode).toBe(200);
      expect(res.body).toEqual({ token: 'mock-token' });
    });

    it('yanlış kimlik bilgileriyle hata döndürmeli', async () => {
      // Yanlış şifre senaryosu için mock
      bcrypt.compare.mockResolvedValue(false);

      const res = await request(app)
        .post('/login')
        .send({
          username: 'admin',
          password: 'yanlis-sifre'
        });

      expect(bcrypt.compare).toHaveBeenCalledWith('yanlis-sifre', expect.any(String));
      expect(jwt.sign).not.toHaveBeenCalled();

      expect(res.statusCode).toBe(401);
      expect(res.body).toEqual({ error: 'Geçersiz şifre' });
    });

    it('olmayan kullanıcı için hata döndürmeli', async () => {
      const res = await request(app)
        .post('/login')
        .send({
          username: 'olmayan-kullanici',
          password: 'sifre123'
        });

      expect(bcrypt.compare).not.toHaveBeenCalled();
      expect(jwt.sign).not.toHaveBeenCalled();

      expect(res.statusCode).toBe(401);
      expect(res.body).toEqual({ error: 'Kullanıcı bulunamadı' });
    });
  });

  describe('GET /greet/:name', () => {
    it('geçerli token ile selamlama mesajı döndürmeli', async () => {
      // Token doğrulama için mock
      jwt.verify.mockImplementation((token, secret, callback) => {
        callback(null, { username: 'admin' });
      });

      const res = await request(app)
        .get('/greet/Ahmet')
        .set('Authorization', 'Bearer mock-token');

      expect(jwt.verify).toHaveBeenCalledWith(
        'mock-token',
        expect.any(String),
        expect.any(Function)
      );

      expect(res.statusCode).toBe(200);
      expect(res.body).toEqual({ message: 'Merhaba, Ahmet!' });
    });

    it('token olmadan hata döndürmeli', async () => {
      const res = await request(app)
        .get('/greet/Ahmet');

      expect(jwt.verify).not.toHaveBeenCalled();
      expect(res.statusCode).toBe(401);
      expect(res.body).toEqual({ error: 'Token gerekli' });
    });

    it('geçersiz token ile hata döndürmeli', async () => {
      // Token doğrulama hatası için mock
      jwt.verify.mockImplementation((token, secret, callback) => {
        callback(new Error('Token geçersiz'), null);
      });

      const res = await request(app)
        .get('/greet/Ahmet')
        .set('Authorization', 'Bearer invalid-token');

      expect(jwt.verify).toHaveBeenCalled();
      expect(res.statusCode).toBe(403);
      expect(res.body).toEqual({ error: 'Geçersiz token' });
    });
  });
}); 