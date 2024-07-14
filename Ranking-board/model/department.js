const mongoose = require('mongoose');

const departmentSchema = new mongoose.Schema({
  name: String,
  downloads: { type: Number, default: 0 },
});

module.exports = mongoose.model('Department', departmentSchema);