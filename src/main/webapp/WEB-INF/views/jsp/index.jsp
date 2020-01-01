<!DOCTYPE html>
<html>
<head>
<title>Demo websocket</title>
</head>
<body>	
		<h2>Demo WebSocket Chat Room</h2>
		<input id="textMessage" type="text" placeholder="message"/>
		<input id="userId" type = "text" placeholder="userId"/>
		<input id="conversationId" type = "text" placeholder="roomId"/>
		<button  onclick="sendMessage()" value="Send Message" type="submit">Send</button> 
		<input onclick="disconnect()" value="Disconnect" type="button" /> <br/><br/>
		<h2 id = "demo">text here</h2>
		
		<script type="text/javascript">
		
			var websocket = new WebSocket("ws://localhost:8080/messenger/chatRoomServer");
			
			websocket.onmessage = function(message) {
				processMessage(message);
			};
			
			function processMessage(message) {
				console.log(message.data);
				var obj = JSON.parse(message.data);
				var p = document.createElement("p");
				var node = document.createTextNode(obj.userId + ": " + obj.text);
				p.appendChild(node);
				var div = document.getElementById("demo");
				div.appendChild(p);
			}
			
			function sendMessage() {
				if (typeof websocket != 'undefined' && websocket.readyState == WebSocket.OPEN) {
					var text = document.getElementById("textMessage").value;
					var userId = document.getElementById("userId").value;
					var roomId = document.getElementById("conversationId").value;
					websocket.send(JSON.stringify({'text': text, 'userId': userId, 'conversationId': roomId }));
					textMessage.value = "";
				}
			}
			
			function disconnect(){
				websocket.close();
			}

		</script>
</body>
</html>