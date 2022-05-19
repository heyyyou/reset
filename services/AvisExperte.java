package com.projet.BackendPfe.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.BackendPfe.model.AvisExpert;
import com.projet.BackendPfe.model.Consultation;
import com.projet.BackendPfe.repository.AvisExpertRepository;
import com.projet.BackendPfe.repository.ConsultationRepository;
@Service

public class AvisExperte {
	@Autowired AvisExpertRepository repository ; 
	@Autowired ConsultationRepository repC ; 

	public Consultation  getDiffTime (long idAvisExpert , LocalDateTime dateAct , LocalDateTime dateConsult) {
		   long idConsult=0;
		 List<Consultation> consults=repC.findAll();
	  AvisExpert avisexp = repository.findById(idAvisExpert).get() ;
	  long idAvisExp=avisexp.getId();
	  for(Consultation consult :consults) {
		  if(consult.getAutoDetection()!=null) {
			  if(consult.getAutoDetection().getAvisExpert()!=null) {
			  if(consult.getAutoDetection().getAvisExpert().getId()==idAvisExp) {
				  idConsult=consult.getId();
				  Duration duration = Duration.between(LocalDateTime.now(), dateConsult);
			        long difM  =Math.abs(duration.toMinutes()) ; 
			    
			        if(difM == 0 ) {
				        long difS  =Math.abs(duration.toMillis())/1000 ; 
				        avisexp.setDecalageHoraireGen(difS+" "+"s");
			  	        repository.save(avisexp) ; 
			  	      
			       }
			        else if(difM <60) {
			        	 avisexp.setDecalageHoraireGen(difM +" "+"min");
				       repository.save(avisexp) ; 
				      
			       }else if( difM < 1440) {
			    	   avisexp.setDecalageHoraireGen(difM/60 +" "+"heure");
				       repository.save(avisexp) ; 

			  }
			       else if( difM >= 1440 && difM <2880) {
			    	   avisexp.setDecalageHoraireGen("1 jour");
			  	        repository.save(avisexp) ; 
			  	      
			       }
			        else if( difM >= 2880 && difM <4320) {
			        	avisexp.setDecalageHoraireGen("2 jour");
			  	        repository.save(avisexp) ; 
			  	      
			       }
			        else if( difM >= 4320 && difM <5760) {
			        	avisexp.setDecalageHoraireGen("3 jour");
			  	        repository.save(avisexp) ; 
			  	      
			       }
			        else if( difM >= 5760 && difM <7200) {
			        	avisexp.setDecalageHoraireGen("4 jour");
			  	        repository.save(avisexp) ; 
			  	      
			       }
			        else if( difM >= 7200 && difM <8640) {
			        	avisexp.setDecalageHoraireGen("5 jour");
			  	        repository.save(avisexp) ; 
			  	      
			       }
			        else if( difM >= 8640 && difM <10080) {
			        	avisexp.setDecalageHoraireGen("6 jour");
			  	        repository.save(avisexp) ; 
			  	      
			       }
			       
			        else if( difM < 10080) {
			        	avisexp.setDecalageHoraireGen("1 semaine");
			  	        repository.save(avisexp) ; 
			  	      
			       }
			        else if( difM <20160) {
			        	avisexp.setDecalageHoraireGen("2 semaine");
			  	        repository.save(avisexp) ; 
			  	      
			       }
			        else if( difM < 30240) {
			        	avisexp.setDecalageHoraireGen("3 semaine");
			  	        repository.save(avisexp) ; 
			  	      
			       }
			        else if( difM < 40320) {
			        	avisexp.setDecalageHoraireGen("4 semaine");
			  	        repository.save(avisexp) ; 
			  	      
			       }
			        else if( difM >= 50400){
			        	 DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
			   		   String formatDateTime = avisexp.getReponseAvisTime().format(format); 
			   		avisexp.setDecalageHoraireGen(formatDateTime);
					       repository.save(avisexp) ;
			        }
		  }	
		  }
	  }
		


       
    
       }
	 return repC.findById(idConsult).get(); }} 	


