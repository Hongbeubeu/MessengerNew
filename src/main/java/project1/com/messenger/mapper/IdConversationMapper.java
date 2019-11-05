package project1.com.messenger.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import project1.com.messenger.entities.Id;

public class IdConversationMapper implements RowMapper<Id>{
	public Id mapRow(ResultSet rs, int rowNum) throws SQLException {
		Id id = new Id();
		id.setId(rs.getInt("conversation_id"));
		return id;
	}
}
