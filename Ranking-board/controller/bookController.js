const Book = require('../model/book');

// Get weekly popular books
exports.getWeeklyPopular = async (req, res) => {
  try {
    const oneWeekAgo = new Date();
    oneWeekAgo.setDate(oneWeekAgo.getDate() - 7);

    const books = await Book.find({ lastDownloaded: { $gte: oneWeekAgo } })
      .sort({ downloads: -1 });
    res.json(books);
  } catch (error) {
    res.status(500).send(error.message);
  }
};

// Get monthly popular books
exports.getMonthlyPopular = async (req, res) => {
  try {
    const oneMonthAgo = new Date();
    oneMonthAgo.setMonth(oneMonthAgo.getMonth() - 1);

    const books = await Book.find({ lastDownloaded: { $gte: oneMonthAgo } })
      .sort({ downloads: -1 });
    res.json(books);
  } catch (error) {
    res.status(500).send(error.message);
  }
};

// Get today's trending books
exports.getTodayTrending = async (req, res) => {
  try {
    const today = new Date();
    today.setHours(0, 0, 0, 0);

    const books = await Book.find({ lastDownloaded: { $gte: today } })
      .sort({ downloads: -1 });
    res.json(books);
  } catch (error) {
    res.status(500).send(error.message);
  }
};

// Remove least popular books
exports.removeLeastPopular = async () => {
  try {
    const twoWeeksAgo = new Date();
    twoWeeksAgo.setDate(twoWeeksAgo.getDate() - 14);

    await Book.deleteMany({ lastDownloaded: { $lte: twoWeeksAgo } });
  } catch (error) {
    console.error('Error removing least popular books:', error);
  }
};
