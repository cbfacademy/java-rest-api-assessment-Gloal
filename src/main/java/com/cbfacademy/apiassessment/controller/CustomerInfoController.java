package com.cbfacademy.apiassessment.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cbfacademy.apiassessment.model.CustomerInfo;
import com.cbfacademy.apiassessment.repository.CustomerCollectionRepository;

@RestController
@RequestMapping("/customer")
public class CustomerInfoController {

    private final CustomerCollectionRepository customerCollectionRepository;


    public CustomerInfoController(CustomerCollectionRepository repository){
        this.customerCollectionRepository = repository;
    }


    /**Rertuns all customers in the database */   
    @GetMapping("")
   public List<CustomerInfo> getAllCustomerInfos(){
    return customerCollectionRepository.getAllCustomerInfos();
   }

   /**Creates New Customer*/
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public CustomerInfo createCustomer(@RequestBody CustomerInfo customerInfo){
        return customerCollectionRepository.createCustomerInfo(customerInfo) ;
    }    
    
    /**Returns Customer by Id*/
    @GetMapping("/{id}")
    public CustomerInfo getCustomerById(@PathVariable String id){
        Long idLong = Long.parseLong(id); 
        return customerCollectionRepository.getCustomerById(idLong)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found in database"));
    } 

    @PutMapping("/{id}")
    public ResponseEntity<CustomerInfo> updateCustomer(@PathVariable String id, @RequestBody CustomerInfo customerInfo){
        if(!customerCollectionRepository.existsById(customerInfo.id())){
            throw new ResponseStatusException(HttpStatusCode.NOT_FOUND, "Customer does not exist")
        }
        Long idLong = Long.parseLong(id);
        return ResponseEntity.ok().body(customerCollectionRepository.updateCustomerInfo(idLong, customerInfo));
    } 


    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id){
         customerCollectionRepository.deleteCustomer(id);
    } 



}
