import { Todo } from '../models/Todo';
import { createConnection } from '../database/connection';
import oracledb from 'oracledb';

// OutBinds için bir interface tanımlayalım
interface TodoOutBinds {
  id: [number];
  title_out: [string];
  completed: [number];
  created_at: [Date];
}

export class TodoService {
  async create(title: string): Promise<Todo> {
    const connection = await createConnection();
    
    try {
      const result = await connection.execute(
        `INSERT INTO todos (title) 
         VALUES (:title) 
         RETURNING id, title, completed, created_at INTO :id, :title_out, :completed, :created_at`,
        {
          title: { val: title, type: oracledb.STRING, dir: oracledb.BIND_IN },
          id: { type: oracledb.NUMBER, dir: oracledb.BIND_OUT },
          title_out: { type: oracledb.STRING, dir: oracledb.BIND_OUT, maxSize: 255 },
          completed: { type: oracledb.NUMBER, dir: oracledb.BIND_OUT },
          created_at: { type: oracledb.DATE, dir: oracledb.BIND_OUT }
        },
        { autoCommit: true }
      );
      
      const outBinds = result.outBinds as TodoOutBinds;

      return {
        id: outBinds.id[0],
        title: outBinds.title_out[0],
        completed: Boolean(outBinds.completed[0]),
        created_at: outBinds.created_at[0]
      };
    } finally {
      await connection.close();
    }
  }

  async getAll(): Promise<Todo[]> {
    const connection = await createConnection();

    try {
      const result = await connection.execute('SELECT * FROM todos');
      return result.rows as Todo[]; 
      
    } finally {
      await connection.close();
    }
  }
}
