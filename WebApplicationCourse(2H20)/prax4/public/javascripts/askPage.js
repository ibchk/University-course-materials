const socket = io();

socket.on('scores', (scores) => {
	var table = document.getElementById("scores");
	var name = table.insertRow(0);
	name.innerHTML = "Best scores"
	var reversed_scores = scores.reverse();
	var row = table.insertRow(1);
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
	cell1.innerHTML = "Name";
	cell2.innerHTML = "Moves";
	cell3.innerHTML = "Score";
  	for (i = 0; i< reversed_scores.length; i++){
		var row = table.insertRow(i+2);
		var cell1 = row.insertCell(0);
		var cell2 = row.insertCell(1);
		var cell3 = row.insertCell(2);
		cell1.innerHTML = reversed_scores[i].name;
		cell2.innerHTML = reversed_scores[i].moves;
		cell3.innerHTML = reversed_scores[i].score;
		if (i > 8){
			break
		}
	}
})


function save() {
		window.localStorage.setItem("name",document.getElementById("name").value);
		window.localStorage.setItem("gameLength",document.getElementById("select").options[select.selectedIndex].text);
		window.location.replace("Shibazee game.html");

}