package com.projet.BackendPfe.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.projet.BackendPfe.model.Generaliste;
import com.projet.BackendPfe.model.Patient;
import com.projet.BackendPfe.repository.GeneralisteRepository;
import com.projet.BackendPfe.repository.PatientRepository;




	@Service
	public class PatientService implements IGestionPatient {
		
		

		@Autowired
		PatientRepository patient;


		
		
		
		
		
		
		
		@Override
		public void  addPatient(Patient p) {
			patient.save(p);
			
		}
		@Override
		public List<Patient> getAllPatient() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public List<Patient> getPatientPMC(String mc) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public List<Patient> getPatient(int mc) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public void delete(int id) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void ajouterPatient(Patient p) {
			patient.save(p);		
		}
		

	}
