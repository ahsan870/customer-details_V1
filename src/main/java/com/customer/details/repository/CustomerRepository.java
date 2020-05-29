package com.customer.details.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.customer.details.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

	@Query(value = "SELECT c FROM CustomerEntity c WHERE c.phoneNumber = :phoneNumber")
	@Transactional
	CustomerEntity findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

	@Query(value = "Delete FROM CustomerEntity c WHERE c.phoneNumber = :phoneNumber")
	@Modifying
	@Transactional
	void deleteCustomerByPhoneNumber(@Param(value = "phoneNumber") String phoneNumber);

	public long countByPhoneNumber(String phoneNumber);

	int countByCustomerId(String customerId);

}
