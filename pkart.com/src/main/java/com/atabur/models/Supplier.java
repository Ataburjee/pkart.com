package com.atabur.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name = "suppliers")
public class Supplier {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(nullable = false)
	@NotBlank(message = "Please fill the company name")
	private String companyName;
	
	@Column(nullable = false)
	@NotBlank(message = "Please fill the city where company established")
	private String city;
	
	@Column(nullable = false)
	@NotBlank(message = "Please fill the state where company established")
	private String state;
	
	
	@Column(nullable = false)
	@NotBlank(message = "Please fill the postalcode where company established")
	private String postalcode;
	
	@Column(nullable = false)
	@NotBlank(message = "Please fill the country where company established")
	private String country;
	
	@Column(nullable = false)
	@Size(min = 10,max = 10,message = "Mobile no should be of 10 digit")
	@NumberFormat
	private String phone;
	
	@Email(message = "Please provide a valid email")
	private String email;
	
	@OneToMany
	@JsonIgnore
	@JoinColumn(name = "supplierid")
	private List<Product> products = new ArrayList<>();
	
}
