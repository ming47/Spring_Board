package com.kedu.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kedu.dto.ReplyDTO;

@Repository
public class ReplyDAO {
	
	@Autowired
	private JdbcTemplate jdbc;

	public int replyInsert(ReplyDTO dto) {
		String sql = "insert into reply(id, writer, contents, parent_seq) values (reply_seq.nextval,?,?,?)";
		return jdbc.update(sql,dto.getWriter(),dto.getContents(),dto.getParent_seq());
		
	}

}
