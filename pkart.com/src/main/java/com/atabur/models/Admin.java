package com.atabur.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

import lombok.Data;

@Entity
@Data
public class Admin implements User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Admin name must not be null...!")
	private String name;
	
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@NotNull
	@Email
	@Column(unique = true)
	private String email;
	
	@NotNull(message = "Password must not be null...!")
	private String password;
	
	@NotNull(message = "Confirm Password must not be null...!")
	private String confirm_password;
	
	@NotNull
	@Size(min = 10, max = 10, message = "Mobile No should be of 10 digit")
	@Column(unique = true)
	@NumberFormat
	private String mobile;
	
	final String role = "ADMIN";
	
	private String address;
	
}
