package com.kedu.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kedu.dao.BoardDAO;
import com.kedu.dao.FileDAO;
import com.kedu.dto.BoardDTO;
import com.kedu.dto.FileDTO;

@Service
public class BoardService {
		
	@Autowired
	private BoardDAO bDao;
	
	@Autowired
	private FileDAO fDao;

	public List<BoardDTO> selectAll() {
		return bDao.selectAll();
	}

	public BoardDTO findById(int seq) {
		return bDao.findById(seq);
	}

	public int deleteBySeq(int seq) {
		return bDao.deleteBySeq(seq);
	}
	
	@Transactional
	public int write(BoardDTO dto,MultipartFile[] files,HttpSession session) throws Exception{
		int parent_seq= bDao.getSeq();
		dto.setSeq(parent_seq);
		
		String realPath = session.getServletContext().getRealPath("upload");
		File realPathFile = new File(realPath);
		
		if(!realPathFile.exists()) realPathFile.mkdir();

		for(MultipartFile file : files) {
			if(!file.isEmpty()) {
				String oriName = file.getOriginalFilename();
				String sysName = UUID.randomUUID()+"_" + oriName;
				file.transferTo(new File(realPath + "/" + sysName));
				fDao.insert(new FileDTO(0,oriName,sysName,parent_seq));
			}
		}	
		return bDao.write(dto);
	}
	


}
