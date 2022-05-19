package com.projet.BackendPfe.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.projet.BackendPfe.model.Expert;
import com.projet.BackendPfe.repository.ExpertRepository;

@Service
public class ExpertService  implements IExpert{
	protected String gender ;
	protected long telephone;

	
	@Autowired
	ExpertRepository expertRepository;


	@Override
	public byte[] getImageExpert(long  id) throws Exception{
		String imageExpert =expertRepository.findById(id).get().getImage() ; 
		Path p =Paths.get(System.getProperty("user.home")+"/upload/",imageExpert);
		return Files.readAllBytes(p);
		
	}
	@Override
	public void updateImageExpert(long id, MultipartFile file) throws IOException {
			
			 Expert  expert = expertRepository.findById(id).get();
			 StringBuilder fileNames=new StringBuilder();
			 Path fileNameAndPath=Paths.get(System.getProperty("user.home")+"/upload/",file.getOriginalFilename()+"");
			 fileNames.append(file);
			 System.out.println("bagraa"+fileNameAndPath);
			 Files.write(fileNameAndPath, file.getBytes());
			 expert.setImage(file.getOriginalFilename());
			 
			 expertRepository.save(expert);}
	


	
	



	

}
