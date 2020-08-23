package com.customer.details.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.customer.details.entity.CustomerEntity;
@Service
public interface CustomerService {

	public String saveCustomer(CustomerEntity customerEntity);

	public List<CustomerEntity> getAll();

	public CustomerEntity findByCustomerId(String customerId);

	public void deleteCustomerByCustomerId(String customerId);

	public void updateCustomer(CustomerEntity customerEntity, String customerId);

	public boolean isCustomerAlreadyExists(String customerId);

	public boolean isCustomerAlreadyExistsByCustomerId(String customerId);

}
