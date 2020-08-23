package com.customer.details.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.customer.details.entity.CustomerEntity;
import com.customer.details.models.CustomerRequest;
import com.customer.details.models.CustomerResponse;
import com.customer.details.services.CustomerService;
import com.customer.details.transformer.CustomerTransformer;
import com.customer.details.utils.EmailUtil;


import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/customer", produces = { MediaType.APPLICATION_JSON_VALUE })
@Api
public class CustomerDetailsController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerDetailsController.class);
	private static final String MESSAGE = "Customer With phone number ";
	private static final String SUBJECT = "Customer data has been Saved!!! ";
	private static final String BODY = "Customar Account has been created succcessfully with Customer ID : ";
	private static final String SIGNATURE = "\nThanks,\nPavelist Inc.";
	

	@Autowired
	private CustomerService service;

	@Autowired
	private CustomerTransformer transformer;

	@Autowired
	private EmailUtil email;

	@PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {

		CustomerEntity customerEntity = service.findByCustomerId(customerRequest.getPhoneNumber());
		if (customerEntity == null) {
			String customerId = service.saveCustomer(transformer.transformCustomerRequest(customerRequest));
			CustomerResponse response = new CustomerResponse(
					"Customer requested With phone number " + customerRequest.getPhoneNumber() + " created Successfully "
							+ "With CustomerId is:: " + customerId);
			
			email.sendEmail(customerRequest.getMailId(), SUBJECT, "Hello "+customerRequest.getFirstName() +"\n"+ BODY + customerId +SIGNATURE);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
			
			//logger.info("Customer requested With phone number", phoneNumber);
			//logger.("Customer requested With phone number", phoneNumber);
			//logger.info("created Successfully With CustomerId is" , customerId);
			//logger.info("Order number is received as::{}", orderNumber);
			
		} else {
			CustomerResponse response = new CustomerResponse(
					"Customer requested With phone number " + customerRequest.getPhoneNumber() + " already exist");

		
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		}

	}

	@GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CustomerEntity>> getAllCustomer() {
		return new ResponseEntity<>(service.getAll(), HttpStatus.OK);

	}

	@GetMapping(path = "/getCustomer", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerResponse> getCustomerByCustomerId(
			@RequestParam(value = "customerId", required = true) final String customerId) {

		CustomerEntity customerEntity = service.findByCustomerId(customerId);
		if (customerEntity != null) {
			return new ResponseEntity<>(transformer.fetchCustomer(customerEntity), HttpStatus.OK);
		} else {
			CustomerResponse response = new CustomerResponse(MESSAGE + customerId + " not found");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping(path = "/delete/{customerId}")
	public ResponseEntity<CustomerResponse> deleteCustomer(@PathVariable String customerId) {

		service.deleteCustomerByCustomerId(customerId);
		CustomerResponse response = new CustomerResponse(MESSAGE + customerId + " Delete Successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PutMapping("/update/{customerId}")
	public ResponseEntity<CustomerResponse> updateCustomer(@RequestBody CustomerRequest customerRequest,
			@PathVariable String customerId) {

		service.updateCustomer(transformer.transformCustomerRequest(customerRequest), customerId);
		CustomerResponse response = new CustomerResponse(MESSAGE + customerId + " updated Successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/check/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> getData(
			@PathVariable(value = "customerId", required = true) final String customerId) {

		boolean customerExists = service.isCustomerAlreadyExists(customerId);
		return new ResponseEntity<>(customerExists, HttpStatus.OK);

	}

	@GetMapping(value = "/check/customerId/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> isCustomerIdExist(
			@PathVariable(value = "customerId", required = true) final String customerId) {

		boolean customerExists = service.isCustomerAlreadyExistsByCustomerId(customerId);
		return new ResponseEntity<>(customerExists, HttpStatus.OK);

	}

}
