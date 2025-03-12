package com.kedu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kedu.dto.MembersDTO;

@Repository
public class MembersDAO {
	
	@Autowired
	private JdbcTemplate jdbc;

	public int insert(MembersDTO dto) { // 회원가입
		String sql = "insert into members values(?,?,?,?,?)";
		System.out.println("dao에서 이미지 값 : " + dto.getProfile_image());
		return jdbc.update(sql,dto.getId(),dto.getPw(),dto.getName(),dto.getContact(),dto.getProfile_image());
	}

	public boolean isIdExist(String id) { // 아이디 중복체크
		String sql = "select count(*) from members where id = ?";
		int result = jdbc.queryForObject(sql, Integer.class, id);
		return result > 0;
	}
	
	public boolean login(String id, String pw) {
		String sql = "select count(*) from members where id=? and pw=?";
		int result = jdbc.queryForObject(sql, Integer.class, id, pw);
		return result > 0;
	}

	public int deleteById(String id) {
		String sql = "delete from members where id =?";
		return jdbc.update(sql,id);
	}

	public MembersDTO selectById(String id) {
		String sql = "select * from members where id =?";
		return jdbc.queryForObject(sql, new RowMapper<MembersDTO>() {
			@Override
			public MembersDTO mapRow(ResultSet rs, int rowNum) throws SQLException{
				MembersDTO dto = new MembersDTO();
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setContact(rs.getString("contact"));
				return dto;
			}
		}, id);
	}

	public String getprofile(String id) {
		String sql = "SELECT profile_image FROM members WHERE id = ?";
		return jdbc.queryForObject(sql,String.class, id);
	}
}
