package com.qa.mongo_test_zwei.customer.repositories;

import com.qa.mongo_test_zwei.customer.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerRepo extends MongoRepository<Customer, String> {

    Customer findByFirstName(String firstname);

    List<Customer> findByLastName(String lastname);
}
