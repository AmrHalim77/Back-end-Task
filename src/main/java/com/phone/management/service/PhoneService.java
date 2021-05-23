package com.phone.management.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.phone.management.entity.Phone;
import com.phone.management.mapper.PhoneMapper;
import com.phone.management.model.GetPhoneResponse;
import com.phone.management.model.PhoneContact;
import com.phone.management.repository.PhoneRepository;
import com.phone.management.repository.PhoneSpecificationQueryBuilder;
import com.phone.management.validator.PhoneValidator;

@Service
public class PhoneService {

	@Autowired
	private PhoneValidator phoneValidator;

	@Autowired
	private PhoneMapper phoneMapper;

	@Autowired
	private PhoneRepository phoneRepository;

	@Autowired
	private PhoneSpecificationQueryBuilder phoneSpecificationQB;

	public GetPhoneResponse addPhoneContact(PhoneContact phoneContact) {

		if (!phoneValidator.validatePhoneContact(phoneContact))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "phone contact not valid");
		Phone phone = phoneMapper.mapPhoneContactToEntity(phoneContact);
		Phone savedPhone = phoneRepository.save(phone);
		return PhoneMapper.mapPhoneEntityToPhoneResponse(savedPhone);

	}

	public Page<GetPhoneResponse> getPhone(PhoneContact phoneContact, Pageable pageable) {
		List<Phone> phonePage = phoneSpecificationQB.filter(phoneContact, pageable);
		List<GetPhoneResponse> phoneResponseList = phonePage.stream().map(PhoneMapper::mapPhoneEntityToPhoneResponse)
				.collect(Collectors.toList());

		return new PageImpl<>(phoneResponseList, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()),
				phoneResponseList.size());

	}

}
