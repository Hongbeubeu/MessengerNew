package project1.com.messenger.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import project1.com.messenger.entities.Friend;

public class FriendMapper implements RowMapper<Friend> {
	public Friend mapRow(ResultSet rs, int rowNum) throws SQLException {
		Friend friend = new Friend();
		friend.setUserId(rs.getInt("user_id"));
		friend.setFriendId(rs.getInt("friend_id"));
		friend.setCreateAt(rs.getInt("create_at"));
		return friend;
	}
}
