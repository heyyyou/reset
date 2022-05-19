package com.projet.BackendPfe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.BackendPfe.model.Expert;

@Repository
public interface ExpertRepository extends JpaRepository<Expert, Long>{
	
	Optional<Expert> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	Optional<Expert> findByImage(String image);


	
}
