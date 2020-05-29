package com.customer.details.transformer;

import org.springframework.stereotype.Component;

import com.customer.details.entity.CustomerEntity;
import com.customer.details.models.CustomerRequest;
import com.customer.details.models.CustomerResponse;

@Component
public class CustomerTransformer {

	public CustomerEntity transformCustomerRequest(CustomerRequest request) {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setFirstName(request.getFirstName());
		customerEntity.setLastName(request.getLastName());
		customerEntity.setMailId(request.getMailId());
		customerEntity.setPhoneNumber(request.getPhoneNumber());
		return customerEntity;

	}

	public CustomerResponse fetchCustomer(CustomerEntity customerEntity) {

		CustomerResponse response = new CustomerResponse();
		response.setFirstName(customerEntity.getFirstName());
		response.setLastName(customerEntity.getLastName());
		response.setMailId(customerEntity.getMailId());
		response.setPhoneNumber(customerEntity.getPhoneNumber());
		return response;

	}
}
