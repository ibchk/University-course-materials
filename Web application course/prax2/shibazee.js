// get name and game length
var name = "Shiba " + localStorage.getItem("name");
var gameLength = localStorage.getItem("gameLength");
var game = true;
var isRolled = false;
var rollLeft = 3;
var totalScore = 0;
var dict = {};
alert("I'm sorry, but your owner is coding, so you are playing alone.")

//calc score before game
document.getElementById('sum').innerHTML = totalScore;

//add name to button and table
document.getElementById('button').innerHTML = "roll dices " + name;
if (name.length > 17) {
	document.getElementById('name bottom-player-name').innerHTML = 'Shiba';
} else {
	document.getElementById('name bottom-player-name').innerHTML = name;
}

//preload the six images first
var face1=new Image()
face1.src="prax2photos/1.png"
var face2=new Image()
face2.src="prax2photos/2.png"
var face3=new Image()
face3.src="prax2photos/3.png"
var face4=new Image()
face4.src="prax2photos/4.png"
var face5=new Image()
face5.src="prax2photos/5.png"
var face6=new Image()
face6.src="prax2photos/6.png"

var dice1CSS = document.getElementById("mydice1");
var dice2CSS = document.getElementById("mydice2");
var dice3CSS = document.getElementById("mydice3");
var dice4CSS = document.getElementById("mydice4");
var dice5CSS = document.getElementById("mydice5");
var dice1Mute = false;
var dice2Mute = false;
var dice3Mute = false;
var dice4Mute = false;
var dice5Mute = false;
var arrayOfDices = [];
refresh();

//Save dice nrs and make it changeable and teke them by id to change their id
function refresh(){
	dice1Mute = false;
	dice1CSS.style.left = '0%';
	dice1CSS.style.transform = 'rotate(0deg)';
	dice2Mute = false;
	dice2CSS.style.left = '0%';
	dice2CSS.style.transform = 'rotate(0deg)';
	dice3Mute = false;
	dice3CSS.style.left = '0%';
	dice3CSS.style.transform = 'rotate(0deg)';
	dice4Mute = false;
	dice4CSS.style.left = '0%';
	dice4CSS.style.transform = 'rotate(0deg)';
	dice5Mute = false;
	dice5CSS.style.left = '0%';
	dice5CSS.style.transform = 'rotate(0deg)';
}

// function to change dices
function throwdice(){
	if (!dice1Mute) {
		arrayOfDices[0] = Math.floor(Math.random() * 6) + 1;
		document.images["mydice1"].src=eval("face"+arrayOfDices[0]+".src");
	}
	if (!dice2Mute) {
		arrayOfDices[1] = Math.floor(Math.random() * 6) + 1;
		document.images["mydice2"].src=eval("face"+arrayOfDices[1]+".src");
	}
	if (!dice3Mute) {
		arrayOfDices[2] = Math.floor(Math.random() * 6) + 1;
		document.images["mydice3"].src=eval("face"+arrayOfDices[2]+".src");
	}
	if (!dice4Mute) {
		arrayOfDices[3] = Math.floor(Math.random() * 6) + 1;
		document.images["mydice4"].src=eval("face"+arrayOfDices[3]+".src");
	}
	if (!dice5Mute) {
		arrayOfDices[4] = Math.floor(Math.random() * 6) + 1;
		document.images["mydice5"].src=eval("face"+arrayOfDices[4]+".src");
	}
}

function addSumOneNr(elementId, nr, justForFun) {
	if (dict[elementId] != true && game) {

		var sum = 0;
		for (var i = 0; i < 5; i++) {
			if (arrayOfDices[i] == nr) {
				sum += nr;
			}
		}
		gameFinish(elementId, sum, justForFun);
	}
}

function sumBigger(elementId, nr, justForFun){
	if (dict[elementId] != true && game) {
		var sum = 0;
		for (var i = 0; i < 5; i++) {
			var thisNr = arrayOfDices[i];
			var thisSum = 0;
			for (var j = 0; j < 5; j++) {
				if (arrayOfDices[j] == thisNr && thisSum/nr != thisNr) {
					thisSum += thisNr;
				}
			}
			if (thisSum > sum && thisSum/nr == thisNr) {
				if (nr == 5) {
					thisSum = 50;
				}
				sum = thisSum;
			}
		}
		gameFinish(elementId, sum, justForFun);
	}
}

function chance(elementId, justForFun) {
	if (dict[elementId] != true && game) {
		var sum = 0;
		for (var i = 0; i < 5; i++) {
			sum += arrayOfDices[i];
		}
		gameFinish(elementId, sum, justForFun);
	}
}

function fullHouse(elementId, justForFun){
	if (dict[elementId] != true && game) {
		var three = 0;
		var sum = 0;
		for (var i = 0; i < 5; i++) {
			var thisNr = arrayOfDices[i];
			var thisSum = 0;
			for (var j = 0; j < 5; j++) {
				if (arrayOfDices[j] == thisNr && thisSum/3 != thisNr) {
					thisSum += thisNr;
				}
			}
			if (thisSum/3 == thisNr) {
				three = thisNr;
			}
		}
		if (three > 0) {
			for (var i = 0; i < 5; i++) {
				var thisNr = arrayOfDices[i];
				if (thisNr != three) {
					var thisSum = 0;
					for (var j = 0; j < 5; j++) {
						if (arrayOfDices[j] == thisNr && thisSum/2 != thisNr) {
							thisSum += thisNr;
						}
					}
					if (thisSum/2 == thisNr) {
						sum = 25;
					}
				}
			}
		}
		gameFinish(elementId, sum, justForFun);
	}
}

