		$.ajax({
			url : 'http://localhost:8080/Auth/json/GrabFilesServlet',
			success : function(data) {
				if (data === "denied") {
					location.href = "index.html";
				} else {
				var files = JSON.parse(data);
				console.log(files);
				
				console.log(files);
				var index;
				var list = document.getElementById('list');
				for (index = -1; index < files.length; ++index) {

					if (index === -1) {
						var path = document.getElementById('fullPath');
						path.innerHTML = pathBeautifier(files[0].path);
						var li = document.createElement('LI')
						li.setAttribute("onClick", "getFiles(this.innerHTML);");
						li.innerHTML = "<img width=\"20px\" src=\"images/back.png\"> [back]";
						list.appendChild(li)
					} else {
						var isDir = files[index].isDirectory;
						var li = document.createElement('LI');
						li.setAttribute("onClick", "getFiles(this.id);");
						li.setAttribute("id", files[index].name);
						li.innerHTML = (files[index]).name;

						if (isDir === true) {
							li.innerHTML = "<img width=\"20px\" src=\"images/folder.png\"> "
									+ (files[index]).name;
						} else {
							li.innerHTML = "<img width=\"20px\" src=\"images/file_icon.png\"> "
									+ (files[index]).name;
						}
						list.appendChild(li);

					}

				}

			}
		}
			

		});

function getFiles(id) {
			$.ajax({

				url : 'http://localhost:8080/Auth/json/GrabFilesServlet'.concat("?file=").concat(id),
				success : function(data) {
					parseDataAndDisplay(data);
				}

			});
}

function pathBeautifier(str) {
	var lastSlash = str.lastIndexOf("\\");
	return  str.slice(0,lastSlash).replace(/\\/g," / ");
}

var loutBtn = document.getElementById("logout");
loutBtn.onclick = function() {
	$.ajax({
		url : 'http://localhost:8080/Auth/json/LogoutServlet',
		success : function(data) {
			location.href = "index.html";
		}
	});
}

var addFolderButton = document.getElementById("addFolderButton");
addFolderButton.onclick = function() {
	
	var person = prompt("Please enter folder name", "");
	if (person != null) {
		$.ajax({
			url : 'http://localhost:8080/Auth/json/FileManagerServlet'.concat("?name=").concat(person),
			success : function(data) {
				parseDataAndDisplay(data);
			}
		});
	}
}

var uploadFile = document.getElementById("uploadFile");
uploadFile.onclick = function() {
	var hidenInput = document.getElementById('header');
	var inputField = document.getElementById('inputFile');
	if (document.getElementById("inputFile").hidden === true) {
		document.getElementById("inputFile").hidden = false;
	/*var input = document.createElement('INPUT');
	input.setAttribute("type", "file");
	input.setAttribute("id", "inputFile");
	input.setAttribute("name", "files[]");
	input.setAttribute("multiple", "");
	hidenInput.appendChild(input);*/
	} else {
		document.getElementById("inputFile").hidden = true;
	}
}

function parseDataAndDisplay(data) {
	var files = JSON.parse(data);
	console.log(files);
	var list = document.getElementById('list');
	var index;
	list.innerHTML = "";
	for (index = -1; index < files.length; ++index) {

		if (index === -1) {
			var path = document.getElementById('fullPath');
			if(files[0].isEmptyDir !== true) {
				path.innerHTML = pathBeautifier(files[0].path);
			} else {
				path.innerHTML = files[0].path.replace(/\\/g," / ");
			}
			var li = document.createElement('LI')
			li.setAttribute("onClick","getFiles(this.innerHTML);");
			li.innerHTML = "<img width=\"20px\" src=\"images/back.png\"> [back]";
			list.appendChild(li);
		} else {
			if(files[0].isEmptyDir === true) {
				var li = document.createElement('LI');
				li.setAttribute("onClick", "getFiles(this.id);");
				li.setAttribute("id", files[index].name);
				li.innerHTML = "<i>Directory is empty.</i>";
				list.appendChild(li);
				return;
			}
			var isDir = files[index].isDirectory;
			console.log(files);
			var li = document.createElement('LI');
			li.setAttribute("onClick", "getFiles(this.id);");
			li.setAttribute("id", files[index].name);
			li.innerHTML = (files[index]).name;
			if (isDir === true) {
				li.innerHTML = "<img width=\"20px\" src=\"images/folder.png\"> "
						+ (files[index]).name;
			} else {
				li.innerHTML = "<img width=\"20px\" src=\"images/file_icon.png\"> "
						+ (files[index]).name;
			}
			list.appendChild(li);
		}
	}

}