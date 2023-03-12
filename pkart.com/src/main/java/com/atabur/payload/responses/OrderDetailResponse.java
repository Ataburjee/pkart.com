package com.atabur.payload.responses;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponse {

	private String productName;
	private String brandName;
	private Double productPrice;
	private Integer quantity;
	private Double totalOrderAmount;
	private Double totalDiscount;
	private LocalDateTime deliveryDate;
	
}
