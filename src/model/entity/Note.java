package model.entity;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Note {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long id;
	@Persistent private Long idCourse;
	@Persistent private String nameCourse;
	@Persistent private Long idStudent;
	@Persistent private Double noteValue;
	@Persistent private Date created;
	
	public Note(Long idCourse, String nameCourse, Long idStudent, Double noteValue) {
		
		this.idCourse = idCourse;
		this.nameCourse = nameCourse;
		this.idStudent = idStudent;
		this.noteValue = noteValue;
		this.created=new Date();
	}

	public Long getId() {
		return id;
	}

	public Long getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(Long idCourse) {
		this.idCourse = idCourse;
	}

	public String getNameCourse() {
		return nameCourse;
	}

	public void setNameCourse(String nameCourse) {
		this.nameCourse = nameCourse;
	}

	public Long getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(Long idstudent) {
		this.idStudent = idstudent;
	}

	public Double getNoteValue() {
		return noteValue;
	}

	public void setNoteValue(Double noteValue) {
		this.noteValue = noteValue;
	}

	public Date getCreated() {
		return created;
	}

	
	
}
