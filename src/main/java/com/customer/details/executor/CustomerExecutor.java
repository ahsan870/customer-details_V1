package com.customer.details.executor;

import static com.customer.details.constant.Constant.CUSTOMER_REQUEST_QUEUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.customer.details.models.CustomerRequest;

@Component
public class CustomerExecutor {

	@Autowired
	private JmsTemplate jmsTemplate;

	public void sendAndReceive(CustomerRequest request) {
		jmsTemplate.convertAndSend(CUSTOMER_REQUEST_QUEUE, request);
	}

}
