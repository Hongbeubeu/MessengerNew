package project1.com.messenger.entities;

public class ConversationMember {
	private int conversationId;
	private int userId;
	private int createAt;
	
	public ConversationMember() {
		
	}

	public int getConversationId() {
		return conversationId;
	}

	public void setConversationId(int conversationId) {
		this.conversationId = conversationId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCreateAt() {
		return createAt;
	}

	public void setCreateAt(int createAt) {
		this.createAt = createAt;
	}
	
}
