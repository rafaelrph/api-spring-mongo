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
	 * Method for list all the objects
	 * @return people's list
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<People> listAll(){
		return peopleRepository.findAll();
	}

	/**
	 * Method for create/save new object
	 * @param people
	 * @return people created
	 */
	@RequestMapping(method = RequestMethod.POST)
	public People create(@Valid @RequestBody People people){
		return peopleRepository.save(people);
	}
	
	/**
	 * Method for find an object by id
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
	 * Method for update an object
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
	 * Method for delete an object
	 * @param id
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") String id){
		peopleRepository.delete(id);
	}
	
}
