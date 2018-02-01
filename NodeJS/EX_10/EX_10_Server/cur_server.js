
const express = require('express');
const handler = express();
const app = require('http').createServer(handler);
const io = require('socket.io')(app);
const alpha = require('alphavantage')({ key: '57W4TPLT2J59MJ5K' });

let port = process.env.PORT || 5000;

//Fired upon a connection from client.
io.on('connect', function (socket) {
	socket.on('quotes', function (data) {
        currency = data;
        console.log("checking for coin " + currency)
        });

  	let printCur = setInterval( ()  => {
		if (!coin || !socket) {
	      	return;
		}

		//
	 	alpha.forex.rate(currency, 'usd').then(data => {
	      	res = Object.values(data['Realtime Currency Exchange Rate'])[4];
	  		socket.emit('Rate', res);
	  	});
		}, 
	15*1000);
  
  socket.on('disconnect',() => {
    clearInterval(printCur);
  });
});


handler.listen(port, function() {
    console.log('port is ' + port);
});
