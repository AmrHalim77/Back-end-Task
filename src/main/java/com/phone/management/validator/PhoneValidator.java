package com.phone.management.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.phone.management.model.PhoneContact;

@Component
public class PhoneValidator {

	public boolean validatePhoneContact(PhoneContact phoneContact) {
		System.out
				.println("in regex " + phoneContact.getCountryCode().concat(phoneContact.getPhoneNumber().toString()));

		if (phoneContact.getCountry().equalsIgnoreCase("Cameroon")

				&& Pattern.matches("\\+237\\ ?[2368]\\d{7,8}$",
						phoneContact.getCountryCode().concat(phoneContact.getPhoneNumber().toString())))
			return true;

		if (phoneContact.getCountry().equalsIgnoreCase("Ethiopia") && Pattern.matches("\\+251\\ ?[1-59]\\d{8}$",
				phoneContact.getCountryCode().concat(phoneContact.getPhoneNumber().toString())))
			return true;

		if (phoneContact.getCountry().equalsIgnoreCase("Morocco") && Pattern.matches("\\+212\\ ?[5-9]\\d{8}$",
				phoneContact.getCountryCode().concat(phoneContact.getPhoneNumber().toString())))
			return true;

		if (phoneContact.getCountry().equalsIgnoreCase("Mozambique") && Pattern.matches("\\+258\\ ?[28]\\d{7,8}$",
				phoneContact.getCountryCode().concat(phoneContact.getPhoneNumber().toString())))
			return true;

		if (phoneContact.getCountry().equalsIgnoreCase("Uganda") && Pattern.matches("\\+256\\ ?\\d{9}$",
				phoneContact.getCountryCode().concat(phoneContact.getPhoneNumber().toString())))
			return true;

		return false;
	}

}
