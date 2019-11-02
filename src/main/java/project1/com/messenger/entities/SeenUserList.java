package project1.com.messenger.entities;

public class SeenUserList {
	private int chatSentenceId;
	private int userId;
	private int createAt;
	
	public SeenUserList() {
		
	}

	public int getChatSentenceId() {
		return chatSentenceId;
	}

	public void setChatSentenceId(int chatSentenceId) {
		this.chatSentenceId = chatSentenceId;
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
