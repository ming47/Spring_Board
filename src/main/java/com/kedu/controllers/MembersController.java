package com.kedu.controllers;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kedu.dao.MembersDAO;
import com.kedu.dto.MembersDTO;
import com.kedu.service.MembersService;

@Controller
@RequestMapping("/members")
public class MembersController {

	@Autowired
	private MembersService mServ;
	
	@Autowired // 보안에 좋지 않아, 프로젝트에서는 매개변수로 작성하는게 더 좋다
	private HttpSession session;

	@RequestMapping("/signupform")
	public String toSignupForm() {

		return "/members/signupform";
	}
	
	// AJAX는 비동기 통신이므로, 따로 한글깨짐을 방지
	@ResponseBody // 컨트롤러의 반환 값을 View가 아닌 HTTP 응답 본문으로 전달
	@RequestMapping(value="/idcheck", produces="text/html;charset=utf8") // 응답 데이터의 문자 인코딩을 UTF-8로 설정(파라미터가 여러개면 key값 필요) 
	public String idcheck(String id) {
		
		boolean result = mServ.isIdExist(id);
		return String.valueOf(result);
	}

	@RequestMapping("/insert") 
	public String insert(MembersDTO dto,MultipartFile[] files,HttpSession session) throws Exception{
		String realPath = session.getServletContext().getRealPath("upload");
		mServ.insert(realPath, dto, files);
		return "redirect:/";
	}
	
	@RequestMapping("/login") 
	public String login(String id, String pw) {
		boolean result = mServ.login(id, pw);
        if (result) {
            session.setAttribute("loginId", id);
            System.out.println("로그인 성공");
        } 
        return "redirect:/";
    }
	
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/memberout")
	public String deleteById(String id) {
		System.out.println(id);
		int result = mServ.deleteById(id);
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/tomypage") //JSP페이지 랜더링
	public String toMypage() {
		return "/members/mypage";
	}
	
	@ResponseBody
	@RequestMapping("/mypage") //페이지가 로드 된 후 ajax 요청 처리
	public MembersDTO mypage() {
		String id = (String)session.getAttribute("loginId");
		MembersDTO dto = mServ.selectById(id);
		return dto;
	}
	
	@ResponseBody
	@RequestMapping(value="/getprofile", produces="text/html;charset=utf8")
	public String getprofile() {
		String id = (String)session.getAttribute("loginId");
		String profile= mServ.getprofile(id);
		
		return profile;
	}
}
