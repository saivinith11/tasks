const Department = require('../model/department');

// Get top 5 departments
exports.getTopDepartments = async (req, res) => {
  try {
    const departments = await Department.find().sort({ downloads: -1 }).limit(5);
    res.json(departments);
  } catch (error) {
    res.status(500).send(error.message);
  }
};

// Get last week's winner
exports.getLastWeekWinner = async (req, res) => {
  // Implementation for last week's winner logic
};
