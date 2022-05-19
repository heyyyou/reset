package com.projet.BackendPfe.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.util.StreamUtils;


import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.projet.BackendPfe.model.AutoDetection;
import com.projet.BackendPfe.model.AvisExpert;
import com.projet.BackendPfe.model.Consultation;
import com.projet.BackendPfe.model.Expert;
import com.projet.BackendPfe.model.Generaliste;
import com.projet.BackendPfe.model.Patient;
import com.projet.BackendPfe.repository.AutoDetectionRepository;
import com.projet.BackendPfe.repository.AvisExpertRepository;
import com.projet.BackendPfe.repository.ConsultationRepository;
import com.projet.BackendPfe.repository.ExpertRepository;
import com.projet.BackendPfe.repository.GeneralisteRepository;
import com.projet.BackendPfe.repository.PatientRepository;
import com.projet.BackendPfe.services.AvisExperte;
import com.projet.BackendPfe.services.ConsultationService;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/consultation")



public class ConsulationController {
	public static String uploadDirectory=System.getProperty("user.home")+"/upload";

	@Autowired ConsultationRepository repository ;
	@Autowired GeneralisteRepository medecinRepository;
	@Autowired PatientRepository patientRepository;
	@Autowired ExpertRepository expertRepository;
	@Autowired ConsultationService service ; 
	@Autowired AvisExperte service2 ; 
	@Autowired AutoDetectionRepository pr ; 
    @Autowired AvisExpertRepository avis; 

	
   /* @GetMapping("/test/{idExpert}")
	public List<Consultation> getttt (@PathVariable("idExpert") long idExpert){ //listeDemandeAvis pour Expert
    	List<Consultation> liste_Droite = repository.findByDemandeAvisD(1) ; 
		List<Consultation> liste1 = new ArrayList<Consultation>();// Empty
		for(Consultation consult :liste_Droite) {
			if((consult.getAutoDetection().getAvisExpert()==null)) {
		
				liste1.add(service.getDiffTime(consult.getId() , LocalDateTime.now(), consult.getDemandeAvisTime()))  ; 
			}
			else {
				// dejaa avis mtaa gauche existe 
				if((consult.getAutoDetection().getAvisExpert().getMaladieDroite()== null)
						&&(consult.getAutoDetection().getAvisExpert().getGraviteDroite()==0 )) {
					
					 liste1.add(service.getDiffTime(consult.getId() , LocalDateTime.now(), consult.getDemandeAvisTime()))  ; 
				}
			}
			}
		List<Consultation> liste_Gauche = repository.findByDemandeAvisG(1) ; 
		List<Consultation> liste2 = new ArrayList<Consultation>();
		for(Consultation consu :liste_Gauche) {
			if((consu.getAutoDetection().getAvisExpert()==null)) {
				
				liste2.add(service.getDiffTime(consu.getId() , LocalDateTime.now(), consu.getDemandeAvisTime()))  ; 
			}
			else {
				if((consu.getAutoDetection().getAvisExpert().getMaladieGauche()==null)
						&&(consu.getAutoDetection().getAvisExpert().getGraviteGauche()==0 )) {
					
					 liste2.add(service.getDiffTime(consu.getId() , LocalDateTime.now(), consu.getDemandeAvisTime()));
				}
			}
			}
		liste1.addAll(liste2) ;
		List<Consultation> resultats =new ArrayList<Consultation>() ; 
		resultats=liste1; 
		return resultats ; 
	
	}*/
    
     @GetMapping("/test/{idExpert}")
 public List<Consultation> gettttt(){
		 
		 List<Consultation> res= new ArrayList<Consultation>() ; 
		 List<Consultation> liste = repository.findAll() ;
		 
		 for(Consultation consultation : liste) {
			 if(consultation.getAutoDetection()!= null) {
				 if(consultation.getAutoDetection().getAvisExpert()== null) {
					 
					res.add(service.getDiffTime(consultation.getId(), LocalDateTime.now(),consultation.getDemandeAvisTime()));	
				
				 
			 }
		 }
	
	 }	 return res ; 
}
    

    @GetMapping("/NotifGen/{idGeneraliste}")
  	public List<Consultation> gett ( @PathVariable("idGeneraliste") long idGeneraliste ){
  		List<Consultation> listeConsult = repository.findAll() ;
  		List<Consultation> liste1 = new ArrayList<Consultation>();// Emptyù

  		for(Consultation consult :listeConsult) {
  			if(consult.getGeneraliste().getId()==idGeneraliste) {
  	  	  		if(consult.getAutoDetection()!=null) {
  	  			if((consult.getAutoDetection().getAvisExpert()!=null)) {
  					service2.getDiffTime(consult.getAutoDetection().getAvisExpert().getId() , LocalDateTime.now(), consult.getAutoDetection().getAvisExpert().getReponseAvisTime()) ; 
 
  	  			
  			liste1.add(consult);	
  			}
  		}
  		
  			}
  		}
		return liste1;}
  		
