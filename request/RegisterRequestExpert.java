package com.projet.BackendPfe.request;

import java.time.LocalDate;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.projet.BackendPfe.model.AdminMedicalManager;

public class RegisterRequestExpert {
	private String nomPrenom;
	public String getNomPrenom() {
		return nomPrenom;
	}
	public void setNomPrenom(String nomPrenom) {
		this.nomPrenom = nomPrenom;
	}
	private String gender ;
	private String  image ; 
	private long telephone ; 
	@NotBlank
    private String username;
    @NotBlank
    private String reserve;
   public String getReserve() {
		return reserve;
	}
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
	// @Size(min=0 , max = 100)
    @Email
    private String email;
  //  @NotBlank
   // private String role;
    @NotBlank
    //@Size( max = 10)
    private String password;
    private LocalDate date_inscription ; 
    private String role ; 
  	public LocalDate getDate_inscription() {
  		return date_inscription;
  	}
    public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@ManyToOne
	private AdminMedicalManager admin;
	public AdminMedicalManager getAdmin() {
		return admin;
	}
	public void setAdmin(AdminMedicalManager admin) {
		this.admin = admin;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
/*	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}*/
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	} 
	public String  getImage() {
		return image;
	} 
	public long getTelephone() {
		return telephone;
	} 
	public void setGender(String gender) {
		this.gender = gender;
	}public void setImage(String image) {
		this.image = image;
	}public void setTelephone(long telephone) {
		this.telephone = telephone;
	}
}


