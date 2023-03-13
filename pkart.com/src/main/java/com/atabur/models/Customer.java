package com.atabur.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
import java.time.LocalDateTime;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name = "customers")
public class Customer implements User{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull(message = "Admin name must not be null...!")
	private String name;
	
	@NotNull
	@Email
	@Column(unique = true)
	private String email;
	
	@NotNull(message = "Password must not be null...!")
	private String password;

	@NotNull(message = "Confirm Password must not be null...!")
	private String confirm_password;
	
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@NotNull
	@Size(min = 10, max = 10, message = "Mobile No should be of 10 digit")
	@Column(unique = true)
	@NumberFormat
	private String mobile;
	
	private String address;
	
	final String role = "CUSTOMER";

	@JsonIgnore
    @OneToMany( cascade = CascadeType.ALL)
    @JoinColumn(name = "customerid")
    private List<Order> orders = new ArrayList<>();

}
