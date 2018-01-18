const express = require('express');
const app = express();
const fs = require('fs');
const bodyParser = require('body-parser');
app.use(bodyParser.json());
app.set('port', (process.env.PORT || 5000));

app.get('/files/:filename', (req, res) => {
	fs.createReadStream(`${req.params.filename}.txt`).pipe(res);
	});

app.listen(app.get('port'), () =>
    console.log('App listening on port: ' + app.get('port')));

