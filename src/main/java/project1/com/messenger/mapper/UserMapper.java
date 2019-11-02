package project1.com.messenger.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import project1.com.messenger.entities.User;

public class UserMapper implements RowMapper<User> {
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setPhoneNumber(rs.getString("phone_number"));
		user.setAvatar(rs.getString("avatar"));
		user.setGender(rs.getString("gender"));
		user.setAddress(rs.getString("address"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setCreateAt(rs.getInt("create_at"));
		return user;
	}
}
