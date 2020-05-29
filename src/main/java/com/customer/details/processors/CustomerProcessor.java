package com.customer.details.processors;

import static com.customer.details.constant.Constant.CUSTOMER_REQUEST_QUEUE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.customer.details.models.CustomerRequest;
import com.customer.details.services.CustomerService;
import com.customer.details.transformer.CustomerTransformer;

@Component
public class CustomerProcessor {

	private static Logger log = LoggerFactory.getLogger(CustomerProcessor.class);

	@Autowired
	private CustomerService service;

	@Autowired
	private CustomerTransformer transformer;

	@JmsListener(destination = CUSTOMER_REQUEST_QUEUE)
	public void receiveMessage(@Payload CustomerRequest request) {

		log.info("Customer Phone number Received as ::::{}", request.getPhoneNumber());

		service.saveCustomer(transformer.transformCustomerRequest(request));

	}

}
