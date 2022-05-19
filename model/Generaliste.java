package com.projet.BackendPfe.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.stereotype.Component;


@Entity
@DiscriminatorValue(value="generaliste")

public class Generaliste extends User {

	private String gender ;
	private long telephone;
	private String specialite;
	public String getSpecialite() {
		return specialite;
	}
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	@OneToMany(targetEntity=Consultation.class, mappedBy = "generaliste",fetch=FetchType.LAZY)
	
	private List<Patient>liste=new ArrayList<Patient>();
	  
	@OneToMany(targetEntity=Consultation.class, mappedBy = "generaliste",fetch=FetchType.LAZY)
	private List<Consultation>liste1=new ArrayList<Consultation>();
	  
	 public Generaliste(String username, String email,String nomPrenom ,String password, String gender, long telephone , String image , LocalDate date_inscription  , String role ,String specialite ,String reserve) {
			super(username,email,nomPrenom,password,image , date_inscription , role,reserve );
			this.gender=gender;
			this.telephone=telephone;
			this.specialite=specialite;
			this.reserve=reserve;
		
		} 
	 public Generaliste(String image) {
			this.image=image ;
		}
	 public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setImage(){
		 super.setImage(super.getImage());
	}
	public String getImage(){
		return super.getImage();
	}
	public long getTelephone() {
		return telephone;
	}

	public void setTelephone(long telephone) {
		this.telephone = telephone;
	}

	public String getUsername(){
		return super.getUsername();
	}
	

	public void setUsername(){
		 super.setUsername(super.getUsername());
		 
	}
	public String getEmail(){
		return super.getEmail();
	}
	

	public void setEmail(){
		 super.setEmail(super.getEmail());
	}
	
	
	public String getPassword(){
		return super.getPassword();
	}
	

	public void setPassword(){
		 super.setPassword(super.getPassword());
	}
	public LocalDate getDate_inscription() {
		return date_inscription;
	}

public Generaliste() {
	super();
}

@Override
public String toString() {
	return "Generaliste [gender=" + gender + ", telephone=" + telephone + ", id=" + id + ", username=" + username
			+ ", email=" + email + ", password=" + password + ", image=" + image + "]";
}

}