function straight(elementId, nr, justForFun) {
	if (dict[elementId] != true && game) {
		var newArray = [...arrayOfDices]
		newArray.sort(function(a, b){return a-b});
		var uniqueArray = newArray.filter((v, i, a) => a.indexOf(v) === i);
		var sum = 0;
		if (nr == 5) {
			if ((uniqueArray[0] == 1 && uniqueArray[1] == 2 && uniqueArray[2] == 3 && uniqueArray[3] == 4 && uniqueArray[4] == 5) || 
				(uniqueArray[0] == 2 && uniqueArray[1] == 3 && uniqueArray[2] == 4 && uniqueArray[3] == 5 && uniqueArray[4] == 6)) {
				sum = 40;
			}
		} else {
			if ((uniqueArray[0] == 1 && uniqueArray[1] == 2 && uniqueArray[2] == 3 && uniqueArray[3] == 4) || 
				(uniqueArray[0] == 2 && uniqueArray[1] == 3 && uniqueArray[2] == 4 && uniqueArray[3] == 5) ||
				(uniqueArray[0] == 3 && uniqueArray[1] == 4 && uniqueArray[2] == 5 && uniqueArray[3] == 6) ||
				(uniqueArray[1] == 1 && uniqueArray[2] == 2 && uniqueArray[3] == 3 && uniqueArray[4] == 4) || 
				(uniqueArray[1] == 2 && uniqueArray[2] == 3 && uniqueArray[3] == 4 && uniqueArray[4] == 5) ||
				(uniqueArray[1] == 3 && uniqueArray[2] == 4 && uniqueArray[3] == 5 && uniqueArray[4] == 6)) {
				sum = 30;
			}
		}
		gameFinish(elementId, sum, justForFun);
	}
}

function showResults() {
	document.getElementById('sum').innerHTML = totalScore;
	addSumOneNr('one', 1, true);
	addSumOneNr('two', 2, true);
	addSumOneNr('three', 3, true);
	addSumOneNr('four', 4, true);
	addSumOneNr('five', 5, true);
	addSumOneNr('six', 6, true);
	sumBigger('threeSum', 3, true);
	sumBigger('fourSum', 4, true);
	sumBigger('fiveSum', 5, true);
	chance('chance', true);
	fullHouse('fullHouse', true);
	straight('straightSmall', 4, true);
	straight('straightBig', 5, true);
}

function gameFinish(elementId, sum, justForFun) {
	if (!justForFun && game && isRolled) {
		rollLeft = 3;
		document.getElementById(elementId).style.backgroundColor = '#F08080';
		totalScore += sum;
		dict[elementId] = true;
		gameLength--;
		isRolled = false;
		if (gameLength < 1) {
			game = false;
			alert(name + " result is " + totalScore + " points, while stupid Bill is still coding.");
			window.location.reload();
		}
		refresh();
	}
	document.getElementById(elementId).innerHTML = sum;
	document.getElementById('sum').innerHTML = totalScore;
}

button.onclick = function() {
	if (rollLeft > 0 && game) {
		isRolled = true;
		throwdice();
		showResults();
		rollLeft--;
	} else if (game) {
		alert("You should make a choice. You had only 3 rolls");
	} else {
		alert("Start new game!");
	}
}

//Functions when click on dices and button:

function diceClick(diceMute, diceCSS){
	if (!diceMute) {
		diceCSS.style.left = Math.floor(Math.random() * 450) + 120 + '%';
		diceCSS.style.transform = 'rotate(' + Math.floor(Math.random() * 70) + 10 + 'deg)';
	} else {
		diceCSS.style.left = '0%';
		diceCSS.style.transform = 'rotate(0deg)';
	}
}

mydice1.onclick = function() {
	if (isRolled) {
		diceClick(dice1Mute, dice1CSS);
		dice1Mute = !dice1Mute;
	} else {
		alert("Roll fucking dice!")
	}
}

mydice2.onclick = function() {
	if (isRolled) {
		diceClick(dice2Mute, dice2CSS);
		dice2Mute = !dice2Mute;
	} else {
		alert("Roll fucking dice!")
	}
}

mydice3.onclick = function() {
	if (isRolled) {
		diceClick(dice3Mute, dice3CSS);
		dice3Mute = !dice3Mute;
	} else {
		alert("Roll fucking dice!")
	}
}

mydice4.onclick = function() {
	if (isRolled) {
		diceClick(dice4Mute, dice4CSS);
		dice4Mute = !dice4Mute;
	} else {
		alert("Roll fucking dice!")
	}
}

mydice5.onclick = function() {
	if (isRolled) {
		diceClick(dice5Mute, dice5CSS);
		dice5Mute = !dice5Mute;
	} else {
		alert("Roll fucking dice!")
	}
}