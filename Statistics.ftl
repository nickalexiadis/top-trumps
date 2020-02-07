<html>

	<head>
		<!-- Web page title -->
    	<title>Top Trumps</title>
    	
    	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
    	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

		<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
		<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css">
    	<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
    	<script>vex.defaultOptions.className = 'vex-theme-os';</script>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css"/>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css"/>
    	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

	</head>

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->
    	
    	<div class="container">

			<!-- Add your HTML Here -->
			<div>
				<h1>Top Trumps Game</h1>
			</div>

			<div>
				<button id="newGameBtn" onClick="newGame()">New Game</button>
				<button id="newGameBtn" onClick="home()">Home</button>
			</div>

			<div>
				<h3>Number of Games: <p id="numOfGames"></p></h3>
				<h3>Number of Human Wins: <p id="humanWins"></p></h3>
				<h3>Number of AI Wins: <p id="AIWins"></p></h3>
				<h3>Average Draw per game: <p id="avgDraw"></p></h3>
				<h3>Longest Game: <p id="longestGame"></p></h3>
			</div>

		</div>
		
		<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {
			
				numOfGames();
				humanWins();
				AIWins();
				avgDraw();
				longestGame();
			}
			
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------

			function newGame() {
				window.location = "http://localhost:7777/toptrumps/game";
			}

			function home() {
				window.location = "http://localhost:7777/toptrumps";
			}

			function numOfGames() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/numOfGames"); /
				if (!xhr) {
  					alert("CORS not supported");
				}
				xhr.onload = function(e) {
 					var responseText = xhr.response; 
					$("#numOfGames").text(responseText);
				};
				xhr.send();		
			}

			function humanWins() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/humanWins"); /
				if (!xhr) {
  					alert("CORS not supported");
				}
				xhr.onload = function(e) {
 					var responseText = xhr.response; 
					$("#humanWins").text(responseText);
				};
				xhr.send();		
			}

			function AIWins() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/AIWins"); /
				if (!xhr) {
  					alert("CORS not supported");
				}
				xhr.onload = function(e) {
 					var responseText = xhr.response; 
					$("#AIWins").text(responseText);
				};
				xhr.send();		
			}

			function avgDraw() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/avgDraw"); /
				if (!xhr) {
  					alert("CORS not supported");
				}
				xhr.onload = function(e) {
 					var responseText = xhr.response; 
					$("#avgDraw").text(responseText);
				};
				xhr.send();		
			}

			function longestGame() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/longestGame"); /
				if (!xhr) {
  					alert("CORS not supported");
				}
				xhr.onload = function(e) {
 					var responseText = xhr.response; 
					$("#longestGame").text(responseText);
				};
				xhr.send();		
			}


			// This is a reusable method for creating a CORS request. Do not edit this.
			function createCORSRequest(method, url) {
  				var xhr = new XMLHttpRequest();
  				if ("withCredentials" in xhr) {

    				// Check if the XMLHttpRequest object has a "withCredentials" property.
    				// "withCredentials" only exists on XMLHTTPRequest2 objects.
    				xhr.open(method, url, true);

  				} else if (typeof XDomainRequest != "undefined") {

    				// Otherwise, check if XDomainRequest.
    				// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
    				xhr = new XDomainRequest();
    				xhr.open(method, url);

 				 } else {

    				// Otherwise, CORS is not supported by the browser.
    				xhr = null;

  				 }
  				 return xhr;
			}
		
		</script>
		
		<!-- Here are examples of how to call REST API Methods -->
		<script type="text/javascript">
		
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloJSONList() {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloJSONList"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloWord(word) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloWord?Word="+word); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}

		</script>
		
		</body>
</html>