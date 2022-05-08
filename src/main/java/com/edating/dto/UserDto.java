package com.edating.dto;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

public class UserDto {

	private Long id;

	@NotBlank
	@Length(min = 5, max = 30)
	private String username;

	@NotBlank
	@Length(min = 5, max = 30)
	private String password;

	@NotBlank
	@Email
	private String email;

	@NotNull
	@Min(value = 18, message = "Age should not be less than 18")
	@Max(value = 45, message = "Age should not be greater than 45")
	private Integer age;

	private String gender;

	@NotNull
	@Size(min = 10, max = 10)
	@Pattern(regexp = "(^$|[0-9]{10})")
	private String phoneNumber;

	private String city;
	private String country;

	@Valid
	private InterestDto interest;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public InterestDto getInterest() {
		return interest;
	}

	public void setInterest(InterestDto interest) {
		this.interest = interest;
	}

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((age == null) ? 0 : age.hashCode());
	    result = prime * result + ((city == null) ? 0 : city.hashCode());
	    result = prime * result + ((country == null) ? 0 : country.hashCode());
	    result = prime * result + ((email == null) ? 0 : email.hashCode());
	    result = prime * result + ((gender == null) ? 0 : gender.hashCode());
	    result = prime * result + ((id == null) ? 0 : id.hashCode());
	    result = prime * result + ((interest == null) ? 0 : interest.hashCode());
	    result = prime * result + ((password == null) ? 0 : password.hashCode());
	    result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
	    result = prime * result + ((username == null) ? 0 : username.hashCode());
	    return result;
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
		return true;
	    if (obj == null)
		return false;
	    if (getClass() != obj.getClass())
		return false;
	    UserDto other = (UserDto) obj;
	    if (age == null) {
		if (other.age != null)
		    return false;
	    } else if (!age.equals(other.age))
		return false;
	    if (city == null) {
		if (other.city != null)
		    return false;
	    } else if (!city.equals(other.city))
		return false;
	    if (country == null) {
		if (other.country != null)
		    return false;
	    } else if (!country.equals(other.country))
		return false;
	    if (email == null) {
		if (other.email != null)
		    return false;
	    } else if (!email.equals(other.email))
		return false;
	    if (gender == null) {
		if (other.gender != null)
		    return false;
	    } else if (!gender.equals(other.gender))
		return false;
	    if (id == null) {
		if (other.id != null)
		    return false;
	    } else if (!id.equals(other.id))
		return false;
	    if (interest == null) {
		if (other.interest != null)
		    return false;
	    } else if (!interest.equals(other.interest))
		return false;
	    if (password == null) {
		if (other.password != null)
		    return false;
	    } else if (!password.equals(other.password))
		return false;
	    if (phoneNumber == null) {
		if (other.phoneNumber != null)
		    return false;
	    } else if (!phoneNumber.equals(other.phoneNumber))
		return false;
	    if (username == null) {
		if (other.username != null)
		    return false;
	    } else if (!username.equals(other.username))
		return false;
	    return true;
	}

	

}
