const mongoose = require('mongoose');

const bookSchema = new mongoose.Schema({
  title: String,
  downloads: { type: Number, default: 0 },
  lastDownloaded: Date,
});

module.exports = mongoose.model('Book', bookSchema);
