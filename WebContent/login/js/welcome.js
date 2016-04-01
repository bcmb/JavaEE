var goBack = document.getElementById("goBack");
if (goBack != null) {
	goBack.onclick = function () {
        location.href = "index.html";
    };
}

document.getElementById("userName").innerHTML = sessionStorage.getItem('username');;
