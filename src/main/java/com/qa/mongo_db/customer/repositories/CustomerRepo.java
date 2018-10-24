package com.qa.mongo_db.customer.repositories;

import com.qa.mongo_db.customer.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerRepo extends MongoRepository<Customer, String> {

    public Customer findByFirstName(String firstname);

    public List<Customer> findByLastName(String lastname);
}
