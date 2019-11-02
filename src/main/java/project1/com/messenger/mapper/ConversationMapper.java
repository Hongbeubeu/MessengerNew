package project1.com.messenger.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import project1.com.messenger.entities.Conversation;

public class ConversationMapper implements RowMapper<Conversation>{
	public Conversation mapRow(ResultSet rs, int rowNum) throws SQLException {
		Conversation conversation = new Conversation();
		conversation.setId(rs.getInt("id"));
		conversation.setConversationName(rs.getString("conversation_name"));
		conversation.setLastTime(rs.getInt("last_time"));
		conversation.setCreateAt(rs.getInt("create_at"));
		return conversation;
	}
}
