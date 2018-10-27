package com.qa.mongo_test_zwei.customer.db.seeder;

import com.qa.mongo_test_zwei.customer.models.Customer;
import com.qa.mongo_test_zwei.customer.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;


@Component
public class DbSeeder implements CommandLineRunner {

    @Autowired
    private CustomerRepo customerRepo;

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