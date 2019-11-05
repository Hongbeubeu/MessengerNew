package project1.com.messenger.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project1.com.messenger.config.DateTime;
import project1.com.messenger.dao.MessengerDAO;
import project1.com.messenger.entities.User;
import project1.com.messenger.entities.ChatSentence;
import project1.com.messenger.entities.Conversation;
import project1.com.messenger.entities.Friend;

@Service
@Transactional
public class MessengerService {
	@Autowired
	private MessengerDAO messengerDAO;
	
	public User findByEmail(String email) {
		return messengerDAO.findByEmail(email);
	}
	public User findFriendByEmail(String email) {
		return messengerDAO.findFriendByEmail(email);
	}
	public User findById(int id) {
		return messengerDAO.findById(id);
	}
	
	public User login(User user){
		User tuser = findByEmail(user.getEmail());
		if(tuser == null ) 
			return null;
		else
			try {
				if(MD5(user.getPassword()).equals(tuser.getPassword())) {
					tuser.setDate(DateTime.setIntToDate(tuser.getCreateAt()));
					return tuser;
				}else
					return null;
			} catch (NoSuchAlgorithmException e) {
				return null;
			}
	}
	
	public User register(User user){
		if(checkEmail(user.getEmail()) == false)
			return null;
		else {
			User tuser = findByEmail(user.getEmail());
			if(tuser == null) {
				if(user.getPassword().equals(user.getConfirmPassword()))
					try {
						user.setPassword(MD5(user.getPassword()));
						user.setCreateAt(DateTime.setDateToInt());
						messengerDAO.save(user);
						return user;
					} catch (NoSuchAlgorithmException e) {
						return null;
					}
				else {
					return null;
				}
			} else {
				return null;
			}
		}
	}
	public void update(User user){
		user.setCreateAt(DateTime.setDateToInt());
		messengerDAO.update(user);
	}
	
	private boolean checkEmail(String email) {
	    String emailPattern = "^[\\w!#$%&�*+/=?`{|}~^-]+(?:\\.[\\w!#$%&�*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	    Pattern regex = Pattern.compile(emailPattern);
	    Matcher matcher = regex.matcher(email);
	    if (matcher.find()) {
	        return true;
	    } else {
	       return false;
	    }
	}
	
	 private String MD5(String text) throws NoSuchAlgorithmException {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        byte[] messageDigest = md.digest(text.getBytes());
	        return convertByteToHex(messageDigest);
	 }
	 private String convertByteToHex(byte[] data) {
		  BigInteger number = new BigInteger(1, data);
		  String hashtext = number.toString(16);
		  while (hashtext.length() < 32) {
		    hashtext = "0" + hashtext;
		  }
		  return hashtext;
	}
	
	public void addFriend(int userId, int friendId) {
		if (userId == friendId) return;
		boolean check = messengerDAO.checkFriend(userId, friendId);
		if(check == false) {
			Friend friend1 = new Friend();
			friend1.setCreateAt(DateTime.setDateToInt());
			friend1.setUserId(userId);
			friend1.setFriendId(friendId);
			messengerDAO.addFriend(friend1);
			friend1.setUserId(friendId);
			friend1.setFriendId(userId);
			messengerDAO.addFriend(friend1);
		}
	}
	
	public List<User> findFriendOfUserId(int id) {
		List<User> listFriend = messengerDAO.findListFriendOfUserId(id);
		return listFriend;
	}
	
	public List<ChatSentence> findListChatSentence(int chatId){
		List<ChatSentence> chatSentence = messengerDAO.findListChatSentence(chatId);
		return chatSentence;
	}
	
	public Conversation findConversation(int chatId) {
		Conversation conversation = messengerDAO.findConversation(chatId);
		return conversation;
	}
	
	public boolean checkConversation(int chatId) {
		return messengerDAO.checkConversation(chatId);
	}
	
	public int getChatId(int userId, int friendId) {
		return messengerDAO.getChatId(userId, friendId);
	}
	
	public int setChatRoom(int userId, int friendId) {
		int date = messengerDAO.setChatRoom(friendId);
		return date;
	}
	
	public int setMemberOfChatRoom(int date, int userId, int friendId) {
		return messengerDAO.setMemberOfChatRoom(date, userId, friendId);
	}
	
	public int findNewestConversation(int userId) {
		return messengerDAO.findNewestConversation(userId);
	}
}
