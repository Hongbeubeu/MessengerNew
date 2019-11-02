package project1.com.messenger.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import project1.com.messenger.entities.ConversationMember;

public class ConversationMemberMapper implements RowMapper<ConversationMember>{
	public ConversationMember mapRow(ResultSet rs, int rowNum) throws SQLException {
		ConversationMember conversationMember = new ConversationMember();
		conversationMember.setConversationId(rs.getInt("conversation_id"));
		conversationMember.setUserId(rs.getInt("user_id"));
		conversationMember.setCreateAt(rs.getInt("create_at"));
		return conversationMember;
	}
}
