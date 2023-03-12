package com.atabur.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "order_details")
public class OrderDetail {
	@Id
	private Integer id;
	private Long orderid;
	private Long supplierid;
	private Long paymentid;
}
