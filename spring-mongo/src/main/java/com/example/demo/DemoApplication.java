package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepo;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	
	@Autowired
	private CustomerRepo customerRepo;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		customerRepo.deleteAll();
		
		customerRepo.save(new Customer("Todd", "leHorse"));
		customerRepo.save(new Customer("Villiam", "leHorse"));
		
		System.out.println("Customers found with findAll()");
		System.out.println("------------------------------");
		
		for (Customer customer : customerRepo.findAll()) {
			System.out.println(customer);
		}
		
		System.out.println();
		
		// FETCH AN INDIVIDUAL THEORM
		System.out.println("Customer found by firstname 'Villiam':");
		System.out.println(customerRepo.findByFirstName("Villiam"));
		System.out.println();
		
		System.out.println("Customers found by");
		for (Customer customer : customerRepo.findByLastName("leHorse")) {
			System.out.println(customer);
		}
	}

}
