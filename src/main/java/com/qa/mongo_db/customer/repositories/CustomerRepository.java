package com.qa.mongo_db.customer.repositories;

import com.qa.mongo_db.customer.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    Customer findByFirstName(String firstname);

    List<Customer> findByLastName(String lastname);

}