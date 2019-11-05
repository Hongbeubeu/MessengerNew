package project1.com.messenger.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import project1.com.messenger.entities.ChatSentence;

public class ChatSentenceMapper implements RowMapper<ChatSentence>{
	public ChatSentence mapRow(ResultSet rs, int rowNum) throws SQLException{
		ChatSentence chatsentence = new ChatSentence();
		chatsentence.setAvatar(rs.getString("avatar"));
		chatsentence.setContext(rs.getString("context"));
		chatsentence.setImageUrl(rs.getString("image_url"));
		chatsentence.setCreateAt(rs.getInt("create_at"));
		return chatsentence;
	}
}
