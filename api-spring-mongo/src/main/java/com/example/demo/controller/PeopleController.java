package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.PeopleRepository;
import com.example.demo.model.People;


/**
 * Controller of People for controlling the API requests
 * 
 * @author rafaelrph
 *
 */
@RestController
@RequestMapping("/people")
public class PeopleController {

	/**
	 * Resository Injection
	 */
	@Autowired
	PeopleRepository peopleRepository;
	
	/**
	 * List all the objects
	 * @return people's list
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<People> listAll(){
		return peopleRepository.findAll();
	}

	/**
	 * Create/save new object
	 * @param people
	 * @return people created
	 */
	@RequestMapping(method = RequestMethod.POST)
	public People create(@Valid @RequestBody People people){
		return peopleRepository.save(people);
	}
	
	/**
	 * Find an object by id
	 * @param id
	 * @return people found by id
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<People> listById(@PathVariable("id") String id) {
		People people = peopleRepository.findOne(id);
		if(people == null) {
			return new ResponseEntity<People>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<People>(people, HttpStatus.OK);
		}
	}
	
	/**
	 * Update an object
	 * @param people
	 * @param id
	 * @return people updated
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<People> update(@Valid @RequestBody People people, @PathVariable("id") String id) {
		People peopleData = peopleRepository.findOne(id);
		if(peopleData == null) {
			return new ResponseEntity<People>(HttpStatus.NOT_FOUND);
		}
		peopleData.setName(people.getName());
		peopleData.setEmail(people.getEmail());
		People peopleUpdated = peopleRepository.save(peopleData);
		return new ResponseEntity<People>(peopleUpdated, HttpStatus.OK);
	}
	
	/**
	 * Delete an object
	 * @param id
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") String id){
		peopleRepository.delete(id);
	}
	
	/**
	 * Insert multiple 
	 * @param quantity
	 * @return people found by id
	 */
	@RequestMapping(value = "{quantity}", method = RequestMethod.POST)
	public ResponseEntity<Object> insertMultiple(@PathVariable("quantity") Integer quantity) {
		long start = System.currentTimeMillis();
		for(int i = 1; i < quantity; i++) {
			People people = new People("People " + i, "mail" + i + "@mail.com");
			peopleRepository.save(people);
		}
		
		long elapsed = System.currentTimeMillis() - start;
		
		return new ResponseEntity<Object>("DONE! Inserted " + quantity + " - In " + elapsed + " milliseconds", HttpStatus.OK);
		
	}
	
}
