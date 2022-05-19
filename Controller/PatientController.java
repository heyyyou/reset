package com.projet.BackendPfe.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projet.BackendPfe.domaine.Message;
import com.projet.BackendPfe.model.Generaliste;
import com.projet.BackendPfe.model.Patient;
import com.projet.BackendPfe.repository.GeneralisteRepository;
import com.projet.BackendPfe.repository.PatientRepository;
import com.projet.BackendPfe.request.PatientRequest;
import com.projet.BackendPfe.request.RegisterRequestExpert;
import com.projet.BackendPfe.services.IGestionPatient;
import com.projet.BackendPfe.services.PatientService;


@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class PatientController {
	
	@Autowired GeneralisteRepository medecinRepository;

	@Autowired
	IGestionPatient gestionPatient;
	@Autowired
	PatientRepository pr;
	@Autowired
	GeneralisteRepository pre;
	@Autowired 
	PatientService ps;

	
	@GetMapping("/patiente/{id}")
	public List<Patient> getAllProducts(@PathVariable("id") @ModelAttribute("id") long id){
         //pr.findById(id);
		  
		 
		    return   pr.findAll();
	} 
	
/*	@GetMapping( "/patient/{cin}" )
			public Patient getExpert(@PathVariable("cin") long cin) throws IOException {

			 Patient expert = pr.findById(cin).get();
			  if(expert.getImage()== null) {
				  return expert;
			  }
			  else {
				    expert.setImage(ps.decompressZLib(expert.getImage()));	
				    return expert ;
			  }
	}*/
	
	@PostMapping("/addpatient/{id}")
	public ResponseEntity<?> AddProduct(@RequestBody PatientRequest patient , @PathVariable("id") @ModelAttribute("id") long id )throws IOException	
{
		Generaliste  generaliste = medecinRepository.findById(id).get(); 
		Patient ps = pr.findByCin(patient.getCin());
		if(ps==null) {
		Patient p = new Patient(patient.getCin(),patient.getUsername(),patient.getEmail(),patient.getTelephone(),patient.getGender(),patient.getDateNaiss(),patient.getAntecedant(),generaliste,LocalDate.now(),patient.getUsername()+" "+patient.getCin());
		pr.save(p);
		}
		else {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Username is already taken!"));
		}return ResponseEntity.ok(new Message("Patient saved!"));
	}
	/*@PostMapping("/addpatient/{id}")
	public void AddProduct(@RequestBody PatientRequest patient , @PathVariable("id") @ModelAttribute("id") long id ){
		Generaliste  generaliste = medecinRepository.findById(id).get(); 
		Patient p = new Patient(patient.getCin(),patient.getUsername(),patient.getEmail(),patient.getTelephone(),patient.getGender(),patient.getDateNaiss(),patient.getAntecedant(),generaliste);
		pr.save(p);
	}*/
	
	
	
	@GetMapping("/patient/{id}/{cin}")
	public Patient productById( @PathVariable("id") @ModelAttribute("id") long id,@PathVariable("cin") long cin ){
		return pr.findById(cin).get();
	}
	@DeleteMapping("/deletepatient/{id}/{cin}")
	public void deleteProduct(@PathVariable("cin") long cin, @PathVariable("id") @ModelAttribute("id") long id){
		 pr.findByGeneraliste_id(id);
	
		pr.deleteById(cin);
	} 
	@GetMapping("/patient/chercher/{username}")
	public List<Patient> patientByName(@PathVariable("username") String username ){
		return pr.findByUsernameContains(username);
	}
	 @PutMapping("/updatePatient/{id}/{cin}")  // check it 
	  public ResponseEntity<Patient> updateGeneraliste(@PathVariable("cin") long cin, @RequestBody Patient Utilisateur) {
	    System.out.println("Update Utilisateur with ID = " + cin + "...");
	 
	    Optional<Patient> UtilisateurInfo = pr.findById(cin);

	    	Patient utilisateur = UtilisateurInfo.get();
	       	utilisateur.setCin(Utilisateur.getCin());
	       	utilisateur.setUsername(Utilisateur.getUsername());
	       	utilisateur.setEmail(Utilisateur.getEmail());
	       	utilisateur.setDateNaiss(Utilisateur.getDateNaiss());
			
	      	utilisateur.setAntecedant(Utilisateur.getAntecedant());
	    	utilisateur.setTelephone(Utilisateur.getTelephone());
	    	utilisateur.setGender(Utilisateur.getGender());
	    	//utilisateur.setImage(Utilisateur.getImage());       //  utilisateur.getEmail();
	        // utilisateur.getUsername();
	    	
	      return new ResponseEntity<>(pr.save(utilisateur), HttpStatus.OK);
	    } 	
	 @GetMapping("/allParDate") // eli zedthom lyum
		public int  getAllPatientsByDateInscription(){
			      int total = pr.findByDateConsult(LocalDate.now()).size();
			       return total ; 
		} 
	 @GetMapping("/age/{idPatient}")
  public int getAgePatient(@PathVariable("idPatient") long idPatient) {
	 
		 Patient patient = pr.findById(idPatient).get() ; 
		 // get date de NAissance de patient --> de type string 
		 String  dateNaiss= patient.getDateNaiss() ; 
		 // regle pour transformer string to ---> localDate 
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		 // date de nasiisance de type local date 
		 LocalDate dateNaissance = LocalDate.parse(dateNaiss, formatter);
		 // date d'aujourd'hui
	     LocalDate date = LocalDate.now() ; 
	  int yearDateNaissance  =dateNaissance.getYear()  ;
	  int yearDateToDay = date.getYear()  ;
	  int agePatient = yearDateToDay-yearDateNaissance ; 
	  
	return agePatient ; } 
	 
	 
	 @GetMapping("/ageS")
  public int getAgePatientSupp() {
	 
		List <Patient> patients = pr.findAll() ; 
	     List<Patient> resultat = new ArrayList<Patient>() ; 

		 // get date de NAissance de patient --> de type string 
		for(Patient patient :patients) {
		 String  dateNaiss= patient.getDateNaiss() ; 

		 // regle pour transformer string to ---> localDate 
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		 // date de nasiisance de type local date 
		 LocalDate dateNaissance = LocalDate.parse(dateNaiss, formatter);
		 // date d'aujourd'hui
	     LocalDate date = LocalDate.now() ; 
	  int yearDateNaissance  =dateNaissance.getYear()  ;
	  int yearDateToDay = date.getYear()  ;
	  int agePatient = yearDateToDay-yearDateNaissance ; 
	  if(agePatient>50) {
	resultat.add(patient) ; 
		}
	  
		}
		int nbrPatient=resultat.size();
		return nbrPatient;
	 }
	 
	 @GetMapping("/ageI")
	  public int getAgePatientSup() {
		 
			List <Patient> patients = pr.findAll() ; 
		     List<Patient> resultat = new ArrayList<Patient>() ; 

			 // get date de NAissance de patient --> de type string 
			for(Patient patient :patients) {
			 String  dateNaiss= patient.getDateNaiss() ; 

			 // regle pour transformer string to ---> localDate 
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			 // date de nasiisance de type local date 
			 LocalDate dateNaissance = LocalDate.parse(dateNaiss, formatter);
			 // date d'aujourd'hui
		     LocalDate date = LocalDate.now() ; 
		  int yearDateNaissance  =dateNaissance.getYear()  ;
		  int yearDateToDay = date.getYear()  ;
		  int agePatient = yearDateToDay-yearDateNaissance ; 
		  if(agePatient<50) {
		resultat.add(patient) ; 
			}
		  
			}
			int nbrPatient=resultat.size();
			return nbrPatient;
		 }
	 
	 @GetMapping("/patientParMonth")
     public int getPatients(@RequestParam("month") Integer month) {
	     List<Patient> resultat = new ArrayList<Patient>() ; 
		 List<Patient> liste = pr.findAll() ; 
		 for(Patient patient :liste) {
				Integer yearInscription = new Integer(patient.getDateConsult().getYear());

		Integer monthInscription = new Integer(patient.getDateConsult().getMonthValue());
			 if(month.equals(monthInscription)&& (yearInscription.equals(LocalDate.now().getYear()))) {
				 resultat.add(patient) ; 
			 }
			 
			 
		 }
		 int nbrPatients = resultat.size() ; 
		 
	  return nbrPatients ;
	  }
	}
	
	