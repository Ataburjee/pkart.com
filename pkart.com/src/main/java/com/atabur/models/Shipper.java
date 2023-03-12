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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name = "shippers")
public class Shipper {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@NotNull(message = "Company name can not be null ..!")
	private String companyName;
	
	@NumberFormat
	@Size(min = 10,max = 10,message = "Mobile no should be of 10 digit")
	@Column(nullable = false)
	private String mobile;
	
	@Email
	private String email;
	
	@OneToMany
	@JsonIgnore
	@JoinColumn(name = "shipperid")
	private List<Order> orders = new ArrayList<>();
	
}
