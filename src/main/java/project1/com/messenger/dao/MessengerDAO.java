package project1.com.messenger.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import project1.com.messenger.entities.User;
import project1.com.messenger.entities.Friend;
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
}
