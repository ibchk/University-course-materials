

function save() {
	window.localStorage.setItem("name",document.getElementById("name").value);
	window.localStorage.setItem("gameLength",document.getElementById("select").options[select.selectedIndex].text);
}