   /* @GetMapping("/NotifExpert/{idExpert}")
  	public List<Consultation> gettt ( @PathVariable("idExpert") long idExpert ){
  		List<Consultation> listeConsult = repository.findAll() ;
  		List<Consultation> liste1 = new ArrayList<Consultation>();// Emptyù

  		for(Consultation consult :listeConsult) {
  			if(consult.getAutoDetection().getAvisExpert().getExpert().getId()==idExpert) {
  	  	  		if(consult.getAutoDetection()!=null) {
  	  			if((consult.getAutoDetection().getAvisExpert()!=null)) {
  					service2.getDiffTime(consult.getAutoDetection().getAvisExpert().getId() , LocalDateTime.now(), consult.getAutoDetection().getAvisExpert().getReponseAvisTime()) ; 
 
  	  			
  			liste1.add(consult);	
  			}
  		}
  		
  			}
  		}
		return liste1;}
  		
    */
    /*@GetMapping("test")
    public List<Consultation> getAllDemandesss (){
    	List<Consultation> liste = repository.findAll();
    	List<Consultation> resultat= new ArrayList<>() ; 
    	for(Consultation consult :liste) {
    		if((consult.getAutoDetection().getAvisExpert()==null)) {
    			if((consult.getDemandeAvisD()==1 && consult.getDemandeAvisG()==0)) {
    			     resultat.add(consult);}
    			if((consult.getDemandeAvisD()==0 && consult.getDemandeAvisG()==1)) {
    			     resultat.add(consult);}
    		  
    			if((consult.getDemandeAvisD()==1 && consult.getDemandeAvisG()==1)) {
    				if(! resultat.contains(consult)) {
    					 resultat.add(consult);
    			    }
    				}
    		
    	}
    	
    	}
    	return resultat ; 
    }/*/
    @GetMapping("/demandes")
    public List<Consultation> getAllDemandes(){
    	    	List<Consultation> liste = repository.findAll();
    	    	List<Consultation> resultat= new ArrayList<>() ; 
    	    	for(Consultation consult :liste) {
    	    		if((consult.getDemandeAvisD()==1 || consult.getDemandeAvisG()==1)){
    	    			if((consult.getAutoDetection().getAvisExpert()==null)) {
    	    				resultat.add(consult);
    	    				
    	    			
    	    			}
    	    		}
    	    	}
    	    	return resultat ; 
    }
    @GetMapping("/Consultations") // hdhy st79itha f expert avis => besh yjiw kol ama f html mtaa angular besh naamlu condiition ala id AvisExpert f autotDetection eli huwa meloul besh ykoun null khater ma7tinch avis 
 	public List<Consultation> getAllConsultation(){
         //pr.findById(id);

	    return  repository.findAll();

	} 
    
	@PostMapping("/Consultations/{idGeneraliste}/{idPatient}")
	public Consultation addConsultation(@PathVariable("idGeneraliste") long idGeneraliste , 
			                                                                 @PathVariable("idPatient") long idPatient   ){
		Generaliste  generaliste = medecinRepository.findById(idGeneraliste).get(); 
		Patient patient = patientRepository.findById(idPatient).get() ; 
		String image1 = null ; 
		String image2 = null ; 
        String image3=null;
        String image4=null;
        String image5=null;
        String image6=null;
        String image7=null;
        String image8=null;
        String image9=null;
        String image10=null;


        		
		


		Consultation consultation = new Consultation(0,LocalDate.now(),generaliste, patient,image1,image2,image3,image4,image5,image6,image7,image8,image9,image10);
		repository.save(consultation) ;

		return consultation ; 

	}
	
	

	
	
	
	
	//deleteAll Pictures 
	@PutMapping("consultation/picturesD/{id}/{idConsultation}")
	public void deleteConsult(@PathVariable("id") long id, @PathVariable("idConsultation") long idConsultation){
		 Consultation consult =repository.findById(idConsultation).get();
		 consult.setImage1_Droite(null);
		
		 repository.save(consult);

	}
	@PutMapping("consultation/picturesG/{id}/{idConsultation}")
	public void deleteConsultG(@PathVariable("id") long id, @PathVariable("idConsultation") long idConsultation){
		 Consultation consult =repository.findById(idConsultation).get();
		 consult.setImage1_Gauche(null);
		
		 repository.save(consult);

	}
	@PutMapping("consultation/rating/{idConsultation}")
	public void putRating(@RequestParam("rating") int rating ,@PathVariable("idConsultation") long idConsultation){
		 Consultation consult =repository.findById(idConsultation).get();
		 consult.setRating(rating);
		
		 repository.save(consult);

	}

	// put for expert baed f avis demander 
	
