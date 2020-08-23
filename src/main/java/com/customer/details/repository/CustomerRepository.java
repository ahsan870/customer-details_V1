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

	@Query(value = "SELECT c FROM CustomerEntity c WHERE c.customerId = :customerId")
	@Transactional
	CustomerEntity findByCustomerId(@Param("customerId") String customerId);

	@Query(value = "Delete FROM CustomerEntity c WHERE c.customerId = :customerId")
	@Modifying
	@Transactional
	void deleteCustomerByCustomerId(@Param(value = "customerId") String customerId);

	public long countByCustomerId(String customerId);

}
