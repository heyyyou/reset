package com.projet.BackendPfe.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.BackendPfe.model.AutoDetection;
import com.projet.BackendPfe.model.AvisExpert;
import com.projet.BackendPfe.model.Consultation;
import com.projet.BackendPfe.model.Expert;
import com.projet.BackendPfe.model.Generaliste;
import com.projet.BackendPfe.model.Patient;
import com.projet.BackendPfe.repository.AutoDetectionRepository;
import com.projet.BackendPfe.repository.AvisExpertRepository;
import com.projet.BackendPfe.repository.ConsultationRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/Auto")
public class AutoDetectionController {
	@Autowired AutoDetectionRepository repository ;
	@Autowired ConsultationRepository pr;
    @Autowired AvisExpertRepository avis; 
	@PostMapping("/auto/{idGeneraliste}/{idConsultation}") // lorsque généraliste clique sur AI MODEL autoDetection va etre créer (2)
	public AutoDetection addConsultation(@PathVariable("idGeneraliste") long idGeneraliste , 
			                                                                 @PathVariable("idConsultation") long idConsultation  )
	{
		Consultation consultation = pr.findById(idConsultation).get(); 
	
	String maladieGauche="diabete";
	String maladieDroite="rhétino";
	int graviteDroite=0;
	int graviteGauche=0;
		
		

		AutoDetection autoDetection = new AutoDetection(maladieDroite,maladieGauche,graviteDroite,graviteGauche);
		repository.save(autoDetection) ;
		return autoDetection;


	}
		
	@PutMapping("ajouterReponseAvis/{idAutoDetection}/{idConsultation}/{idAvisExpert}") //updateIDAvis expert f classe mtaa autodetection wa9teh cad f entite autodetection avisExpert maadsh ?
	//wa9teli yjeweb expert al demande avis  ;) ekher etape
	public void updateIdAvisExpert(@PathVariable("idAutoDetection") long idAutoDetection, @PathVariable("idConsultation") long idConsultation ,@PathVariable ("idAvisExpert") long idAvisExpert){
		 Consultation consult =pr.findById(idConsultation).get();
	AutoDetection autoDetection = repository.findById(idAutoDetection).get(); 
	AvisExpert avisExpert=avis.findById(idAvisExpert).get();
		 autoDetection.setAvisExpert(avisExpert);
		 repository.save(autoDetection);

	}

}

