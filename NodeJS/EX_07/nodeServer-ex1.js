const express = require('express');
const app = express();
const fs = require('fs');
const bodyParser = require('body-parser');
app.use(bodyParser.json());
app.set('port', (process.env.PORT || 5000));

app.get('/getTime', (req, res) => {
    let time = currentdate => {
        return currentdate.getDate() + "/"
            + (currentdate.getMonth() + 1) + "/"
            + currentdate.getFullYear() + " @ "
            + currentdate.getHours() + ":"
            + currentdate.getMinutes() + ":"
            + currentdate.getSeconds();
    };
    res.send(currentdate);
});

app.get('/getFile', (req, res) => {
    fs.readFile(`${req.query.filename}.txt`, "utf8", (err, data) => {
        if (err) {
            res.send("file not found");
            return;
        }
        res.send(data);
    });
});

app.get('/getFile/:filename', (req, res) => {
    file(res, req.params.filename);
});

app.listen(app.get('port'), () =>
    console.log('App listening on port: ' + app.get('port')));

