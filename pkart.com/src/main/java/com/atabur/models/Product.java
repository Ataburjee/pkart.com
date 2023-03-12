package com.atabur.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Product {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
	@NotNull(message = "Image url is mandetory...!")
	private String imageURL;
	
	@Column(nullable = false)
	@NotNull(message = "Product Name is mandetory...!")
	private String productName;
	
	@Column(nullable = false)
	@NotNull(message = "Brand Name is mandetory...!")
	private String brandName;
	
	@Column(nullable = false)
	@NumberFormat
	@NotNull(message = "Sale Price is mandetory...!")
	private double salePrice;
	
	@Column(nullable = false)
	@NumberFormat
	@NotNull(message = "Market Price is mandetory...!")
	private double marketPrice;
	
	@Column(nullable = false)
	private Long categoryid;
	
	@Column(nullable = false)
	private Long supplierid;
	
	@ManyToOne
	@Transient
	@JsonIgnore
	@JoinColumn(referencedColumnName = "productid")
	private Order order;
	
	@ManyToOne
	@Transient
	@JsonIgnore
	private Category category;
	
	@ManyToOne
	@Transient
	@JsonIgnore
	private Supplier supplier;
	
}
