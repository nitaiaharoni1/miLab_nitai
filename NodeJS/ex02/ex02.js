const express = require('express');
const app = express();
const fs = require('fs');
const bodyParser = require('body-parser');
app.use(bodyParser.json());
app.set('port', (process.env.PORT || 5000));

app.get('/getFile/:filename', (req, res) => {
	let readStream = fs.createReadStream(`${req.query.filename}.txt` , (err, data) => {
        if (err) {
            res.send("file not found");
            return;
        };
	readStream.pipe(res);
	});
});

app.listen(app.get('port'), () =>
    console.log('App listening on port: ' + app.get('port')));

