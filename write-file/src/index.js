const express = require('express');
const { writeBookToFile } = require('./bookWriter');

const app = express();
app.use(express.json());

app.post('/api/books', async (req, res) => {
    try {
        const { title, author } = req.body;
        
        if (!title || !author) {
            return res.status(400).json({ error: 'Kitap adı ve yazar zorunludur' });
        }

        if (typeof title !== 'string' || typeof author !== 'string') {
            return res.status(400).json({ error: 'title ve author string olmalı' });
        }

        await writeBookToFile(title, author);
        res.status(201).json({ message: 'Kitap başarıyla kaydedildi' });
    } catch (error) {
        res.status(500).json({ error: 'Bir hata oluştu' });
    }
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
    console.log(`Server ${PORT} portunda çalışıyor`);
});

module.exports = app; 