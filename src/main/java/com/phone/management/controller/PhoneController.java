package com.phone.management.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phone.management.model.GetPhoneResponse;
import com.phone.management.model.PhoneContact;
import com.phone.management.service.PhoneService;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api")
public class PhoneController {

	@Autowired
	private PhoneService phoneService;

	@PostMapping(value = "/add")
	public ResponseEntity<GetPhoneResponse> add(@Valid @RequestBody PhoneContact phoneContact) {
		return ResponseEntity.ok(phoneService.addPhoneContact(phoneContact));
	}

	@GetMapping(value = "/getAll")
	public Page<GetPhoneResponse> getPhone(@RequestParam(name = "phoneNumber", required = false) Long phoneNumber,
			@RequestParam(name = "country", required = false) String country,
			@RequestParam(name = "state", required = false) String state,
			@RequestParam(name = "countryCode", required = false) String countryCode,
			@PageableDefault(value = 50, page = 0) Pageable pageable) {

		PhoneContact phoneContact = PhoneContact.builder().phoneNumber(phoneNumber).country(country).state(state)
				.countryCode(countryCode).build();

		return phoneService.getPhone(phoneContact, pageable);

	}

}
