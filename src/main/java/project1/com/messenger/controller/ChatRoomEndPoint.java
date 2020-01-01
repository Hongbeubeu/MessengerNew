package project1.com.messenger.controller;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import com.fasterxml.jackson.databind.ObjectMapper;

import project1.com.messenger.entities.Message;

@ServerEndpoint(value = "/chatRoomServer")
public class ChatRoomEndPoint {
	static Set<Session> users = Collections.synchronizedSet(new HashSet<Session>());
	  @OnOpen
	  public void handleOpen(Session session) {
	    users.add(session);
	  }
	  @OnMessage
	  public void handleMessage(String message, Session userSession) throws IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		Message messages = new Message();
		messages.setName("hong");
		messages.setText("hello");
		//String json = mapper.writeValueAsString(messages);
		Message mess = new Message();
		mess = mapper.readValue(message, Message.class);
		
	    String username = (String) userSession.getUserProperties().get("username");
	    if (username == null) {
	      userSession.getUserProperties().put("username", mess.getName());
	      userSession.getBasicRemote().sendText(message);
	    } else {
	      for (Session session : users) {
	        session.getBasicRemote().sendText(message);
	      }
	    }
	  }
	  @OnClose
	  public void handleClose(Session session) {
	    users.remove(session);
	  }
	  @OnError
	  public void handleError(Throwable t) {
	    t.printStackTrace();
	  }

}