	/*@PutMapping("SendConsultation/{idConsultation}/{idExpert}")
	public Consultation EnvoyerConultationAunExpert(@PathVariable("idConsultation") long idConsultation , 
			                                                                                               @PathVariable("idExpert") long idExpert) {
		Consultation consultation = repository.findById(idConsultation).get() ;
		Expert expert = expertRepository.findById(idExpert).get() ;
	   consultation.setExpert(expert);
	   repository.save(consultation) ;
	   return consultation ; 
	}/*
	/***********Oeil Droite *************/
	
	
	
	
	/*@PutMapping("/addimage1D/{idConsultation}")
	public String updateImage1D(@PathVariable("idConsultation") long idConsultation  , @RequestParam("image1") MultipartFile image1) throws IOException {
		service.updateImage1Droite(idConsultation , image1);
		return "Done pour image1 Droite !!!!" ; 
		
	}*/
	

	@PutMapping("/addimage1D/{idConsultation}")
	public String updateImage1D(@PathVariable("idConsultation") long idConsultation  , @RequestParam("image1") MultipartFile image1 ) throws IOException {
			updateImage1Droite(idConsultation,  image1);
		return "Done pour image2  Droite!!!!" ; 
	}
	public void updateImage1Droite(long id , MultipartFile file) throws IOException {
		
		 Consultation  consultation = repository.findById(id).get();
		 StringBuilder fileNames=new StringBuilder();
		 Path fileNameAndPath=Paths.get(uploadDirectory,file.getOriginalFilename()+"");
		 fileNames.append(file);
		 System.out.println("bagraa"+fileNameAndPath);
		 Files.write(fileNameAndPath, file.getBytes());
		 consultation.setImage1_Droite(file.getOriginalFilename());
		 
		repository.save(consultation);
	}
	
	/************************************************/
	
	@PutMapping("/addimage2D/{idConsultation}")
	public String updateImage2D(@PathVariable("idConsultation") long idConsultation  , @RequestParam("image2") MultipartFile image2 ) throws IOException {
			updateImage2Droite(idConsultation,  image2);
		return "Done pour image2  Droite!!!!" ; 
	}
	public void updateImage2Droite(long id , MultipartFile file) throws IOException {
		
		 Consultation  consultation = repository.findById(id).get();
		 StringBuilder fileNames=new StringBuilder();
		 Path fileNameAndPath=Paths.get(uploadDirectory,file.getOriginalFilename()+"");
		 fileNames.append(file);
		 System.out.println("bagraa"+fileNameAndPath);
		 Files.write(fileNameAndPath, file.getBytes());
		 consultation.setImage2_Droite(file.getOriginalFilename());
		 
		repository.save(consultation);
	}
	/*************************************************************************/
	@PutMapping("/addimage3D/{idConsultation}")
	public String updateImage3D(@PathVariable("idConsultation") long idConsultation  , @RequestParam("image3") MultipartFile image3 ) throws IOException {
			updateImage1Droite(idConsultation,  image3);
		return "Done pour image2  Droite!!!!" ; 
	}
	public void updateImage3Droite(long id , MultipartFile file) throws IOException {
		
		 Consultation  consultation = repository.findById(id).get();
		 StringBuilder fileNames=new StringBuilder();
		 Path fileNameAndPath=Paths.get(uploadDirectory,file.getOriginalFilename()+"");
		 fileNames.append(file);
		 System.out.println("bagraa"+fileNameAndPath);
		 Files.write(fileNameAndPath, file.getBytes());
		 consultation.setImage3_Droite(file.getOriginalFilename());
		 
		repository.save(consultation);
	}
	/*************************************************************************************/
	@PutMapping("/addimage4D/{idConsultation}")
	public String updateImage4D(@PathVariable("idConsultation") long idConsultation  , @RequestParam("image4") MultipartFile image4 ) throws IOException {
			updateImage4Droite(idConsultation,  image4);
		return "Done pour image2  Droite!!!!" ; 
	}
	public void updateImage4Droite(long id , MultipartFile file) throws IOException {
		
		 Consultation  consultation = repository.findById(id).get();
		 StringBuilder fileNames=new StringBuilder();
		 Path fileNameAndPath=Paths.get(uploadDirectory,file.getOriginalFilename()+"");
		 fileNames.append(file);
		 System.out.println("bagraa"+fileNameAndPath);
		 Files.write(fileNameAndPath, file.getBytes());
		 consultation.setImage4_Droite(file.getOriginalFilename());
		 
		repository.save(consultation);
	}
	/******************************************************************************************/
	@PutMapping("/addimage5D/{idConsultation}")
	public String updateImage5D(@PathVariable("idConsultation") long idConsultation  , @RequestParam("image5") MultipartFile image5 ) throws IOException {
			updateImage5Droite(idConsultation,  image5);
		return "Done pour image2  Droite!!!!" ; 
	}
	public void updateImage5Droite(long id , MultipartFile file) throws IOException {
		
		 Consultation  consultation = repository.findById(id).get();
		 StringBuilder fileNames=new StringBuilder();
		 Path fileNameAndPath=Paths.get(uploadDirectory,file.getOriginalFilename()+"");
		 fileNames.append(file);
		 System.out.println("bagraa"+fileNameAndPath);
		 Files.write(fileNameAndPath, file.getBytes());
		 consultation.setImage5_Droite(file.getOriginalFilename());
		 
		repository.save(consultation);
	}
	
	
	
	
	@GetMapping(path="imageDroite1/{id}" , produces= MediaType.IMAGE_JPEG_VALUE)
    public  byte[] getImageDroite1(@PathVariable("id") long id) throws Exception{
    	return service.getImageDroite1(id);
    }
    
