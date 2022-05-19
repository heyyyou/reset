package com.projet.BackendPfe.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value="Expert")

public class Expert extends User {
@OneToMany(targetEntity=AvisExpert.class, mappedBy = "expert",fetch=FetchType.LAZY)
	private List<AvisExpert>liste=new ArrayList<AvisExpert>();
	protected String gender ;
	protected long telephone ;
	@ManyToOne
	private AdminMedicalManager admin;
	
	public Expert( String username, String email,String nomPrenom ,String password, 
			String gender, long telephone , String image, LocalDate date_inscription , String role ,String reserve  ) {
		super(username,email,nomPrenom,password,image , date_inscription , role,reserve );
		this.gender=gender;
		this.telephone=telephone;
		
	
	
	}
	public Expert( String username, String email,String nomPrenom ,String password, 
			String gender, long telephone , String image , AdminMedicalManager admin , LocalDate date_inscription , String role,String reserve   ) {
		super(username,email,nomPrenom,password,image , date_inscription ,  role,reserve );
		this.gender=gender;
		this.telephone=telephone;
		this.admin= admin ; 
	
	}

	public Expert(String image) {
		this.image=image ;
	}
	public Expert() {
		super();
	}
	public LocalDate getDate_inscription() {
		return date_inscription;
	}

	 public String getGender() {
			return gender;
		}
		
		public void setGender(String gender) {
			this.gender = gender;
		}

	public  String getImage() {
		return image ; 
	}
	public  void setImage(String image) {
		this.image = image;
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

		

	

@Override
	public String toString() {
		return "Expert [gender=" + gender + ", telephone=" + telephone + ", id=" + id + ", username=" + username
				+ ", email=" + email + ", password=" + password + ", image=" + image + "]";
	}
public AdminMedicalManager getAdmin() {
	return admin;
}
public void setAdmin(AdminMedicalManager admin) {
	this.admin = admin;
}
}
