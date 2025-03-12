package com.kedu.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kedu.dto.BoardDTO;

@Repository
public class BoardDAO {
	
	@Autowired
	private JdbcTemplate jdbc;

	public List<BoardDTO> selectAll() {
		String sql = "select * from board order by seq DESC";
		return jdbc.query(sql, new BeanPropertyRowMapper<>(BoardDTO.class));
	}

	public int write(BoardDTO dto) {
		String sql = "insert into board (seq, title, writer, contents, write_date, view_count)" + 
						"values(?, ?, ?, ?, sysdate, 0)";
		System.out.println(dto.getTitle()+":"+dto.getWriter()+":"+dto.getContents());
		return jdbc.update(sql, dto.getSeq(),dto.getTitle(),dto.getWriter(),dto.getContents());
	}

	public BoardDTO findById(int seq) {
		String sql = "select * from board where seq = ?";
		return jdbc.queryForObject(sql, new BeanPropertyRowMapper<BoardDTO>(BoardDTO.class), seq);
	}

	public int deleteBySeq(int seq) {
		String sql = "delete from board where seq=?";
		return jdbc.update(sql,seq);
	}

	public int getSeq() {
		String sql = "select board_seq.nextval from dual";
		return jdbc.queryForObject(sql, Integer.class);
	}

}
