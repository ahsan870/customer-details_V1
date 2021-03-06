package com.customer.details.services;

import java.util.List;

import com.customer.details.entity.CustomerEntity;

public interface CustomerService {

	public String saveCustomer(CustomerEntity customerEntity);

	public List<CustomerEntity> getAll();

	public CustomerEntity findByPhoneNumber(String phoneNumber);

	public void deleteCustomerByPhoneNumber(String phoneNumber);

	public void updateCustomer(CustomerEntity customerEntity, String phoneNumber);

	public boolean isCustomerAlreadyExists(String phoneNumber);

	public boolean isCustomerAlreadyExistsByCustomerId(String customerId);

}
