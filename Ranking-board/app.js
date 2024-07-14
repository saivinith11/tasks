const express = require('express');
const mongoose = require('mongoose');
const bodyParser = require('body-parser');
const cron = require('node-cron');
const departmentRoutes = require('./routes/departmentRoutes');
const bookRoutes = require('./routes/bookRoutes');
const bookController = require('./controller/bookController');

const app = express();
const PORT = process.env.PORT || 3000;

mongoose.connect('mongodb+srv://vini:test@cluster0.qllge.mongodb.net/nodejscarwash?retryWrites=true&w=majority', {
  useNewUrlParser: true,
  useUnifiedTopology: true,
});

app.use(bodyParser.json());


app.post('/books', (req, res) => {
    var newBook = {
        title: req.body.title,
        downloads: req.body.downloads,
        lastDownloaded: req.body.lastDownloaded
 
    }
    var book = new Review(newBook);
        book.save().then(() => {
        console.log("New book placed");
        res.status(201).json({
         message:  " book placed Success"
     
         });
    }).catch((err) => {
        if (err) {
            throw err;
        }
    })
    
})

app.use('/departments', departmentRoutes);
app.use('/books', bookRoutes);

// Schedule the removal of least popular books
cron.schedule('0 0 * * 0', bookController.removeLeastPopular); // Every Sunday at midnight

// Schedule trending books update every hour
cron.schedule('0 * * * *', async () => {
  const books = await bookController.getTodayTrending();
  console.log('Trending Books:', books);
});

app.listen(9001, () => {
  console.log(`Server is running on port ${9001}`);
});
