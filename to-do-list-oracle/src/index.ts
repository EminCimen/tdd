import express from 'express';
import dotenv from 'dotenv';
import { TodoService } from './services/TodoService';

dotenv.config();

const app = express();
const todoService = new TodoService();

app.use(express.json());

app.post('/todos', async (req, res) => {
  try {
    const { title } = req.body;
    if (!title) {
      return res.status(400).json({ error: 'Başlık alanı zorunludur' });
    }
    const todo = await todoService.create(title);
    res.status(201).json(todo);
  } catch (error) {
    console.error('Todo ekleme hatası:', error);
    const errorMessage = error instanceof Error ? error.message : 'Bilinmeyen bir hata oluştu';
    res.status(500).json({ 
      error: 'Todo eklenirken bir hata oluştu',
        details: errorMessage,
        code: (error as any).code 
    });
  }
});

app.get('/todos', async (req, res) => {
  const todos = await todoService.getAll();
  res.json(todos);
});

const port = process.env.PORT || 3000;
app.listen(port, () => {
  console.log(`Sunucu ${port} portunda çalışıyor`);
}); 