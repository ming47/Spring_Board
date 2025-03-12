package com.kedu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kedu.dao.ReplyDAO;
import com.kedu.dto.ReplyDTO;

@Controller
@RequestMapping("/reply")
public class ReplyController {
	
	@Autowired
	private ReplyDAO dao;
	
	/*@RequestMapping("/insert")
	public ReplyDTO replyInsert(ReplyDTO dto) {
		int result = dao.replyInsert(dto);
		return "";	
	}*/

}
