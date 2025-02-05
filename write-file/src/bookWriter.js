const fs = require('fs').promises;
const path = require('path');

const FILE_PATH = path.join(__dirname, '../data/books.txt');

async function writeBookToFile(title, author) {
    const bookEntry = `${title} - ${author}\n`;
    
    try {
        // Dizin yoksa oluştur
        await fs.mkdir(path.dirname(FILE_PATH), { recursive: true });
        
        // Dosyaya ekle
        await fs.appendFile(FILE_PATH, bookEntry);
    } catch (error) {
        throw new Error('Dosya yazma hatası');
    }
}

module.exports = {
    writeBookToFile
}; 