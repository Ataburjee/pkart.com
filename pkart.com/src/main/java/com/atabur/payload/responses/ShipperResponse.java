package com.atabur.payload.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipperResponse {

	private String company;
	private String contact;
	private String mail;
	
}
