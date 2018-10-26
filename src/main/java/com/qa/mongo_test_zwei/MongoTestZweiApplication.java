package com.qa.mongo_test_zwei;

import com.qa.mongo_test_zwei.customer.models.Customer;
import com.qa.mongo_test_zwei.customer.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MongoTestZweiApplication implements CommandLineRunner {

    @Autowired
    private CustomerRepo customerRepo;

    public static void main(String[] args) {
        SpringApplication.run(MongoTestZweiApplication.class, args);
    }

    @Override
    public void run(String... args) {

        customerRepo.deleteAll();

        // save a couple of customers
        customerRepo.save(new Customer("Alice", "Smith"));
        customerRepo.save(new Customer("Bob", "Smith"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : customerRepo.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(customerRepo.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Customer customer : customerRepo.findByLastName("Smith")) {
            System.out.println(customer);
        }
    }
}
