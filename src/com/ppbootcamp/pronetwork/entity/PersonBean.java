package com.ppbootcamp.pronetwork.entity;

import java.util.ArrayList;

public class PersonBean {
	int id;
	
	// Personal Details
	String name;
	String mailid;
	String password;
	
	//Education Details
    ArrayList<EmploymentBean> employmentBean;

	// Professional Details
	ArrayList<EducationBean> educationBean;
	
	String skills;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMailid() {
		return mailid;
	}
	public void setMailid(String mailid) {
		this.mailid = mailid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList<EmploymentBean> getEmploymentBean() {
		return employmentBean;
	}
	public void setEmploymentBean(ArrayList<EmploymentBean> employmentBean) {
		this.employmentBean = employmentBean;
	}
	
	public ArrayList<EducationBean> getEducationBean() {
		return educationBean;
	}

	public void setEducationBean(ArrayList<EducationBean> educationBean) {
		this.educationBean = educationBean;
	}

	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
}
