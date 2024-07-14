const express = require('express');
const router = express.Router();
const bookController = require('../controller/bookController');

router.get('/weekly-popular', bookController.getWeeklyPopular);
router.get('/monthly-popular', bookController.getMonthlyPopular);
router.get('/today-trending', bookController.getTodayTrending);

module.exports = router;
