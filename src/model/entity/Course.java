package model.entity;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import java.util.Date;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Course {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long id;
	@Persistent private String name;
	@Persistent private Long idTeacher;
	@Persistent private String emailTeacher;
	@Persistent private Long[]idStudents;
	@Persistent private boolean status;
	@Persistent private Date created;
	
	public Course(String name, Long idTeacher, String emailTeacher) {
		
		this.name = name;
		this.idTeacher = idTeacher;
		this.emailTeacher = emailTeacher;
		this.idStudents=new Long[20];
		this.status = true;
		this.created = new Date();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getIdTeacher() {
		return idTeacher;
	}

	public void setIdTeacher(Long idTeacher) {
		this.idTeacher = idTeacher;
	}

	public String getEmailTeacher() {
		return emailTeacher;
	}

	public void setEmailTeacher(String nameTeacher) {
		this.emailTeacher = nameTeacher;
	}

	public Long[] getIdStudents() {
		return idStudents;
	}

	public boolean addIdStudents(Long idStudent) {
		for (int i = 0; i < idStudents.length; i++) {
			if(idStudents[i]==null){
				idStudents[i]=idStudent;
				return true;
			}		
		}
		return false;
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
