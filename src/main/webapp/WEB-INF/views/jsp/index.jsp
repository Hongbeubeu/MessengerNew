<!DOCTYPE html>
<html>
<head>
<title>Demo websocket</title>
</head>
<body>	
		<h2>Demo WebSocket Chat Room</h2>
		<input id="textMessage" type="text" />
		<button  onclick="sendMessage()" value="Send Message" type="submit">Send</button> 
		<input onclick="disconnect()" value="Disconnect" type="button" /> <br/><br/>
		<textarea id="textAreaMessage" rows="10" cols="50"></textarea>

		<script type="text/javascript">
			var websocket = new WebSocket("ws://localhost:8080/messenger/chatRoomServer");
			websocket.onmessage = function(message) {
				processMessage(message);
			};
			function processMessage(message) {
				console.log(message.data);
				var obj = JSON.parse(message.data);
				textAreaMessage.value += obj.name + " \n";
			}
			function sendMessage() {
				if (typeof websocket != 'undefined' && websocket.readyState == WebSocket.OPEN) {
					var text = document.getElementById("textMessage").value;
					websocket.send(JSON.stringify({'text': text, 'name': text }));
					textMessage.value = "";
				}
			}
			
			function disconnect(){
				websocket.close();
			}

		</script>
</body>
</html>