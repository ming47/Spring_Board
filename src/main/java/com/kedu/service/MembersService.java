package com.kedu.service;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kedu.dao.MembersDAO;
import com.kedu.dto.MembersDTO;

@Service
public class MembersService {
	
	@Autowired
	private MembersDAO mDao;
	
	public boolean isIdExist(String id) {
		return mDao.isIdExist(id);
	}

	public MembersDTO selectById(String id) {
		return mDao.selectById(id);
	}

	public boolean login(String id, String pw) {	
		return mDao.login(id, pw);
	}

	public int insert(String realPath,MembersDTO dto,MultipartFile[] files) throws Exception{
		File realPathFile = new File(realPath);
		if(!realPathFile.exists()) {
			realPathFile.mkdir();
		}
		for(MultipartFile file : files) {
		String oriName = file.getOriginalFilename();
		String sysName = UUID.randomUUID()+"_" + oriName;
		file.transferTo(new File(realPath + "/" + sysName));
		dto.setProfile_image(sysName);
		}
		
		mDao.insert(dto);
		return 0;
	}

	public String getprofile(String id) {
		return mDao.getprofile(id);
	}

	public int deleteById(String id) {
		return mDao.deleteById(id);
	}
	
	
}
