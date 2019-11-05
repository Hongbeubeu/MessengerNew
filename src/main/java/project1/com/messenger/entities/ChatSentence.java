package project1.com.messenger.entities;

public class ChatSentence {
	private int userId;
	private String avatar;
	private String context;
	private String imageUrl;
	private int createAt;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public int getCreateAt() {
		return createAt;
	}
	public void setCreateAt(int createAt) {
		this.createAt = createAt;
	}
	
	
	
	
}
