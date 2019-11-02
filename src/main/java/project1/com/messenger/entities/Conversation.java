package project1.com.messenger.entities;

public class Conversation {
	private int id;
	private String conversationName;
	private int lastTime;
	private int createAt;
	
	public Conversation() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getConversationName() {
		return conversationName;
	}
	public void setConversationName(String conversationName) {
		this.conversationName = conversationName;
	}
	public int getLastTime() {
		return lastTime;
	}
	public void setLastTime(int lastTime) {
		this.lastTime = lastTime;
	}
	public int getCreateAt() {
		return createAt;
	}
	public void setCreateAt(int createAt) {
		this.createAt = createAt;
	}
	
}
