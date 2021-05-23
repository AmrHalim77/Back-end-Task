package com.phone.management.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneContact {

	@NotNull(message = "phone number should be not null")
	private Long phoneNumber;
	@NotBlank(message = "mandatory field country")
	private String country;
	@NotBlank(message = "mandatory field state")
	private String state;
	@NotBlank(message = "mandatory field country code ")
	private String countryCode;
}