    @GetMapping(path="imageGauche1/{id}" , produces= MediaType.IMAGE_JPEG_VALUE)
    public  byte[] getImageGauche1(@PathVariable("id") long id) throws Exception{
    	return service.getImageGauche1(id);
    }
    
    
    @GetMapping(path="imageDroite2/{id}" , produces= MediaType.IMAGE_JPEG_VALUE)
    public  byte[] getImageDroite2(@PathVariable("id") long id) throws Exception{
    	return service.getImageDroite2(id);
    }
    
    @GetMapping(path="imageGauche2/{id}" , produces= MediaType.IMAGE_JPEG_VALUE)
    public  byte[] getImageGauche2(@PathVariable("id") long id) throws Exception{
    	return service.getImageGauche2(id);
    }
    
    
    @GetMapping(path="imageDroite3/{id}" , produces= MediaType.IMAGE_JPEG_VALUE)
    public  byte[] getImageDroite3(@PathVariable("id") long id) throws Exception{
    	return service.getImageDroite3(id);
    }
    
    @GetMapping(path="imageGauche3/{id}" , produces= MediaType.IMAGE_JPEG_VALUE)
    public  byte[] getImageGauche3(@PathVariable("id") long id) throws Exception{
    	return service.getImageGauche3(id);
    }
    
    @GetMapping(path="imageDroite4/{id}" , produces= MediaType.IMAGE_JPEG_VALUE)
    public  byte[] getImageDroite4(@PathVariable("id") long id) throws Exception{
    	return service.getImageDroite4(id);
    }
    
    @GetMapping(path="imageGauche4/{id}" , produces= MediaType.IMAGE_JPEG_VALUE)
    public  byte[] getImageGauche4(@PathVariable("id") long id) throws Exception{
    	return service.getImageGauche4(id);
    }
    
    @GetMapping(path="imageDroite5/{id}" , produces= MediaType.IMAGE_JPEG_VALUE)
    public  byte[] getImageDroite5(@PathVariable("id") long id) throws Exception{
    	return service.getImageDroite5(id);
    }
    
    @GetMapping(path="imageGauche5/{id}" , produces= MediaType.IMAGE_JPEG_VALUE)
    public  byte[] getImageGauche5(@PathVariable("id") long id) throws Exception{
    	return service.getImageGauche5(id);
    }
	/*
	@PutMapping("/addimage3D/{idConsultation}")
	public String updateImage3D(@PathVariable("idConsultation") long idConsultation  , @RequestParam("image3") MultipartFile image3) throws IOException {
	updateImage3Droite(idConsultation , image3);
		return "Done pour image3 Droite !!!!" ; 
	}
	public void updateImage3Droite(long id , MultipartFile file) throws IOException {
		
		 Consultation  consultation = repository.findById(id).get();
		 StringBuilder fileNames=new StringBuilder();
		 Path fileNameAndPath=Paths.get(uploadDirectory,file.getOriginalFilename()+"");
		 fileNames.append(file);
		 System.out.println("bagraa"+fileNameAndPath);
		 Files.write(fileNameAndPath, file.getBytes());
		 consultation.setImage3_Droite(fileNameAndPath.toString());
		 
		repository.save(consultation);
	}
	@PutMapping("/addimage4D/{idConsultation}")
	public String updateImage4D(@PathVariable("idConsultation") long idConsultation  , @RequestParam("image4") MultipartFile image4 ) throws IOException {
		updateImage4Droite(idConsultation , image4);
		return "Done pour image4  Droite!!!!" ; 
	}
	public void updateImage4Droite(long id , MultipartFile file) throws IOException {
		
		 Consultation  consultation = repository.findById(id).get();
		 StringBuilder fileNames=new StringBuilder();
		 Path fileNameAndPath=Paths.get(uploadDirectory,file.getOriginalFilename()+"");
		 fileNames.append(file);
		 System.out.println("bagraa"+fileNameAndPath);
		 Files.write(fileNameAndPath, file.getBytes());
		 consultation.setImage4_Droite(fileNameAndPath.toString());
		 
		repository.save(consultation);
	}
	@PutMapping("/addimage5D/{idConsultation}")
	public String updateImage5D(@PathVariable("idConsultation") long idConsultation  , @RequestParam("image5") MultipartFile image5        ) throws IOException {

		updateImage5Droite(idConsultation, image5);
		return "Done pour image5 Droite  !!!!" ; 
	}
	
	public void updateImage5Droite(long id , MultipartFile file) throws IOException {
		
		 Consultation  consultation = repository.findById(id).get();
		 StringBuilder fileNames=new StringBuilder();
		 Path fileNameAndPath=Paths.get(uploadDirectory,file.getOriginalFilename()+"");
		 fileNames.append(file);
		 System.out.println("bagraa"+fileNameAndPath);
		 Files.write(fileNameAndPath, file.getBytes());
		 consultation.setImage5_Droite(fileNameAndPath.toString());
		 
		repository.save(consultation);
	}
	*/
	/***********Oeil Gauche *************/
	
