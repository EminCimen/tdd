const request = require('supertest');
const app = require('../app');

describe('Selamlama API', () => {
  //5. success
  test('18 yaşından büyük kullanıcıyı selamlar', async () => {
    const response = await request(app)
      .post('/api/greeting')
      .send({ name: 'Ahmet', age: 25 });
    
    expect(response.status).toBe(200);
    expect(response.body.message).toBe('Merhaba Ahmet!');
  });

  //1.test 18 yaş case
  test('18 yaşından küçük kullanıcı için hata döner', async () => {
    const response = await request(app)
      .post('/api/greeting')
      .send({ name: 'Ali', age: 15 });
    
    expect(response.status).toBe(400);
    expect(response.body.error).toBe('18 yaşından küçükler için erişim yasak!');
  });

  //3.test isim string değilse hata döner
  test ('isim string değilse hata döner', async () => {
    const response = await request(app)
      .post('/api/greeting')
      .send({ name: 123, age: 25 });
    
    expect(response.status).toBe(400);
  });

  //4.test yaş integer değilse hata döner
  test('yaş integer değilse hata döner', async () => {
    const response = await request(app)
      .post('/api/greeting')
      .send({ name: 'Mehmet', age: '25' });
    
    //assertEqual
    expect(response.status).toBe(400);
    expect(response.body.error).toBe('18 yaşından küçükler için erişim yasak!');
  });

  //2.test eksik bilgi gönderildiğinde hata döner
  test('eksik bilgi gönderildiğinde hata döner', async () => {
    const response = await request(app)
      .post('/api/greeting')
      .send({ name: 'Mehmet' });
    
    expect(response.status).toBe(400);
    expect(response.body.error).toBe('İsim ve yaş zorunludur!');
  });
}); 