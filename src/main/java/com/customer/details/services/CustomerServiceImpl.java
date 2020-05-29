package com.customer.details.services;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.details.entity.CustomerEntity;
import com.customer.details.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public String saveCustomer(CustomerEntity customerEntity) {
		Random rand = new Random();
		String nextInt = String.valueOf(rand.nextInt(Integer.MAX_VALUE));
		customerEntity.setCustomerId(nextInt);
		customerRepository.save(customerEntity);
		return nextInt;

	}

	@Override
	public List<CustomerEntity> getAll() {
		return customerRepository.findAll();
	}

	@Override
	public CustomerEntity findByPhoneNumber(String phoneNumber) {
		CustomerEntity customerEntity = customerRepository.findByPhoneNumber(phoneNumber);
		if (customerEntity != null) {
			return customerEntity;
		} else {
			return null;
		}
	}

	@Override
	public void deleteCustomerByPhoneNumber(String phoneNumber) {
		customerRepository.deleteCustomerByPhoneNumber(phoneNumber);

	}

	@Override
	public void updateCustomer(CustomerEntity customerEntity, String phoneNumber) {

		CustomerEntity entity = customerRepository.findByPhoneNumber(phoneNumber);
		if (entity != null) {
			entity.setFirstName(customerEntity.getFirstName());
			entity.setLastName(customerEntity.getLastName());
			entity.setMailId(customerEntity.getMailId());
			entity.setPhoneNumber(phoneNumber);
			customerRepository.save(entity);
		} else {
			customerEntity.setPhoneNumber(phoneNumber);
			customerRepository.save(customerEntity);
		}

	}

	@Override
	public boolean isCustomerAlreadyExists(String phoneNumber) {
		return customerRepository.countByPhoneNumber(phoneNumber) > 0;
	}

	@Override
	public boolean isCustomerAlreadyExistsByCustomerId(String customerId) {
		return customerRepository.countByCustomerId(customerId) > 0;
	}
}
