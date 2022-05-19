package com.projet.BackendPfe.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;



@Entity
public class Consultation {
protected LocalDate dateDemandeAvis ;  
protected LocalDateTime DemandeAvisTime;
protected String decalageHoraire;
protected int rating;

public Consultation(int rating) {
	super();
	this.rating = rating;
}

public int getRating() {
	return rating;
}

public void setRating(int rating) {
	this.rating = rating;
}

public String getDecalageHoraire() {
	return decalageHoraire;
}

public void setDecalageHoraire(String decalageHoraire) {
	this.decalageHoraire = decalageHoraire;
}

public LocalDateTime getDemandeAvisTime() {
	return DemandeAvisTime;
}

public void setDemandeAvisTime(LocalDateTime demandeAvisTime) {
	DemandeAvisTime = demandeAvisTime;
}

public LocalDate getDateDemandeAvis() {
		return dateDemandeAvis;
	}

	public void setDateDemandeAvis(LocalDate dateDemandeAvis) {
		this.dateDemandeAvis = dateDemandeAvis;
	}


private int demandeAvisD;
 private int demandeAvisG;
 private long sizeConsultaion ; 
 
	public Consultation( int demandeAvisD,int demandeAvisG,LocalDate dateConsult, Generaliste generaliste, Patient patient,
			AutoDetection autoDetection, String  image1_Droite, String image1_Gauche,LocalDateTime DemandeAvisTime,String DecalageHoraire , long sizeConsultation) {
		super();
		this.dateConsult = dateConsult;
		this.generaliste = generaliste;
		this.patient = patient;
		this.autoDetection = autoDetection;
		this.image1_Droite = image1_Droite;
		this.demandeAvisD=demandeAvisD;
		this.demandeAvisG=demandeAvisG;
		this.DemandeAvisTime=DemandeAvisTime;
		this.decalageHoraire=DecalageHoraire;
		this.sizeConsultaion =sizeConsultation ; 
	}

	public int getDemandeAvisD() {
		return demandeAvisD;
	}

	public void setDemandeAvisD(int demandeAvisD) {
		this.demandeAvisD = demandeAvisD;
	}

	public int getDemandeAvisG() {
		return demandeAvisG;
	}

	public void setDemandeAvisG(int demandeAvisG) {
		this.demandeAvisG = demandeAvisG;
	}

	public Generaliste getGeneraliste() {
		return generaliste;
	}


private LocalDate dateConsult;
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	public Consultation(Generaliste generaliste, AutoDetection autoDetection) {
		super();
		this.generaliste = generaliste;
		this.autoDetection = autoDetection;
	}
	@ManyToOne()
	protected Generaliste generaliste;
	public AutoDetection getAutoDetection() {
		return autoDetection;
	}
	public void setAutoDetection(AutoDetection autoDetection) {
		this.autoDetection = autoDetection;
	}

	@ManyToOne()
	
	protected Patient patient;
	@OneToOne
	
	protected AutoDetection autoDetection;
	
	@Column(name = "image1Droite")
	protected String image1_Droite;
	@Column(name = "image2Droite")
	protected String image2_Droite;
	@Column(name = "image3Droite")
	protected String image3_Droite;
	@Column(name = "image4Droite")
	protected String image4_Droite;
	@Column(name = "image5Droite")
	protected String  image5_Droite;
	@Column(name = "image1Gauche")
	protected String image1_Gauche;
	@Column(name = "image2Gauche")
	protected String image2_Gauche;
    @Column(name = "image3Gauche")
	protected String image3_Gauche;
	@Column(name = "image4Gauche")
	protected String image4_Gauche;
	@Column(name = "image5Gauche")
	protected String image5_Gauche;
	 
	public String getImage1_Droite() {
		return image1_Droite;
	}
	public void setImage1_Droite(String image1_Droite) {
		this.image1_Droite = image1_Droite;
	}
	
