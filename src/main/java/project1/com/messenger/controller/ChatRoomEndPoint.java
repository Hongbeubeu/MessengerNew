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

@ServerEndpoint(value = "/chatRoomServer")
public class ChatRoomEndPoint {
	 static Set<Session> users = Collections.synchronizedSet(new HashSet<Session>());
	  @OnOpen
	  public void handleOpen(Session session) throws IOException {
	    session.getBasicRemote().sendText("You are connected as: " + session.getId());
	    users.add(session);
	    sendListUserOnline();
	  }
	  @OnMessage
	  public void handleMessage(String message, Session userSession) throws IOException {
	    for (Session session : users) {
	      session.getBasicRemote().sendText(userSession.getId() + ": " + message);
	    }
	  }
	  public void sendListUserOnline() throws IOException {
	    for (Session session : users) {
	      session.getBasicRemote().sendText(buildListUser());
	    }
	  }
	  public String buildListUser() {
	    StringBuffer listUser = new StringBuffer("list_user");
	    for (Session session : users) {
	      listUser.append(session.getId() + " \n");
	    }
	    return listUser.toString();
	  }
	  @OnClose
	  public void handleClose(Session session) throws IOException {
	    users.remove(session);
	    sendListUserOnline();
	  }
	  @OnError
	  public void handleError(Throwable t) {
	    t.printStackTrace();
	  }

}
