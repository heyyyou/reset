package com.projet.BackendPfe.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "Utilisateur",
uniqueConstraints = { 
		@UniqueConstraint(columnNames = "username"
				+ ""),
		@UniqueConstraint(columnNames = "email") 
	})
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="roles")
public abstract class User {
	
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	protected long id;
	  @NotBlank
		@Size(max = 100)
	  protected String username;
	  @NotBlank
	  @Size(max = 100)
	  @Email
	  protected String email;
	  protected String password;
	  protected String reserve;
	  public String getReserve() {
		return reserve;
	}
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
	@Column(name = "image", length = 1000000)
	  protected String image;

	  protected  LocalDate date_inscription  ; 
	   protected String role ; 
	
	 
protected String nomPrenom;
	public String getNomPrenom() {
	return nomPrenom;
}
public void setNomPrenom(String nomPrenom) {
	this.nomPrenom = nomPrenom;
}
	public LocalDate getDate_inscription() {
		return date_inscription;
	}
	public void setDate_inscription(LocalDate date_inscription) {
		this.date_inscription = date_inscription;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public User(String username, String email,String nomPrenom ,String password  , String image  , LocalDate date_inscription  , String role,String reserve) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.image = image;
		this.date_inscription=date_inscription ; 
		this.role = role  ;
		this.nomPrenom=nomPrenom ;
		this.reserve=reserve;}	
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public User() {
		super();
		
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", image="
				+ image + ", date_inscription=" + date_inscription + ", role=" + role + ", nomPrenom=" + nomPrenom
				+ "]";
	}

}