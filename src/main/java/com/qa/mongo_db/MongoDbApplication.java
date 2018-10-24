package com.qa.mongo_db;

import com.qa.mongo_db.customer.domain.Customer;
import com.qa.mongo_db.customer.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@EnableWebMvc
public class MongoDbApplication implements CommandLineRunner {

    @Autowired
    private CustomerRepo customerRepo;

    public static void main(String[] args) throws Exception{
        SpringApplication.run(MongoDbApplication.class, args);
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
