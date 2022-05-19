package com.projet.BackendPfe.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.projet.BackendPfe.model.Expert;
import com.projet.BackendPfe.model.Generaliste;
import com.projet.BackendPfe.repository.ExpertRepository;
import com.projet.BackendPfe.repository.GeneralisteRepository;

@Service
public class GeneralisteService  implements IGeneraliste{
	protected String gender ;
	protected long telephone;

	
	@Autowired
	GeneralisteRepository generalisteRepository;


	@Override
	public byte[] getImageGeneraliste(long id) throws Exception{
		String imageGeneraliste =generalisteRepository.findById(id).get().getImage() ; 
		Path p =Paths.get(System.getProperty("user.home")+"/upload/",imageGeneraliste);
		return Files.readAllBytes(p);
		
	}
	@Override
	public void updateImageGeneraliste(long id, MultipartFile file) throws IOException {
			
			 Generaliste  generaliste = generalisteRepository.findById(id).get();
			 StringBuilder fileNames=new StringBuilder();
			 Path fileNameAndPath=Paths.get(System.getProperty("user.home")+"/upload/",file.getOriginalFilename()+"");
			 fileNames.append(file);
			 System.out.println("bagraa"+fileNameAndPath);
			 Files.write(fileNameAndPath, file.getBytes());
			 generaliste.setImage(file.getOriginalFilename());
			 
			 generalisteRepository.save(generaliste);}

}
