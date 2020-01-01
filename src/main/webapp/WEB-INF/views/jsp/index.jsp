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
				websocket.onopen = function(message) {
					processOpen(message);
				};
				websocket.onmessage = function(message) {
					processMessage(message);
				};
				websocket.onclose = function(message) {
					processClose(message);
				};
				websocket.onerror = function(message) {
					processError(message);
				};

			function processOpen(message) {
				textAreaMessage.value += "Server connect... \n";
			}
			function processMessage(message) {
				console.log(message);
				textAreaMessage.value += message.data + " \n";
			}
			function processClose(message) {
				textAreaMessage.value += "Server Disconnect... \n";
			}
			function processError(message) {
				textAreaMessage.value += "Error... " + message +" \n";
			}

			function sendMessage() {
				if (typeof websocket != 'undefined' && websocket.readyState == WebSocket.OPEN) {
					websocket.send(textMessage.value);
					textMessage.value = "";
				}
			}
			
			function disconnect(){
				websocket.close();
			}

		</script>
</body>
</html>