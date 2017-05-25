package com.example.demo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.People;

/**
 * Repository of People
 * This is an interface which extends MongoRepository for persistence of data using MongoDB
 * Example: MongoRepository<Model.class, TypeOfIdAttribute>
 * 
 * @author rafaelrph
 *
 */
public interface PeopleRepository extends MongoRepository<People, String> {
	
}