	public String getImage1_Gauche() {
		return image1_Gauche;
	}
	public void setImage1_Gauche(String image1_Gauche) {
		this.image1_Gauche = image1_Gauche;
	}
	

	
public Consultation() {}


public Consultation(Generaliste generaliste,Patient patient,LocalDate dateConsult,
		String image1_Gauche ,String image1_Droite  ,String image2_Gauche ,String image2_Droite ,
		String image3_Gauche ,String image3_Droite ,String image4_Gauche ,String image4_Droite,
		String image5_Gauche ,String image5_Droite ,LocalDateTime DemandeAvisTime , String decalageHoraire  , long sizeConsultation) {
	super();
	this.generaliste = generaliste;
	this.patient = patient;

	this.image1_Gauche=image1_Gauche ; 
	
	this.image1_Droite=image1_Droite ; 
    this.image2_Gauche=image1_Gauche ; 
	
	this.image2_Droite=image1_Droite ; 
    this.image3_Gauche=image1_Gauche ; 
	
	this.image3_Droite=image1_Droite ; 
   this.image4_Gauche=image1_Gauche ; 
	
	this.image4_Droite=image1_Droite ; 
   this.image5_Gauche=image1_Gauche ; 
	
	this.image5_Droite=image1_Droite ; 
	this. dateConsult=dateConsult;
	this.DemandeAvisTime=DemandeAvisTime;
	this.decalageHoraire=decalageHoraire;
	this.sizeConsultaion =sizeConsultation ;
}



	public LocalDate getDateConsult() {
	return dateConsult;
}

public void setDateConsult(LocalDate dateConsult) {
	this.dateConsult = dateConsult;
}

	public void setGeneraliste(Generaliste generaliste) {
		this.generaliste = generaliste;
	}

	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getImage2_Droite() {
		return image2_Droite;
	}

	public void setImage2_Droite(String image2_Droite) {
		this.image2_Droite = image2_Droite;
	}

	public String getImage3_Droite() {
		return image3_Droite;
	}

	public void setImage3_Droite(String image3_Droite) {
		this.image3_Droite = image3_Droite;
	}

	public String getImage4_Droite() {
		return image4_Droite;
	}

	public void setImage4_Droite(String image4_Droite) {
		this.image4_Droite = image4_Droite;
	}

	public String getImage5_Droite() {
		return image5_Droite;
	}

	public void setImage5_Droite(String image5_Droite) {
		this.image5_Droite = image5_Droite;
	}

	public String getImage2_Gauche() {
		return image2_Gauche;
	}

	public void setImage2_Gauche(String image2_Gauche) {
		this.image2_Gauche = image2_Gauche;
	}

	public String getImage3_Gauche() {
		return image3_Gauche;
	}

	public void setImage3_Gauche(String image3_Gauche) {
		this.image3_Gauche = image3_Gauche;
	}

	public String getImage4_Gauche() {
		return image4_Gauche;
	}

	public void setImage4_Gauche(String image4_Gauche) {
		this.image4_Gauche = image4_Gauche;
	}

	public String getImage5_Gauche() {
		return image5_Gauche;
	}

	public void setImage5_Gauche(String image5_Gauche) {
		this.image5_Gauche = image5_Gauche;
	}

	public long getSizeConsultaion() {
		return sizeConsultaion;
	}

	public void setSizeConsultaion(long sizeConsultaion) {
		this.sizeConsultaion = sizeConsultaion;
	}

	/******* ******/
	public Consultation( long sizeConsultaion, LocalDate dateConsult,
			Generaliste generaliste, Patient patient, String image1_Droite, String image2_Droite, String image3_Droite,
			String image4_Droite, String image5_Droite, String image1_Gauche, String image2_Gauche,
			String image3_Gauche, String image4_Gauche, String image5_Gauche) {
		super();
		this.sizeConsultaion = sizeConsultaion;
		this.dateConsult = dateConsult;
		this.generaliste = generaliste;
		this.patient = patient;
		this.image1_Droite = image1_Droite;
		this.image2_Droite = image2_Droite;
		this.image3_Droite = image3_Droite;
		this.image4_Droite = image4_Droite;
		this.image5_Droite = image5_Droite;
		this.image1_Gauche = image1_Gauche;
		this.image2_Gauche = image2_Gauche;
		this.image3_Gauche = image3_Gauche;
		this.image4_Gauche = image4_Gauche;
		this.image5_Gauche = image5_Gauche;
	}

	
	
}