	@PutMapping("/addimage1G/{idConsultation}")
	public String updateImage1G(@PathVariable("idConsultation") long idConsultation  , @RequestParam("image1") MultipartFile image1  ) throws IOException {
	updateImage1Gauche(idConsultation , image1);
		return "Done pour image1 Gauche !!!!" ; 
	}
	public void updateImage1Gauche(long id , MultipartFile file) throws IOException {
		
		 Consultation  consultation = repository.findById(id).get();
		 StringBuilder fileNames=new StringBuilder();
		 Path fileNameAndPath=Paths.get(uploadDirectory,file.getOriginalFilename()+"");
		 fileNames.append(file);
		 System.out.println("bagraa"+fileNameAndPath);
		 Files.write(fileNameAndPath, file.getBytes());
		 consultation.setImage1_Gauche(file.getOriginalFilename());
		 
		repository.save(consultation);
	}
	/********************************************************/
	@PutMapping("/addimage2G/{idConsultation}")
	public String updateImage2G(@PathVariable("idConsultation") long idConsultation  , @RequestParam("image2") MultipartFile image2 ) throws IOException {
	updateImage1Gauche(idConsultation , image2);
		return "Done pour image1 Gauche !!!!" ; 
	}
	public void updateImage2Gauche(long id , MultipartFile file) throws IOException {
		
		 Consultation  consultation = repository.findById(id).get();
		 StringBuilder fileNames=new StringBuilder();
		 Path fileNameAndPath=Paths.get(uploadDirectory,file.getOriginalFilename()+"");
		 fileNames.append(file);
		 System.out.println("bagraa"+fileNameAndPath);
		 Files.write(fileNameAndPath, file.getBytes());
		 consultation.setImage2_Gauche(file.getOriginalFilename());
		 
		repository.save(consultation);
	}
	/************************************************************/
	@PutMapping("/addimage3G/{idConsultation}")
	public String updateImage3G(@PathVariable("idConsultation") long idConsultation  , @RequestParam("image3") MultipartFile image3  ) throws IOException {
	updateImage1Gauche(idConsultation , image3);
		return "Done pour image1 Gauche !!!!" ; 
	}
	public void updateImage3Gauche(long id , MultipartFile file) throws IOException {
		
		 Consultation  consultation = repository.findById(id).get();
		 StringBuilder fileNames=new StringBuilder();
		 Path fileNameAndPath=Paths.get(uploadDirectory,file.getOriginalFilename()+"");
		 fileNames.append(file);
		 System.out.println("bagraa"+fileNameAndPath);
		 Files.write(fileNameAndPath, file.getBytes());
		 consultation.setImage3_Gauche(file.getOriginalFilename());
		 
		repository.save(consultation);
	}
	/***************************************************************/
	@PutMapping("/addimage4G/{idConsultation}")
	public String updateImage4G(@PathVariable("idConsultation") long idConsultation  , @RequestParam("image4") MultipartFile image4  ) throws IOException {
	updateImage1Gauche(idConsultation , image4);
		return "Done pour image1 Gauche !!!!" ; 
	}
	public void updateImage4Gauche(long id , MultipartFile file) throws IOException {
		
		 Consultation  consultation = repository.findById(id).get();
		 StringBuilder fileNames=new StringBuilder();
		 Path fileNameAndPath=Paths.get(uploadDirectory,file.getOriginalFilename()+"");
		 fileNames.append(file);
		 System.out.println("bagraa"+fileNameAndPath);
		 Files.write(fileNameAndPath, file.getBytes());
		 consultation.setImage4_Gauche(file.getOriginalFilename());
		 
		repository.save(consultation);
	}
	@PutMapping("/addimage5G/{idConsultation}")
	public String updateImage5G(@PathVariable("idConsultation") long idConsultation  , @RequestParam("image5") MultipartFile image5  ) throws IOException {
	updateImage1Gauche(idConsultation , image5);
		return "Done pour image1 Gauche !!!!" ; 
	}
	public void updateImage5Gauche(long id , MultipartFile file) throws IOException {
		
		 Consultation  consultation = repository.findById(id).get();
		 StringBuilder fileNames=new StringBuilder();
		 Path fileNameAndPath=Paths.get(uploadDirectory,file.getOriginalFilename()+"");
		 fileNames.append(file);
		 System.out.println("bagraa"+fileNameAndPath);
		 Files.write(fileNameAndPath, file.getBytes());
		 consultation.setImage5_Gauche(file.getOriginalFilename());
		 
		repository.save(consultation);
	}
	
	
	
