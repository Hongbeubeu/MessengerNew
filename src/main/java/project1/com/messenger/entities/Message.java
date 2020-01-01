package project1.com.messenger.entities;

public class Message {
	private String text;
	private int userId;
	private int conversationId;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getConversationId() {
		return conversationId;
	}
	public void setConversationId(int conversationId) {
		this.conversationId = conversationId;
	}
	
}
