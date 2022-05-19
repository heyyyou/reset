package com.projet.BackendPfe.Controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.projet.BackendPfe.EmailSenderService;
import com.projet.BackendPfe.config.JwtTokenUtil;
import com.projet.BackendPfe.domaine.JwtResponse;
import com.projet.BackendPfe.domaine.Message;
import com.projet.BackendPfe.exception.ResourceNotFoundException;
import com.projet.BackendPfe.model.Consultation;
import com.projet.BackendPfe.model.Expert;
import com.projet.BackendPfe.model.Generaliste;
import com.projet.BackendPfe.repository.ConsultationRepository;
import com.projet.BackendPfe.repository.ExpertRepository;
import com.projet.BackendPfe.repository.UserRepository;
import com.projet.BackendPfe.request.LoginRequest;
import com.projet.BackendPfe.request.RegisterRequestExpert;
import com.projet.BackendPfe.request.RegisterRequestGeneraliste;
import com.projet.BackendPfe.services.ExpertService;
import com.projet.BackendPfe.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/expert")
public class ExpertController {
	
	@Autowired 	AuthenticationManager authenticationManager;
	@Autowired	ExpertRepository expertRepository;
	@Autowired	UserRepository userRepository;
		@Autowired	ConsultationRepository repository;
	@Autowired	private ExpertService expertService ;
	@Autowired	PasswordEncoder encoder;
	@Autowired	JwtTokenUtil jwtUtils;

	@Autowired  EmailSenderService senderService;

	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest data) {
		System.out.println("aaaa");
		System.out.println(data.getPassword());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						data.getUsername(),
						data.getPassword()));	
		  System.out.println("bbbbb");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(),
												 userDetails.getRole()
											));
	}
	
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerExpert(@Valid @RequestBody RegisterRequestExpert signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Email is already in use!"));
		}
		Expert expert = new Expert(signUpRequest.getUsername(), 
				 signUpRequest.getEmail(),
				 signUpRequest.getNomPrenom(),
				 encoder.encode(signUpRequest.getPassword()),
						 signUpRequest.getGender(),
						 signUpRequest.getTelephone(),
						 signUpRequest.getImage(),
				signUpRequest.getAdmin(), LocalDate.now() , "expert",signUpRequest.getReserve());
		expertRepository.save(expert);
		senderService.sendEmailAttachment("Welcome Doctor ! ", "Email Inline Attachment using MimeMessageHelper",
				"pfe4575@gmail.com",signUpRequest.getEmail(),true/*, new File("C:/icone/retina.png")*/);

		return ResponseEntity.ok(new Message("User registered successfully!"));
	}	  
	
	
	  @PutMapping("/update/{id}")
	  public ResponseEntity<Expert> updateExpert(@PathVariable("id") long id, @RequestBody Expert Utilisateur) {
	    System.out.println("Update Utilisateur with ID = " + id + "...");
	 
	    Optional<Expert> UtilisateurInfo = expertRepository.findById(id);

	    Expert utilisateur = UtilisateurInfo.get();
	    	utilisateur.setTelephone(Utilisateur.getTelephone());
	    	utilisateur.setGender(Utilisateur.getGender());
	    	utilisateur.setNomPrenom(Utilisateur.getNomPrenom());

	    	     //  utilisateur.getEmail();
	        // utilisateur.getUsername();
	    	
	      return new ResponseEntity<>(expertRepository.save(utilisateur), HttpStatus.OK);
	    } 
	  
	  @PutMapping("/updateImage/{id}")
	  public String updateExpert(@PathVariable("id") long id ,  @RequestParam("imageFile") MultipartFile imageFile ) throws IOException		    		  {
		  expertService.updateImageExpert(id,imageFile);
		    	return "Done !!!";
		    }

	  @GetMapping( "/getExpert/{id}" )
		public Expert getExpert(@PathVariable("id") long id) throws IOException {

		 return expertRepository.findById(id).get();

		}
	  @GetMapping( path = { "/getImage/{id}" }, produces= MediaType.IMAGE_JPEG_VALUE )
		public byte[] getImage(@PathVariable("id") long id) throws Exception {
      return expertService.getImageExpert(id) ; 
			
		
		}
	  @GetMapping("/getDemendeAvisEnvoyesParMonth")
	  public  int  getAllDemandesEnvoyesAExpertParMonth ( @RequestParam("month") Integer month) {
	              List<Consultation> liste = repository.findAll() ; 
	  	       // List<Expert> liste1 = expertRepository.findAll() ;
	  	        List<Consultation> res = new ArrayList<Consultation>() ; 

	  	        for(Consultation consult :liste ) {
	  	        	if(consult.getDateDemandeAvis()!= null) {
		  	      		Integer yearInscription = new Integer(consult.getDateConsult().getYear());

	  	        		Integer date =new Integer(consult.getDateDemandeAvis().getMonthValue()) ;
	  	        		if(date.equals(month)&& (yearInscription.equals(LocalDate.now().getYear()))) {
	  	        			if(consult.getAutoDetection().getAvisExpert()==null) {
	  			        	   res.add(consult) ; 
	  			        	}
	  		        	}
	  	        	}
	  	        }
	  	  		int nbrDemandes = res.size() ; 
	  	  		 
	  	  	  return nbrDemandes ;
	  	  	  }

	  @GetMapping("/getDemendeAvisEnvoyesParMonthAvecReponse/{idExpert}")
	  public  int  getAllDemandesEnvoyesAExpertParMonthAvecReponse ( @PathVariable("idExpert") long idExpert ,
	  		                                                       @RequestParam("month") Integer month) {
	              List<Consultation> liste = repository.findAll() ; 
	  	        List<Consultation> res = new ArrayList<Consultation>() ; 

	  	        for(Consultation consult :liste ) {
	  	        	if(consult.getDateDemandeAvis()!= null) {
	  	      		Integer yearInscription = new Integer(consult.getDateConsult().getYear());

	  	        		Integer date =new Integer(consult.getDateDemandeAvis().getMonthValue()) ;
	  	        		if(date.equals(month)&& (yearInscription.equals(LocalDate.now().getYear()))) {
	  	        			if(consult.getAutoDetection().getAvisExpert()!=null) {
	  	        				if(consult.getAutoDetection().getAvisExpert().getExpert()!= null) {
	  	        				if(consult.getAutoDetection().getAvisExpert().getExpert().getId()== idExpert) {
	  	 			        	   res.add(consult) ; 
	  	 			        	}
	  			        	}
	  		        	}
	  	        		}
	  	        	}
	  	        }
	  	  		int nbrDemandes = res.size() ; 
	  	  		 
	  	  	  return nbrDemandes ;
	  	  	  }

	 

 }