	/*@PutMapping("/addimage2G/{idConsultation}")
	public String updateImage2G(@PathVariable("idConsultation") long idConsultation  , @RequestParam("image2") MultipartFile image2 ) throws IOException {
		updateImage2Gauche(idConsultation, image2);
		return "Done pour image2  Gauche!!!!" ; 
	}
	
	public void updateImage2Gauche(long id , MultipartFile file) throws IOException {
		
		 Consultation  consultation = repository.findById(id).get();
		 StringBuilder fileNames=new StringBuilder();
		 Path fileNameAndPath=Paths.get(uploadDirectory,file.getOriginalFilename()+"");
		 fileNames.append(file);
		 System.out.println("bagraa"+fileNameAndPath);
		 Files.write(fileNameAndPath, file.getBytes());
		 consultation.setImage2_Gauche(fileNameAndPath.toString());
		 
		repository.save(consultation);
	}
	
	@PutMapping("/addimage3G/{idConsultation}")
	public String updateImage3G(@PathVariable("idConsultation") long idConsultation  , @RequestParam("image3") MultipartFile image3 ) throws IOException {
		updateImage3Gauche(idConsultation, image3);
		return "Done pour image3 Gauche !!!!" ; 
	}
	
	public void updateImage3Gauche(long id , MultipartFile file) throws IOException {
		
		 Consultation  consultation = repository.findById(id).get();
		 StringBuilder fileNames=new StringBuilder();
		 Path fileNameAndPath=Paths.get(uploadDirectory,file.getOriginalFilename()+"");
		 fileNames.append(file);
		 System.out.println("bagraa"+fileNameAndPath);
		 Files.write(fileNameAndPath, file.getBytes());
		 consultation.setImage3_Gauche(fileNameAndPath.toString());
		 
		repository.save(consultation);
	}
	/*
	
	@PutMapping("/addimage4G/{idConsultation}")
	public String updateImage4G(@PathVariable("idConsultation") long idConsultation  , @RequestParam("image4") MultipartFile image4 ) throws IOException {
	updateImage4Gauche(idConsultation,  image4);
		return "Done pour image4  Gauche!!!!" ; 
	}
	
	public void updateImage4Gauche(long id , MultipartFile file) throws IOException {
		
		 Consultation  consultation = repository.findById(id).get();
		 StringBuilder fileNames=new StringBuilder();
		 Path fileNameAndPath=Paths.get(uploadDirectory,file.getOriginalFilename()+"");
		 fileNames.append(file);
		 System.out.println("bagraa"+fileNameAndPath);
		 Files.write(fileNameAndPath, file.getBytes());
		 consultation.setImage4_Gauche(fileNameAndPath.toString());
		 
		repository.save(consultation);
	}
	
	
	@PutMapping("/addimage5G/{idConsultation}")
	public String updateImage5G(@PathVariable("idConsultation") long idConsultation  , @RequestParam("image5") MultipartFile image5        ) throws IOException {
		updateImage5Gauche(idConsultation , image5);
		return "Done pour image5 Gauche  !!!!" ; 
	}
	public void updateImage5Gauche(long id , MultipartFile file) throws IOException {
		
		 Consultation  consultation = repository.findById(id).get();
		 StringBuilder fileNames=new StringBuilder();
		 Path fileNameAndPath=Paths.get(uploadDirectory,file.getOriginalFilename()+"");
		 fileNames.append(file);
		 System.out.println("bagraa"+fileNameAndPath);
		 Files.write(fileNameAndPath, file.getBytes());
		 consultation.setImage5_Gauche(fileNameAndPath.toString());
		 
		repository.save(consultation);
	}
	
	*/
	@DeleteMapping("/deleteConsult/{id}/{idConsultation}")
	public void deleteProduct(@PathVariable("idConsultation") long idConsultation){
		
	
		repository.deleteById(idConsultation);
	} 
	@GetMapping("/Consultations/{id}")
	public List<Consultation> getAllProducts(@PathVariable("id") @ModelAttribute("id") long id){
         //pr.findById(id);
	    return  repository.findByGeneraliste_id(id);

	} 

	@GetMapping("/Consultation/{id}/{idPatient}") // hehdy pour lien de consultation pour chaque patient
	public List<Consultation> getConsultationsByPatient (@PathVariable("id") long id ,@PathVariable ("idPatient") long idPatient){
	
    return repository.findByPatient_idAndGeneraliste_id(idPatient,id);		 
		
	}
	/*@GetMapping("/Consultations/{id}/{idConsultation}/{idPatient}")
	public ResponseEntity<byte[]> getImage() throws IOException {
		Consultation conster = repository.findById((long) 1).get();

		ClassPathResource imageFile = new ClassPathResource(conster.getImage1_Droite());

		byte[] imageBytes = StreamUtils.copyToByteArray(imageFile.getInputStream());

		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageBytes);
    }*/
	
