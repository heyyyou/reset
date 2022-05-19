package com.projet.BackendPfe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.BackendPfe.model.AutoDetection;
import com.projet.BackendPfe.model.AvisExpert;
import com.projet.BackendPfe.model.Consultation;
@Repository
public interface AvisExpertRepository extends JpaRepository<AvisExpert, Long> {
	
}
