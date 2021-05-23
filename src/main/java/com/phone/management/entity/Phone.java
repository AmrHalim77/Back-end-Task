package com.phone.management.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Phone {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Long phoneNumber;

	private String country;
	private String state;
	private String countryCode;

}