	@GetMapping("/Consultation/{id}/{idConsultation}/{idPatient}")
	public Consultation getAllProductsbyid(@PathVariable("id") long id,@PathVariable("idConsultation") long idConsultation,@PathVariable("idPatient") long idPatient){
		Consultation conster = repository.findById(idConsultation).get();
		 if( conster.getImage1_Droite()== null) {
			  return conster;
		  }
		  else {
			    conster.setImage1_Droite((conster.getImage1_Droite()));}	
		 if( conster.getImage1_Gauche()== null) {
			  return conster;
		  }
		  else {
			    conster.setImage1_Gauche((conster.getImage1_Gauche()));
			    return conster;
				  
				
			}}
			    
	// input id de Auto detection dans consultation
	
	
	@PutMapping("/editAuto/{idGeneraliste}/{idConsult}/{idAutoDetection}")//(2) scénarion besh ysir directement maa ajout autoDetectio(cad k nenzlu al AI model 2 fonction bsh ysiru whda huni whda post mtaa autodetection)  
	public Consultation updateID(@PathVariable("idGeneraliste") long idGeneraliste,@PathVariable("idConsult") long idConsult,  @PathVariable("idAutoDetection") long idAutoDetection) {
	Consultation consultation = repository.findById(idConsult).get();
		AutoDetection autp =pr.findById(idAutoDetection).get();
           consultation.setAutoDetection(autp);
           LocalDateTime date = LocalDateTime.now() ; 
           LocalDate date1=LocalDate.now();
           consultation.setDemandeAvisTime(date);
           consultation.setDateDemandeAvis(date1);

	return	repository.save(consultation);
		 
	}
	
	/*@PutMapping("/demanderAvis/{idGeneraliste}/{idConsult}")
	public String udemanderAvisID(@PathVariable("idGeneraliste") long idGeneraliste,@PathVariable("idConsult") long idConsult) {
	Consultation consultation = repository.findById(idConsult).get();
		repository.save(consultation);
		return "Done pour changement ID  !!!!" ; 
	}*/
	// demande avis virtuelle 
	@PutMapping("/demanderAvisG/{idGeneraliste}/{idConsult}")
	public Consultation demanderAvisID(@PathVariable("idGeneraliste") long idGeneraliste,@PathVariable("idConsult") long idConsult) {
	Consultation consultation = repository.findById(idConsult).get();
           consultation.setDemandeAvisG(1);
return		repository.save(consultation);
	}
	@PutMapping("/demanderAvisD/{idGeneraliste}/{idConsult}")
	public Consultation demanderAvisId(@PathVariable("idGeneraliste") long idGeneraliste,@PathVariable("idConsult") long idConsult) {
	Consultation consultation = repository.findById(idConsult).get();
           consultation.setDemandeAvisD(1);
		return repository.save(consultation);
}
	
	 @GetMapping("/consultationParMonth")
     public int getConsultation(@RequestParam("month") Integer month) {
	     List<Consultation> resultat = new ArrayList<Consultation>() ; 
		 List<Consultation> liste =  repository.findAll() ; 
		 for(Consultation consult :liste) {
				Integer yearInscription = new Integer(consult.getDateConsult().getYear());

		Integer monthInscription = new Integer(consult.getDateConsult().getMonthValue());
			 if(month.equals(monthInscription)&& (yearInscription.equals(LocalDate.now().getYear()))) {
				 resultat.add(consult) ; 
			 }
			 
			 
		 }
		 int nbrConsult = resultat.size() ; 
		 
	  return nbrConsult ;
	  }

	 @GetMapping("/getAllPatho")
		public int getttw (@RequestParam("month") Integer month){
		 List<Consultation> liste =  repository.findAll() ; 

			List<Consultation> liste1 = new ArrayList<Consultation>();// Empty
			
			for(Consultation consult :liste) {
				Integer yearInscription = new Integer(consult.getDateConsult().getYear());

				Integer monthInscription = new Integer(consult.getDateConsult().getMonthValue());

				if(consult.getAutoDetection()!=null) {
				if(((consult.getAutoDetection().getMaladieDroite()!=null) && (consult.getAutoDetection().getMaladieDroite()!="saine") )||(consult.getAutoDetection().getMaladieGauche()!=null) &&( (consult.getAutoDetection().getMaladieGauche()!="saine")) ) {
					 if(month.equals(monthInscription) && (yearInscription.equals(LocalDate.now().getYear()))) {
					liste1.add(consult)  ; 
				}
			
				}
				}
			}
			
			 int nbr =liste1.size();

				return nbr ; 
			
	 }
	
