var usernameFiled = document.getElementById("username");
if (usernameFiled != null) {
	usernameFiled.onkeydown = function () {
		document.getElementById("wrongPassword").innerHTML = "";
    };
}

var goToReg = document.getElementById("goToRegistrationButton");
if (goToReg != null) {
	goToReg.onclick = function () {
        location.href = "register.html";
    };
}

var goToLogin = document.getElementById("goToLoginButton");
if (goToLogin != null) {
	goToLogin.onclick = function () {
        location.href = "index.html";
    };
}

var regBtn = document.getElementById("registerButton");
if (regBtn != null) {
	regBtn.onclick = function () {
        location.href = "register.html";
    };
}

var loginBtn = document.getElementById("loginButton");
if (loginBtn != null) {
	loginBtn.onclick = function () {
		if (validateForm() === false) {
			return;
		}
        var xhr = new XMLHttpRequest();
		var baseUrl = "http://localhost:8080/Auth/json/AuthServlet";
		var userName = document.getElementById('username').value;
		var userPass = document.getElementById('password').value;
		var requestUrl = baseUrl.concat("?username=").concat(userName).concat("&password=").concat(userPass);
		//baseUrl.appe
		xhr.open('GET', requestUrl);
		xhr.send(null);

		xhr.onreadystatechange = function () {
		  var DONE = 4; // readyState 4 means the request is done.
		  var OK = 200; // status 200 is a successful return.
		  if (xhr.readyState === DONE) {
		    if (xhr.status === OK) 
		      console.log(xhr.responseText); // 'This is the returned text.'
		  	  var _data = JSON.parse(xhr.responseText);
		  	console.log('Data = ' + _data.auntheticated);
		  	  var user = _data.username;
		  	  if (_data.authenticated === true) {
		  		 //location.href = "logedin.html";
		  		 location.href = "files.html";
		  	  } else {
		  		document.getElementById("wrongPassword").innerText = "Wrong password or username";
		  	  }
		    } else {
		      console.log('Error: ' + xhr.status); // An error occurred during the request.
		    }
		}

    };
}

function validateForm() {
	var usr = document.getElementById("username").value;
	var pwd = document.getElementById("password").value;
	if (usr==null || usr=="",pwd==null || pwd=="") {
		document.getElementById("wrongPassword").innerHTML = "Please enter username and password.";
	  	return false;
	  } else {
		return true;
	  }
	}

