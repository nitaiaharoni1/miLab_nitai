const bodyParser = require('body-parser');
const express = require('express');
const app = express();
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));
const MongoClient = require('mongodb').MongoClient;
const mongoUrl = 'mongodb://localhost:27017/mongotest';
const ObjectId = require('mongodb').ObjectId;
let port = process.env.PORT || 6000;
let db;

app.get('/Read', function (req, res) {
    if (req.query.name) {
        getSong(req.query.name, res);
    } else if (req.query.artist) {
        getArtist(req.query.artist, res);
    } else if (req.query.artist) {
        getGenre(req.query.genre, res);
    } else {
        res.status(400).send({message: "No Songs for this data"});
    }
});

app.post('/Create', function (req, res) {
    if (!req.body.name || !req.body.artist || !req.body.genre)
        res.status(400).send({message: "Not a JSON"});
    else
        let status = EnterSong(req.body.name, req.body.artist, req.body.genre, res);
});

function EnterSong(name, artist, genre, res) {
    let song = {name: name, artist: artist, genre: genre};
    db.collection('mySongs').insert([song], function (err, database) {
        if (err) res.send("Can't add")
        else (res.send("Added"));
    });
}

app.put('/Update', function (req, res) {
    updateSong(req.body.id, req.body.name, req.body.artist, req.body.genre, res);
});

function updateSong(id, name, artist, genre, res) {
    let song = {
        name: name,
        artist: artist,
        genre: genre
    };

    db.collection('mySongs').updateOne({_id: ObjectId(id)}, {$set: song}, function (err, numUpdated) {
        if (err) res.send("Can't update : " + id);
        else if (numUpdated) res.send("Updated");
        else res.send("Can't find");
    });
}

function getSong(song, res) {
    db.collection('mySongs').findOne({name: song}, function (err, document) {
        if (err || !document) res.send("Can't find: " + song);
        else {
            printSong("Songs: " + document, res);
            res.end();
        }
    });
}

function getGenre(genere, res) {
    db.collection('mySongs').find({genre: genere}).toArray((err, results) => {
        if (err || !results[0]) res.send("Can't find Genre");
        else {
            res.write(`Songs: \n`);
            results.forEach(doc => printSong(doc, res));
            res.end();
        }
    });
}

function getArtist(artist, res) {
    db.collection('mySongs').find({artist: artist}).toArray((err, results) => {
        if (err || !results[0]) res.send("Can't find Artist");
        else {
            res.write(`Songs: \n`);
            results.forEach(doc => printSong(doc, res));
            res.end();
        }
    });
}

function printSong(song, res) {
    res.write(`Song: ${song.name}\n`);
    res.write(`Artist: ${song.artist}\n`);
    res.write(`Genre: ${song.genre}\n`);
    res.write(`Id: ${song._id}\n\n`);
}

app.delete('/Delete/:id', function (req, res) {
    let SongId = req.params.id;
    deleteSong(SongId, res);
});

function deleteSong(SongId, res) {
    db.collection('mySongs').deleteOne({_id: ObjectId(SongId)}, function (err, res) {
        res.send("Delete: " + SongId);
    });
}

MongoClient.connect(mongoUrl, (err, database) => {
    if (err)
        return console.log(err)
    console.log("Connected");
    if (err) return console.log(err)
    db = database.db("songs")
    app.listen(port, function () {
        console.log('app is running on http://localhost:' + port);
    });
});