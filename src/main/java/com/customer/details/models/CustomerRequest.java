package com.customer.details.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CustomerRequest {

	@NotNull(message = "Please provide first Name")
	@NotEmpty(message = "Please provide valid first Name")
	private String firstName;

	@NotNull(message = "Please provide last Name")
	@NotEmpty(message = "Please provide valid last Name")
	private String lastName;

	@NotNull(message = "Missing mandatory parameter - phone number")
	@NotEmpty(message = "Please provide valid phone number")
	@Pattern(regexp = "(^$|[0-9]{10})")
	@Size(min = 10, max = 10)
	private String phoneNumber;

	@NotNull(message = "Please provide email address")
	@NotEmpty(message = "Please provide valid email address")
	@Email(message = "Please provide valid email address")
	private String mailId;

	@NotNull(message = "Please provide city")
	@NotEmpty(message = "Please provide valid city")
	private String city;

	@NotNull(message = "Please provide state")
	@NotEmpty(message = "Please provide valid state")
	private String state;

	@NotNull(message = "Please provide postalCode")
	@NotEmpty(message = "Please provide valid postalCode")
	private String postalCode;

	@NotNull(message = "Please provide country")
	@NotEmpty(message = "Please provide valid country")
	private String country;

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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "CustomerRequest [firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber
				+ ", mailId=" + mailId + ", city=" + city + ", state=" + state + ", postalCode=" + postalCode
				+ ", country=" + country + "]";
	}

}
