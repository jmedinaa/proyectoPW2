package model.entity;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Access {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long id;
	@Persistent private Long idRole;
	@Persistent private String nameRole;
	@Persistent private Long idResourse;
	@Persistent private String urlResourse;
	@Persistent private boolean status;
	@Persistent private Date created;
	
	public Access(Long idRole,String nameRole, Long idResourse,String urlResourse) {
		super();
		this.idRole = idRole;
		this.nameRole= nameRole;
		this.idResourse = idResourse;
		this.urlResourse= urlResourse;
		this.status=true;
		this.created=new Date();
	}

	public Long getId() {
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

	public Long getIdResourse() {
		return idResourse;
	}

	public void setIdResourse(Long idResourse) {
		this.idResourse = idResourse;
	}
	
	public String getUrlResource() {
		return urlResourse;
	}

	public void setUrlResource(String urlResource) {
		this.urlResourse=urlResource;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getCreated() {
		return created;
	}

	
}
