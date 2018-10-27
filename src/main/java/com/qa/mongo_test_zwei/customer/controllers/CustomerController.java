package com.qa.mongo_test_zwei.customer.controllers;

import com.qa.mongo_test_zwei.customer.models.Customer;
import com.qa.mongo_test_zwei.customer.repositories.CustomerRepo;

import org.apache.commons.io.IOUtils;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.omg.CORBA.portable.InputStream;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private CustomerRepo customerRepo;

    public CustomerController(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAll() {
        List<Customer> customers = this.customerRepo.findAll();
        return customers;
    }

//        @PostMapping("/post")
//    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
//
//        File convertFile = new File(file.getOriginalFilename());
//        convertFile.createNewFile();
//        FileOutputStream fout = new FileOutputStream(convertFile);
//        fout.write(file.getBytes());
//        fout.close();
//
//        return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
//    }

    @PostMapping("/uploadCV")
    public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file) {
        try {
            Customer customer = new Customer("tony", "huang");
            customer.setFile(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
            customerRepo.save(customer);
            System.out.println(customer.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Upload failed", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("File is successfully uploaded", HttpStatus.OK);

    }

    @GetMapping(value = "/retrieve",
    produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody byte[] retrieveFile() throws IOException {
        Customer customer = customerRepo.findByFirstName("tony");
        Binary document = customer.getFile();
        try {
            FileOutputStream fos = null;
            fos = new FileOutputStream("/home/tony308/Documents/random.pdf");
            fos.write(document.getData());
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return document.getData();
    }
}