package com.projet.BackendPfe.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IGeneraliste {

public byte[] getImageGeneraliste(long  id) throws Exception;
	
	public void updateImageGeneraliste(long id , MultipartFile file) throws IOException;
}