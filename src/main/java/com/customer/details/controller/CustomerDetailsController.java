package com.customer.details.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/customer", produces = { MediaType.APPLICATION_JSON_VALUE })
@Api
public class CustomerDetailsController {

	private static final String MESSAGE = "Customer With phone number ";

	@Autowired
	private CustomerService service;

	@Autowired
	private CustomerTransformer transformer;

	@PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {

		CustomerEntity customerEntity = service.findByPhoneNumber(customerRequest.getPhoneNumber());
		if (customerEntity == null) {
			String customerId = service.saveCustomer(transformer.transformCustomerRequest(customerRequest));
			CustomerResponse response = new CustomerResponse(
					"Customer requested With phone number " + customerRequest.getPhoneNumber() + " created Successfully"
							+ "With CustomerId is:: " + customerId);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
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

	@GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerResponse> getCustomerByPhoneNumber(
			@RequestParam(value = "name", required = true) final String phoneNumber) {

		CustomerEntity customerEntity = service.findByPhoneNumber(phoneNumber);
		if (customerEntity != null) {
			return new ResponseEntity<>(transformer.fetchCustomer(customerEntity), HttpStatus.OK);
		} else {
			CustomerResponse response = new CustomerResponse(MESSAGE + phoneNumber + " not found");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping(path = "/delete/{phoneNumber}")
	public ResponseEntity<CustomerResponse> deleteCustomer(@PathVariable String phoneNumber) {

		service.deleteCustomerByPhoneNumber(phoneNumber);
		CustomerResponse response = new CustomerResponse(MESSAGE + phoneNumber + " Delete Successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PutMapping("/update/{phoneNumber}")
	public ResponseEntity<CustomerResponse> updateCustomer(@RequestBody CustomerRequest customerRequest,
			@PathVariable String phoneNumber) {

		service.updateCustomer(transformer.transformCustomerRequest(customerRequest), phoneNumber);
		CustomerResponse response = new CustomerResponse(MESSAGE + phoneNumber + " updated Successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/check/{phoneNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> getData(
			@PathVariable(value = "phoneNumber", required = true) final String phoneNumber) {

		boolean customerExists = service.isCustomerAlreadyExists(phoneNumber);
		return new ResponseEntity<>(customerExists, HttpStatus.OK);

	}

	@GetMapping(value = "/check/customerId/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> isCustomerIdExist(
			@PathVariable(value = "customerId", required = true) final String customerId) {

		boolean customerExists = service.isCustomerAlreadyExistsByCustomerId(customerId);
		return new ResponseEntity<>(customerExists, HttpStatus.OK);

	}

}
