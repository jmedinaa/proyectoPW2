package model.entity;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Report {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long id;
	@Persistent private String issue;
	@Persistent private String nameSender;
	@Persistent private String description;
	@Persistent private Date created;
	public Report(String issue, String nameSender, String description) {
		
		this.issue = issue;
		this.nameSender = nameSender;
		this.description = description;
		this.created= new Date();
	}
	public Long getId() {
		return id;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public String getNameSender() {
		return nameSender;
	}
	public void setNameSender(String nameSender) {
		this.nameSender = nameSender;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreated() {
		return created;
	}
	
	
}
