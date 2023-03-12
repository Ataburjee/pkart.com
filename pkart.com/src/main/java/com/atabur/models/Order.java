package com.atabur.models;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long customerid;
    
    @Column(nullable = false)
    private Long productid;
    
    @Column(nullable = false)
    private Long paymentid;
    
    @Column(nullable = false)
    private Long shipperid;
    
    private Integer quantity;
    
    private Integer orderDetailsid;
    
    @ManyToOne
    @Transient
    @JsonIgnore
    private Customer customer;
    
    @Transient
    @JsonIgnore
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "order")
    private List<Product> products = new ArrayList<>();
    
    @ManyToOne
    @Transient
    @JsonIgnore
    private Payment payment;
    
    @ManyToOne
    @Transient
    @JsonIgnore
    private Shipper shipper;
    
}
