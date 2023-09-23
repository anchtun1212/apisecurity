const button = document.getElementById('submit');

button.addEventListener('click', async _ => {
	const name = document.getElementById('name').value;
		
    let response = await fetch('http://localhost:8098/api/xss/danger/v1/greeting?name=' + name);
    response.text().then(function(text) {
    	document.getElementById('greeting').innerHTML = text;
    });
});