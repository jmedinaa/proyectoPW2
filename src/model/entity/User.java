package model.entity;


import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import java.util.Date;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class User {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long id;
	@Persistent private Long idRole;
	@Persistent private String nameRole;
	@Persistent private String email;
	@Persistent private Date birth;
	@Persistent private boolean gender;
	@Persistent private boolean status;
	@Persistent private Date created;
	
	public User(Long idRole,String nameRole, String email, Date birth, boolean gender) {
		super();
		this.idRole = idRole;
		this.nameRole=nameRole;
		this.email = email;
		this.birth = birth;
		this.gender = gender;
		this.status = true;
		this.created = new Date();
	}
	
	public Long getId(){
		return id;
	}

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}
	
	public String getNameRole() {
		return nameRole;
	}

	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public Date getCreated(){
		return this.created;
	}
	

}
