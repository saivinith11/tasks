const express = require('express');
const router = express.Router();
const departmentController = require('../controller/departmentController');

router.get('/top', departmentController.getTopDepartments);
router.get('/last-week-winner', departmentController.getLastWeekWinner);

module.exports = router;
