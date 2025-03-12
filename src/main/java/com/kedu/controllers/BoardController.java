package com.kedu.controllers;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kedu.dao.BoardDAO;
import com.kedu.dao.FileDAO;
import com.kedu.dto.BoardDTO;
import com.kedu.dto.FileDTO;
import com.kedu.service.BoardService;



@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService bServ;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/tolist")
	public String toBoard() {
		return "/board/list";
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public List<BoardDTO> selectAll() {
		List<BoardDTO> list = bServ.selectAll();
		return list;
	}
	
	@RequestMapping("/towrite")
	public String toWrite() {
		return "/board/write";//작성폼 보여주기
	}
	
	@RequestMapping("/write") //작성폼에서 작성됨
	public String write(BoardDTO dto, MultipartFile[] files,HttpSession session) throws Exception{
		int result = bServ.write(dto, files, session);
		return "redirect:/board/tolist";
	}
	
	@RequestMapping("/detail")
	public String findById(int seq, Model model) {
		BoardDTO board = bServ.findById(seq);
		model.addAttribute("board", board);
		return "/board/detail";
	}
	
	@RequestMapping("/update")
	public String update() {
		return "";
	}
	
	@RequestMapping("/delete")
	public String delete(int seq) {
		BoardDTO board = bServ.findById(seq);
		System.out.println(seq);
		String mdto = (String) session.getAttribute("loginId");
		if(board.getWriter().equals(mdto)) {
			int result = bServ.deleteBySeq(seq);
			return "redirect:/board/tolist";
		}else {
	        // 작성자가 다르면 삭제 권한이 없으므로 상세 페이지로 리다이렉트
	        return "redirect:/board/detail?seq=" + seq;  
	    }
	}
	
}
