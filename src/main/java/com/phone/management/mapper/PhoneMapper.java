package com.phone.management.mapper;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import com.phone.management.entity.Phone;
import com.phone.management.model.GetPhoneResponse;
import com.phone.management.model.PhoneContact;

@Component
public class PhoneMapper {

	public Phone mapPhoneContactToEntity(@Valid PhoneContact phoneContact) {

		return Phone.builder().phoneNumber(phoneContact.getPhoneNumber()).country(phoneContact.getCountry())
				.state(phoneContact.getState()).countryCode(phoneContact.getCountryCode()).build();

	}

	public static GetPhoneResponse mapPhoneEntityToPhoneResponse(Phone phone) {
		return GetPhoneResponse.builder().id(phone.getId()).country(phone.getCountry())
				.countryCode(phone.getCountryCode()).state(phone.getState()).phoneNumber(phone.getPhoneNumber())
				.build();
	}
}