	 @GetMapping("/getAllavecAvis")
		public int getAvis (){
		 List<Consultation> liste =  repository.findAll() ; 
			List<Consultation> liste1 = new ArrayList<Consultation>();// Empty
			
			for(Consultation consult :liste) {
			
				if(consult.getAutoDetection()!=null) {

				if(consult.getAutoDetection().getAvisExpert()!=null ) {
				//if(((consult.getAutoDetection().getAvisExpert().getMaladieDroite()!=null) || (consult.getAutoDetection().getAvisExpert().getMaladieGauche()!=null) )) {
					liste1.add(consult)  ; 
				}
			
				}
				
			
			
				}	
	 
	
			int nbr =liste1.size();

			return nbr ; 
	 
	 } 
	 
	 @GetMapping("/getAllsansAvis")
		public int getsansAvis (){
		 List<Consultation> liste =  repository.findAll() ; 
			List<Consultation> liste1 = new ArrayList<Consultation>();// Empty
			
			for(Consultation consult :liste) {
			
				if(consult.getAutoDetection()!=null) {

				if(consult.getAutoDetection().getAvisExpert()==null ) {
				//if(((consult.getAutoDetection().getAvisExpert().getMaladieDroite()!=null) || (consult.getAutoDetection().getAvisExpert().getMaladieGauche()!=null) )) {
					liste1.add(consult)  ; 
				}
			
				}
				
			
			
				}	
	 
	
			int nbr =liste1.size();

			return nbr ; 
	 
	 } 
	 
	 
	 

@GetMapping("/getAIEgaleAvis")
public int getAvisEgaleAI (@RequestParam("month") Integer month){
 List<Consultation> liste =  repository.findAll() ; 
 
 List<Consultation> liste1 = new ArrayList<Consultation>();// Empty


	for(Consultation consult :liste) {
		Integer monthInscription = new Integer(consult.getDateConsult().getMonthValue());
		Integer yearInscription = new Integer(consult.getDateConsult().getYear());

		if(consult.getAutoDetection()!= null) {
		if(consult.getAutoDetection().getAvisExpert()!= null) {
			if(consult.getAutoDetection().getMaladieDroite()!= null ||consult.getAutoDetection().getGraviteDroite()!= 0) {

		if((consult.getAutoDetection().getAvisExpert().getMaladieDroite().equals(consult.getAutoDetection().getMaladieDroite()))&&(consult.getAutoDetection().getAvisExpert().getGraviteDroite()==consult.getAutoDetection().getGraviteDroite())) {
			//liste1.add(consult)  ; 
			 if(month.equals(monthInscription)&& (yearInscription.equals(LocalDate.now().getYear()))) {
				 liste1.add(consult) ; 
			 }
		
		if((consult.getAutoDetection().getAvisExpert().getMaladieGauche().equals(consult.getAutoDetection().getMaladieGauche())||(consult.getAutoDetection().getAvisExpert().getGraviteGauche()==consult.getAutoDetection().getGraviteGauche()))) {
		if(!(liste1.contains(consult))){
			if(month.equals(monthInscription)&& (yearInscription.equals(LocalDate.now().getYear()))){
				 liste1.add(consult) ; 
			 } 
		}
		}
			}
		}
	
		}	
}}

	int nbr =liste1.size();

	return nbr ; 
}
@GetMapping("/getAIdiffAvis")

public int getAvisDiffAI (@RequestParam("month") Integer month){
 List<Consultation> liste =  repository.findAll() ; 
 
 List<Consultation> liste1 = new ArrayList<Consultation>();// Empty
	
	for(Consultation consult :liste) {
		Integer monthInscription = new Integer(consult.getDateConsult().getMonthValue());
		Integer yearInscription = new Integer(consult.getDateConsult().getYear());
		if(consult.getAutoDetection()!= null) {
		if(consult.getAutoDetection().getAvisExpert()!= null) {
		if(consult.getAutoDetection().getMaladieDroite()!= null ||consult.getAutoDetection().getGraviteDroite()!= 0) {
		if((consult.getAutoDetection().getAvisExpert().getMaladieDroite()!=consult.getAutoDetection().getMaladieDroite())||(consult.getAutoDetection().getAvisExpert().getGraviteDroite()!=consult.getAutoDetection().getGraviteDroite())) {
			if(month.equals(monthInscription) && (yearInscription.equals(LocalDate.now().getYear()))) {
				 liste1.add(consult) ; 
			 
		}
		if((consult.getAutoDetection().getAvisExpert().getMaladieGauche()!=consult.getAutoDetection().getMaladieGauche())&&(consult.getAutoDetection().getAvisExpert().getGraviteGauche()!=consult.getAutoDetection().getGraviteGauche())) {
		if(!(liste1.contains(consult))){
			if(month.equals(monthInscription)&& (yearInscription.equals(LocalDate.now().getYear()))) {
				 liste1.add(consult) ; 
			 } 
		}
		}
		
		}
	
		}	
		}}
}
	int nbr =liste1.size();

	return nbr ; 
}

}	 
	

	
