import { createConnection } from '../connection';

async function createTodoTable() {
  const connection = await createConnection();
  
  try {
    // Eğer tablo varsa önce silelim
    try {
      await connection.execute(`DROP TABLE todos`);
    } catch (e) {
      // Tablo yoksa hata verecektir, görmezden gelebiliriz
    }

    await connection.execute(`
      CREATE TABLE todos (
        id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
        title VARCHAR2(255),
        completed NUMBER(1) DEFAULT 0,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
      )
    `);
    
    // Değişiklikleri kaydet
    await connection.commit();
    
    console.log('Todos tablosu başarıyla oluşturuldu');
  } catch (error) {
    console.error('Tablo oluşturma hatası:', error);
    await connection.rollback();
  } finally {
    await connection.close();
  }
}

createTodoTable(); 