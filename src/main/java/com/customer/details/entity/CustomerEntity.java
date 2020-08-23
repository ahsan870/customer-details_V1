package com.customer.details.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "CUSTOMER")
public class CustomerEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(name = "CUSTOMER_ID")
	private String customerId;
	
	@NotBlank
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@NotBlank
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@NotBlank
	@Column(name = "MAIL_ID")
	private String mailId;

	@NotBlank
	@Column(name = "PHONE_NUMBER" , nullable = false)
	private String phoneNumber;
	
	@NotBlank
	@Column(name = "city")
	private String city;
	
	@NotBlank
	@Column(name = "postalCode")
	private String postalCode;
	
	@NotBlank
	@Column(name = "state")
	private String state;
	
	@NotBlank
	@Column(name = "country")
	private String country;
	


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "CustomerEntity [id=" + id + ", customerId=" + customerId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", mailId=" + mailId + ", phoneNumber=" + phoneNumber + ", city=" + city + ", postalCode="
				+ postalCode + ", state=" + state + ", country=" + country + "]";
	}
	
	
}
