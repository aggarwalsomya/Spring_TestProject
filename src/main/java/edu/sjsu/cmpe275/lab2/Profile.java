package edu.sjsu.cmpe275.lab2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "profile")
public class Profile {

	@Id
	private int id;

	private String firstname;
	private String lastname;
	private String email;
	private String address;
	private String organization;
	private String about_myself;

	public Profile() {

	}

	/* getters & setters */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return firstname;
	}

	public void setFname(String s) {
		this.firstname = s;
	}

	public String getLname() {
		return lastname;
	}

	public void setLname(String s) {
		this.lastname = s;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String s) {
		this.email = s;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String s) {
		this.address = s;
	}

	public String getOrg() {
		return organization;
	}

	public void setOrg(String s) {
		this.organization = s;
	}

	public String getAbout() {
		return about_myself;
	}

	public void setAbout(String s) {
		this.about_myself = s;
	}

}