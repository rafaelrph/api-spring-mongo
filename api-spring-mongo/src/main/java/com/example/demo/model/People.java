package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Model of People
 * 
 * @author rafaelrph
 *
 */
@Document(collection = "people")
public class People {

	/**
	 * Id attribute
	 */
	@Id
	private String id;
	
	/**
	 * Name and email attributes
	 */
	private String name, email;
	
	/**
	 * Constructor without fields
	 */
	public People(){
		
	}

	/**
	 * Constructor with fields
	 * @param name
	 * @param email
	 */
	public People(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Method toString
	 */
	@Override
	public String toString(){
		return String.format("[id=%s, title='%s', completed='%s']", id, name, email);
	}
		
}
