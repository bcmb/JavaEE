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

var registerButton = document.getElementById("registerButton");
if (registerButton != null) {
	registerButton.onclick = function () {
		if (validateForm() === false) {
		return;	
		} else { 
        var xhr = new XMLHttpRequest();
		var baseUrl = "http://localhost:8080/Auth/json/AddUserServlet";
		var userName = document.getElementById('username').value;
		var userPass = document.getElementById('password').value;
		var confirmPass = document.getElementById('confirmPassword').value;
		var requestUrl = baseUrl.concat("?username=").concat(userName).concat("&password=").concat(userPass).concat("&confirm_password=").concat(confirmPass);
		baseUrl.appe
		xhr.open('GET', requestUrl);
		xhr.send(null);

		xhr.onreadystatechange = function () {
		  var DONE = 4; // readyState 4 means the request is done.
		  var OK = 200; // status 200 is a successful return.
		  if (xhr.readyState === DONE) {
		    if (xhr.status === OK) 
		      console.log(xhr.responseText); // 'This is the returned text.'
		  	  var _data = JSON.parse(xhr.responseText);
			  if ( _data.confirmPaswordWrong === true) {
					document.getElementById("wrongPassword").innerHTML = "Password and confirm password do not match.";
				 } else {
				  if (_data.regSuccess === true) {
					 sessionStorage.setItem('username', _data.userName);
					 location.href = "regsuccess.html";
				  } else {
					document.getElementById("wrongPassword").innerHTML = "Somwething went wrong, please try again.";
				  }
			  }
		    } else {
		      console.log('Error: ' + xhr.status); // An error occurred during the request.
		    }
		}
	  }

    };
}

function validateForm() {
var usr = document.getElementById("username").value;
var pwd = document.getElementById("password").value;
var confirmPrwd = document.getElementById("confirmPassword").value;
if (usr==null || usr=="",pwd==null || pwd=="",confirmPrwd==null || confirmPrwd=="") {
	document.getElementById("wrongPassword").innerHTML = "All filelds are required.";
  	return false;
  } else {
	return true;
  }
}
