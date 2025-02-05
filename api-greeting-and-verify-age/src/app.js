const express = require('express');
const app = express();

app.use(express.json());

app.post('/api/greeting', (req, res) => {
  const { name, age } = req.body;

  if (!name || !age) {
    return res.status(400).json({ error: 'İsim ve yaş zorunludur!' });
  }

  if (typeof name !== 'string' || typeof age !== 'number') {
    return res.status(400).json({ error: 'HATALI İŞLEM' });
  }

  if (age < 18) {
    return res.status(400).json({ error: '18 yaşından küçükler için erişim yasak!' });
  }

  return res.json({ message: `Merhaba ${name}!` });
});

if (process.env.NODE_ENV !== 'test') {
  app.listen(3000, () => {
    console.log('Sunucu 3000 portunda çalışıyor');
  });
}

module.exports = app; 