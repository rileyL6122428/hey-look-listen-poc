package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Customer;

public interface CustomerRepo extends MongoRepository<Customer, String> {
	
	public Customer findByFirstName(String firstName);
	public List<Customer> findByLastName(String lastName);
	
}
