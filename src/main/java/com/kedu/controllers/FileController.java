package com.kedu.controllers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kedu.dao.FileDAO;
import com.kedu.dto.FileDTO;

@Controller
@RequestMapping("/files")
public class FileController {
	
	@Autowired
	private FileDAO dao;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<FileDTO> selectAll(String parent_seq) {
		System.out.println(parent_seq);
		List<FileDTO> list = dao.selectBySeq(parent_seq);
		
		return list;
	}
	
	@RequestMapping("/download") // 서비스계층이 굳이 필요없는 예외코드 (각각 코드 의존성이 높아 나누기 애매함 )
	public void download(String sysName, String oriName, HttpServletResponse response) throws Exception{

		String realPath = session.getServletContext().getRealPath("upload");
		File target = new File(realPath + "/" + sysName);
		
		oriName = new String(oriName.getBytes("utf8"),"ISO-8859-1");
		response.setHeader("content-disposition","attachment;filename=\""+oriName+"\"");
		
		try(DataInputStream dis = new DataInputStream(new FileInputStream(target));
			DataOutputStream dos = new DataOutputStream(response.getOutputStream())){
			byte[] fileContents = dis.readAllBytes();
			dos.write(fileContents);
			dos.flush();
		}
	}
}
