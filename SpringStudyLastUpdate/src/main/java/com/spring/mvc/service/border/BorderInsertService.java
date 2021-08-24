package com.spring.mvc.service.border;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.mvc.dao.MyBatisBorderDao;
import com.spring.mvc.model.BorderInsertModel;
import com.spring.mvc.model.beans.BorderBean;

@Service
public class BorderInsertService {
	
	@Autowired
	private MyBatisBorderDao myBatisBorderDao;
	
	@Autowired
	private ServletContext context;
	
	public void fileUpload(BorderInsertModel borderInsertModel) throws IllegalStateException, IOException {
		MultipartFile[] multipartFile = borderInsertModel.getFile();
		String filePath = context.getRealPath("/static/upload");

		StringBuilder originName = new StringBuilder();
		StringBuilder tempName = new StringBuilder();
		
		for(MultipartFile mfile : multipartFile) {
			String originalFile = mfile.getOriginalFilename();
			String originalFileExtension = originalFile.substring(originalFile.lastIndexOf("."));
			String tempFile = UUID.randomUUID().toString().replaceAll("-", "") + originalFileExtension;
			
			originName.append(originalFile);
			originName.append(", ");
			tempName.append(tempFile);
			tempName.append(", ");
			
			File file = new File(filePath, tempFile);
			if(!file.exists()) {
				file.mkdirs();
			}

			mfile.transferTo(file);
		}
		originName.delete(originName.length()-2, originName.length());
		tempName.delete(tempName.length()-2, tempName.length());
		
		BorderBean borderBean = new BorderBean();
		borderBean.setBorder_title(borderInsertModel.getBorder_title());
		borderBean.setBorder_content(borderInsertModel.getContent_textarea());
		borderBean.setBorder_file(originName.toString());
		borderBean.setBorder_tempfile(tempName.toString());
		borderBean.setWriter_name(borderInsertModel.getUser_name());
		borderBean.setWriter_ip(borderInsertModel.getUser_ip());
		
		int insertCount = myBatisBorderDao.insertBorder(borderBean);
		
	}
	
}
