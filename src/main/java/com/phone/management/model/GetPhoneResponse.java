package com.phone.management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPhoneResponse {

	private Integer id;
	private Long phoneNumber;
	private String country;
	private String state;
	private String countryCode;
}
