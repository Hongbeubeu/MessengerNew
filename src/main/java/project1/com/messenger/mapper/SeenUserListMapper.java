package project1.com.messenger.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import project1.com.messenger.entities.SeenUserList;

public class SeenUserListMapper implements RowMapper<SeenUserList>{
	public SeenUserList mapRow(ResultSet rs, int rowNum) throws SQLException{
		SeenUserList seenUserList = new SeenUserList();
		seenUserList.setChatSentenceId(rs.getInt("chat_sentence_id"));
		seenUserList.setUserId(rs.getInt("user_id"));
		seenUserList.setCreateAt(rs.getInt("create_at"));
		return seenUserList;
	}
}
