package project1.com.messenger.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import project1.com.messenger.config.*;

import project1.com.messenger.entities.User;
import project1.com.messenger.entities.ChatSentence;
import project1.com.messenger.entities.Conversation;
import project1.com.messenger.entities.Friend;
import project1.com.messenger.entities.Id;
import project1.com.messenger.mapper.*;

@Repository
@Transactional
public class MessengerDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void save(User user) {
		String sql =  "INSERT INTO user "
					+ "(first_name, last_name, address, phone_number, avatar, gender, email, password, create_at) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql,user.getFirstName(),
								user.getLastName(),
								user.getAddress(),
								user.getPhoneNumber(),
								user.getAvatar(),
								user.getGender(), 
								user.getEmail(), 
								user.getPassword(),
								user.getCreateAt());
	}
	
	public void update(User user) {
		String sql = "UPDATE user SET first_name = ?, last_name = ?, address = ?, phone_number = ?, avatar = ?, gender = ?, email = ?, create_at = ? WHERE id = ?";
				   
		jdbcTemplate.update(sql,user.getFirstName(),
								user.getLastName(),
								user.getAddress(),
								user.getPhoneNumber(),
								user.getAvatar(),
								user.getGender(), 
								user.getEmail(),
								user.getCreateAt(),
								user.getId()
							);
	}
	
	public User findFriendByEmail(String email) {
		String sql = "SELECT id, first_name, last_name, avatar FROM user WHERE email = ?";
		try {
			User user =  jdbcTemplate.queryForObject(sql, new FriendListMapper(), email);
			return user;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public User findByEmail(String email) {
		String sql = "SELECT * FROM user WHERE email = ?";
		try {
			User user =  jdbcTemplate.queryForObject(sql, new UserMapper(), email);
			return user;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public User findById(int id) {
		String sql = "SELECT * FROM user WHERE id = ?";
		try {
			User user =  jdbcTemplate.queryForObject(sql, new UserMapper(), id);		
			return user;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public void addFriend(Friend friend) {
		String sql =  "INSERT INTO friend "
					+ "(user_id, friend_id, create_at)"
					+ "VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, friend.getUserId(),
								 friend.getFriendId(),
								 friend.getCreateAt()
							);
	}
	
	public List<User> findListFriendOfUserId(int id) {
		String sql = "SELECT id, first_name, last_name, avatar "
				   + "FROM user "
				   + "WHERE id in "
				   + "(SELECT friend_id "
				   + "FROM friend "
				   + "WHERE user_id = ?)";
		try {
			List<User> listFriend =  jdbcTemplate.query(sql, new FriendListMapper(), id);
			return listFriend;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public boolean checkFriend(int userId, int friendId) {
		String sql = "SELECT * "
				+ "FROM friend " 
				+ "WHERE user_id = ? "
				+ "AND friend_id = ? ";
		try {
			if(jdbcTemplate.queryForObject(sql, new FriendMapper(), userId, friendId) == null)
				return false;
			else 
				return true;
			
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}
	
	public Conversation findConversation(int chatId) {
		String sql = "SELECT * "
				+ "FROM conversation "
				+ "WHERE id = ? ";
		try {
			Conversation conversation = jdbcTemplate.queryForObject(sql, new ConversationMapper(), chatId);
			return conversation;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}		
	}
	
	public boolean checkConversation(int chatId) {
		String sql = "SELECT * "
				+ "FROM conversation "
				+ "WHERE id = ? ";
		try {
			if(jdbcTemplate.queryForObject(sql, new ConversationMapper(), chatId) == null)
				return true;
			else
				return false;
		}catch (EmptyResultDataAccessException e) {
			return true;
		}
	}
	
	public int getChatId(int userId, int friendId) {
		String sql = "SELECT conv1.conversation_id "
				+ "FROM conversation_member conv1, conversation_member conv2 "
				+ "WHERE conv1.conversation_id = conv2.conversation_id "
				+ "AND conv1.user_id = ? "
				+ "AND conv2.user_id = ? ";
		try {
			Id chatId = jdbcTemplate.queryForObject(sql, new IdConversationMapper(),  userId, friendId);
			return chatId.getId();
		}catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}
	
	public int setChatRoom(int friendId) {
		String sql = "INSERT INTO conversation "
				+ "(conversation_name, last_time, create_at) "
				+ "VALUES (?, ?, ?) ";
		User user = findById(friendId);
		String conversationName = user.getLastName() + " " + user.getFirstName();
		int date = DateTime.setDateToInt();
		jdbcTemplate.update(sql, conversationName,
								 date,
								 date);
		return date;
	}
	
	public int getConversationId(int date) {
		String sql = "SELECT id "
				+ "FROM conversation "
				+ "WHERE create_at = ? ";
		try {
			Id conversationId = jdbcTemplate.queryForObject(sql, new IdMapper(), date);
			return conversationId.getId();
		}catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}
	
	public int setMemberOfChatRoom(int date, int userId, int friendId) {
		String sql = "INSERT INTO conversation_member "
				+ "(conversation_id, user_id, create_at) "
				+ "VALUES (?, ?, ?) ";
		int conversationId = getConversationId(date);
		jdbcTemplate.update(sql, conversationId,
								 userId,
								 date);
		jdbcTemplate.update(sql, conversationId,
								 friendId,
								 date);
		return conversationId;
	}
	
	public int findNewestConversation(int userId) {
		String sql = "SELECT conversation_id "
				+ "FROM chat_sentence "
				+ "WHERE user_id = ? "
				+ "ORDER BY create_at DESC "
				+ "LIMIT 1";
		try {
			Id conversationId = jdbcTemplate.queryForObject(sql, new IdConversationMapper(), userId);
			return conversationId.getId();
		}catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}
	
	public List<ChatSentence> findListChatSentence(int chatId) {
		String sql = "SELECT user.avatar, chat.user_id, chat.context, chat.image_url, chat.create_at "
				+ "FROM chat_sentence chat, user "
				+ "WHERE chat.user_id = user.id "
				+ "AND chat.conversation_id = ? "
				+ "ORDER BY create_at ASC ";
		try {
			List<ChatSentence> listChat = jdbcTemplate.query(sql, new ChatSentenceMapper(), chatId);
			return listChat;
		}catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public void sendMessage(int chatId, int userId, String message) {
		String sql = "INSERT INTO chat_sentence "
				+ "(conversation_id, user_id, context, image_url, create_at) "
				+ "VALUES (?, ?, ?, ?, ?) ";
		int date = DateTime.setDateToInt();
		jdbcTemplate.update(sql, chatId,
								 userId,
								 message,
								 null,
								 date);	
	}
}
