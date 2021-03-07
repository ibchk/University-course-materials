const express = require('express')
const path = require('path')
const http = require('http')
const PORT = 3001

const socketio = require('socket.io')
const e = require('express')
const app = express()
const server = http.createServer(app)
const io = socketio(server)
  
// Set static folder
app.use(express.static(path.join(__dirname, "public")))

// Start server
server.listen(PORT, () => {
	console.log("Server running at " + PORT)
})

const games = [{moves: null, p1_name: null, player1: null, p1_socket_id: null, p1_score: null, p2_name: null, player2: null,  p2_socket_id: null, p2_score: null}]
let free_id = 0;

const scores = [];


// Handle con request from cli
io.on('connection', (socket) => {
	socket.emit('scores', scores)

	socket.on("addToGame", gameSize => {

		let notInGame = true;
		for (i = 0; i < games.length; i++){
			console.log(i)
			if (games[i].moves == null){
				games[i].player1 = free_id;
				games[i].p1_socket_id = socket.id;
				games[i].moves = gameSize;
				notInGame = false;
				break
			} else if (games[i].moves == gameSize && games[i].player2 == null){
				games[i].player2 = free_id;
				games[i].p2_socket_id = socket.id;
				socket.broadcast.to(games[i].p1_socket_id).emit('player-con', games[i].player2, games[i].player1, i)
				socket.emit('player-con', games[i].player1, games[i].player2 , i)
				games.push({moves: null, p1_name: null, player1: null, p1_socket_id: null, p1_score: null, p2_name: null, player2: null,  p2_socket_id: null, p2_score: null});
				notInGame = false;
				break
			}
		}
		if (notInGame){
			games.push({moves: gameSize, player1: free_id, p1_socket_id: socket.id, player2: null,  p2_socket_id: null});
		}
		free_id++;
	})

	socket.on("myMove", (place, points, enemy_id, game_id) => {
		if (games[game_id].player1 === enemy_id){
			socket.broadcast.to(games[game_id].p1_socket_id).emit('enemyMove', place, points);
		} else if (games[game_id].player2 === enemy_id){
			socket.broadcast.to(games[game_id].p2_socket_id).emit('enemyMove', place, points);
		}
	});

	socket.on("myName", (name, enemy_id, game_id) => {
		if (games[game_id].player1 === enemy_id){
			socket.broadcast.to(games[game_id].p1_socket_id).emit('enemyName', name);
		} else if (games[game_id].player2 === enemy_id){
			socket.broadcast.to(games[game_id].p2_socket_id).emit('enemyName', name);
		}
	});

	socket.on("score",(name, score, game_id, player_id) => {
		scores.push({name: null, score: null, moves: null})
		scores[scores.length -1].name = name;
		scores[scores.length -1].moves = games[game_id].moves;
		scores[scores.length -1].score = score;
		if (games[game_id].p1_score == null){
			games[game_id].p1_score = score;
			games[game_id].p1_name = name;
		} else {
			games[game_id].p2_score = score;
			games[game_id].p2_name = name;
			socket.broadcast.to(games[game_id].p1_socket_id).emit('finish', name)
			socket.emit('finish', name)
		}
	})

	console.log(`Player connected to the server`)
	
})
