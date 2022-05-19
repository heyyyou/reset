package com.projet.BackendPfe.Controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.projet.BackendPfe.EmailSenderService;
import com.projet.BackendPfe.config.JwtTokenUtil;
import com.projet.BackendPfe.domaine.JwtResponse;
import com.projet.BackendPfe.domaine.Message;
import com.projet.BackendPfe.exception.ResourceNotFoundException;
import com.projet.BackendPfe.model.AdminDigitalManager;
import com.projet.BackendPfe.model.Expert;
import com.projet.BackendPfe.model.Generaliste;
import com.projet.BackendPfe.model.User;
import com.projet.BackendPfe.repository.AdminDigitalMangerRepository;
import com.projet.BackendPfe.repository.ExpertRepository;
import com.projet.BackendPfe.repository.GeneralisteRepository;
import com.projet.BackendPfe.repository.UserRepository;
import com.projet.BackendPfe.request.LoginRequest;
import com.projet.BackendPfe.request.RegisterRequest;
import com.projet.BackendPfe.request.RegisterRequestAdmin;
import com.projet.BackendPfe.request.RegisterRequestExpert;
import com.projet.BackendPfe.request.RegisterRequestGeneraliste;
import com.projet.BackendPfe.services.ExpertService;
import com.projet.BackendPfe.services.GeneralisteService;
import com.projet.BackendPfe.services.UserDetailsImpl;
import com.projet.BackendPfe.services.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.springframework.boot.json.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired 	UserRepository repository;
	@Autowired 	AuthenticationManager authenticationManager;
	@Autowired	UserRepository userRepository;
	@Autowired	ExpertRepository expertRepository;
	@Autowired	GeneralisteRepository genRepository;
	@Autowired	AdminDigitalMangerRepository adminRepository;
	@Autowired UserDetailsServiceImpl serviceUser;
	
	@Autowired
	private ExpertService expertService ;
	
	@Autowired
	private GeneralisteService generalisteService ;
	
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
						data.getPassword())
			
				);
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
	
	
	@PostMapping("/send")
	public void  sendEmailReset(@RequestParam("email") String email)throws Exception {
		try {
		if(repository.existsByEmail(email) == true) {
			User user = repository.findByEmail(email);
			senderService.sendEmail(email ,"Reset password", "Votre mot de passe est : "+user.getReserve());
		}
			
		}catch(Exception e) {
		        e.printStackTrace();
		      }

	}
	
	

		/*****************Partie Admin ************/
		
		/*@PostMapping("/signupAdmin")
		public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequestAdmin signUpRequest) {
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

			// Create new user's account
			/*Date actuelle = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date = dateFormat.format(actuelle);
			Date dateInscription = date ; 
			AdminDigitalManager user = new AdminDigitalManager(signUpRequest.getUsername(), 
								 signUpRequest.getEmail(),
								 encoder.encode(signUpRequest.getPassword()),
										 signUpRequest.getImage() , LocalDate.now());
			adminRepository.save(user);

			return ResponseEntity.ok(new Message("User registered successfully!"));
		}	 */ 
		
		// rahy bsh twali put ll lokhrina update prodil expert et medecin 
	    @PostMapping("/addP")
	    public String saveProduct(@RequestParam("file") MultipartFile file,
	    		@RequestParam("pname") String name,
	    		@RequestParam("price") int price,
	    		@RequestParam("desc") String desc)
	    {
	    	//productService.saveProductToDB(file, name, desc, price);
	    	return "redirect:/listProducts.html";
	    }
	    /****************** Test put ************/
	    @PutMapping( "/updateEx/{id}")
		 public ResponseEntity<Message> saveUser (@PathVariable ("id") long id , @RequestParam("file") MultipartFile file,
				 @RequestParam("expert") String expert) throws JsonParseException , JsonMappingException , Exception
		 {
	    	
	       Expert expert1 = expertRepository.findById(id).get();
	       expert1 =new ObjectMapper().readValue(expert, Expert.class); 
	       Expert  resultat  = repository.save(expert1);
	        	return new ResponseEntity<Message>(new Message ("Expert  savrd "),HttpStatus.OK);	
		 }

		
		  /****** partie de projet 
	 @GetMapping("/users")
	  public List<User> getAllUtilisateur() {
	    System.out.println("Get all Utilisateur...");
	 
	    List<User> Utilisateur = new ArrayList<>();
	    repository.findAll().forEach(Utilisateur::add);
	 
	    return Utilisateur;	
	  }

	@GetMapping("/generaliste/{id}")
	public ResponseEntity<Generaliste> getGenById(@PathVariable(value = "id") long UtilisateurId)
			throws ResourceNotFoundException {
		Generaliste Utilisateur = genRepository.findById(UtilisateurId)
				.orElseThrow(() -> new ResourceNotFoundException("Utilisateur not found for this id : " + UtilisateurId));
		return ResponseEntity.ok().body(Utilisateur);
	}
	@GetMapping("/expert/{id}")
	public ResponseEntity<Expert> getExpertById(@PathVariable(value = "id") long UtilisateurId)
			throws ResourceNotFoundException {
Expert Utilisateur = expertRepository.findById(UtilisateurId)
				.orElseThrow(() -> new ResourceNotFoundException("Utilisateur not found for this id : " + UtilisateurId));
		return ResponseEntity.ok().body(Utilisateur);
	}

	//crud
	 
	
	
	
		@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteUtilisateur(@PathVariable(value = "id") Long UtilisateurId)
			throws ResourceNotFoundException {
		User Utilisateur = repository.findById(UtilisateurId)
				.orElseThrow(() -> new ResourceNotFoundException("Utilisateur not found  id :: " + UtilisateurId));

		repository.delete(Utilisateur);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/users/delete")
	  public ResponseEntity<String> deleteAllUtilisateur() {
	    System.out.println("Delete All Utilisateur...");
	 
	    repository.deleteAll();
	 
	    return new ResponseEntity<>("All Utilisateurs have been deleted!", HttpStatus.OK);
	  }
	 
	

	 /*@PutMapping("/Generaliste/{id}")
	  public String updateGeneraliste(@PathVariable("id") long id, @RequestParam("image") MultipartFile image,
	    	   	 //@RequestParam("username") String username,
	    		// @RequestParam("email") int email,
	    		 @RequestParam("gender") String gender,
	    		 @RequestParam("telephone") long telephone) {
	    System.out.println("Update Utilisateur with ID = " + id + "...");
	 
	    Optional<Generaliste> UtilisateurInfo = genRepository.findById(id);

	    	Generaliste utilisateur = UtilisateurInfo.get();
	    	utilisateur.setTelephone(utilisateur.getTelephone());
	    	utilisateur.setGender(utilisateur.getGender());
	    	      //  utilisateur.getEmail();
	        // utilisateur.getUsername();
	    	generalisteService.saveGeneraliste(id , image, utilisateur.getGender(), utilisateur.getTelephone());
	      return ("Done !!!");
	    } */
	 
		/*@PutMapping("/updateGeneraliste/{id}")
		 public String updateExpert(@PathVariable("id") long id,
													 @RequestParam("image") MultipartFile image,
										    	   	 //@RequestParam("username") String username,
										    		// @RequestParam("email") int email,
										    		 @RequestParam("gender") String gender,
										    		 @RequestParam("telephone") long telephone) {
			 Optional<Generaliste> expertA = genRepository.findById(id);

			 Generaliste expertP = expertA.get();
		        expertP.setTelephone(expertP.getTelephone());
		        expertP.setGender(expertP.getGender());
		        generalisteService.saveGeneraliste(id , image, expertP.getGender(), expertP.getTelephone());
			    	return "redirect:/listProducts.html";
			    }*/
		  
		    
		
}
