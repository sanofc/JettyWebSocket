<!DOCTYPE html>
<html>
  <head>
		<title>WebSocket Chat</title>
		<script type="text/javascript">
			var ws = new WebSocket("ws://localhost:8080/WebSocketEndpoint");
 
			ws.onmessage = function(msg) {
				console.log("msg received" + msg.data);
				var log = document.getElementById("log");
				log.innerHTML += msg.data;
			};
			
			function send(){
				var msg = document.getElementById("msg");
				ws.send(msg.value + "<br>");
				msg.value = "";
			}
		</script>
	</head>
	<body>
		<h1>WebSocket Chat</h1>
		<div id="log"></div>
		<input id="msg" type="text"/>
		<button id="send" onclick="send();">send</button>
	</body>
</html>