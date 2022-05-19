package com.projet.BackendPfe.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.projet.BackendPfe.model.Generaliste;
import com.projet.BackendPfe.model.Patient;


public interface PatientRepository  extends JpaRepository<Patient , Long>{
	public List<Patient> findByUsernameContains(String mc);
	public Patient findByCin(long cin);
	public List<Patient> findByDateConsult (LocalDate date ) ; 

	public List<Patient> findByGeneraliste_id(@ModelAttribute("id") long id);
}
