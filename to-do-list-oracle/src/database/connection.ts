import oracledb from 'oracledb';
import dotenv from 'dotenv';

dotenv.config();

export async function createConnection() {
  try {
    const connection = await oracledb.getConnection({
      user: process.env.DB_USER,
      password: process.env.DB_PASSWORD,
      connectString: process.env.DB_CONNECT_STRING,
      privilege: oracledb.SYSDBA
    });
    
    console.log('Database connection successful');
    return connection;
  } catch (error) {
    console.error('Database connection error:', error);
    throw error;
  }
} 