package com.projet.BackendPfe.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.BackendPfe.model.AdminDigitalManager;
import com.projet.BackendPfe.model.AdminMedicalManager;

@Repository
public interface AdminMedicalManagerRepository  extends JpaRepository<AdminMedicalManager, Long>{
	
	Optional<AdminDigitalManager> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	Optional<AdminDigitalManager> findByImage(String image);

}
