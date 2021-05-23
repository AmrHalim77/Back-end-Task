package com.phone.management;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.server.ResponseStatusException;

import com.phone.management.entity.Phone;
import com.phone.management.mapper.PhoneMapper;
import com.phone.management.model.GetPhoneResponse;
import com.phone.management.model.PhoneContact;
import com.phone.management.repository.PhoneRepository;
import com.phone.management.repository.PhoneSpecificationQueryBuilder;
import com.phone.management.service.PhoneService;
import com.phone.management.validator.PhoneValidator;

@RunWith(MockitoJUnitRunner.class)
class PhoneServiceTests {

	@Mock
	PhoneRepository phoneRepository;

	@Spy
	PhoneMapper phoneMapper;

	@Spy
	PhoneValidator phoneValidator;

	@Mock
	PhoneSpecificationQueryBuilder phoneSpecificationQueryBuilder;

	@InjectMocks
	PhoneService phoneService;

	PhoneContact phoneContact;
	Phone phone;

	String country = "Cameroon", state = "valid", countryCode = "+237";
	Long phoneNumber = 23681293L;

	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		phoneContact = PhoneContact.builder().country(country).state(state).countryCode(countryCode)
				.phoneNumber(phoneNumber).build();
		phone = Phone.builder().country(country).state(state).countryCode(countryCode).phoneNumber(phoneNumber).build();
	}

	@Test
	@DisplayName("createValidPhoneContact_ReturnsOkTest")
	void createValidPhoneContact_ReturnsOkTest() {
		when(phoneRepository.save(any(Phone.class))).thenReturn(phone);

		GetPhoneResponse addPhoneContact = phoneService.addPhoneContact(phoneContact);
		verify(phoneRepository, times(1)).save(any(Phone.class));
		Assertions.assertEquals(country, addPhoneContact.getCountry());
		Assertions.assertEquals(state, addPhoneContact.getState());
		Assertions.assertEquals(countryCode, addPhoneContact.getCountryCode());
		Assertions.assertEquals(phoneNumber, addPhoneContact.getPhoneNumber());
	}

	@Test
	@DisplayName("createNotValidPhoneContact_ReturnsOkTest")
	void createNotValidPhoneContact_ReturnsOkTest() {

		when(phoneRepository.save(any(Phone.class))).thenReturn(phone);
		phoneContact.setCountry("cameron");
		Assertions.assertThrows(ResponseStatusException.class, () -> phoneService.addPhoneContact(phoneContact));
	}

	@Test
	@DisplayName("getPhoneContact_ReturnsOkTest")
	void getPhoneContact_ReturnsOkTest() {
		PageRequest pageRequest = PageRequest.of(0, 50);
		List<Phone> phones = new ArrayList<>();
		phones.add(phone);
		when(phoneSpecificationQueryBuilder.filter(phoneContact, pageRequest)).thenReturn(phones);
		Page<GetPhoneResponse> pagePhone = phoneService.getPhone(phoneContact, pageRequest);
		Assertions.assertEquals(1, pagePhone.getTotalElements());
		Assertions.assertEquals(country, pagePhone.getContent().get(0).getCountry());
	}

	@Test
	@DisplayName("getPhoneContactByCountryNotFound_ReturnsOkTest")
	void getPhoneContactByCountryNotFound_ReturnsOkTest() {
		PageRequest pageRequest = PageRequest.of(0, 50);
		List<Phone> phones = new ArrayList<>();
		phoneContact.setCountry("Ethiopia");
		when(phoneSpecificationQueryBuilder.filter(phoneContact, pageRequest)).thenReturn(phones);
		Page<GetPhoneResponse> pagePhone = phoneService.getPhone(phoneContact, pageRequest);
		Assertions.assertEquals(0, pagePhone.getTotalElements());
	}

	@Test
	@DisplayName("getPhoneContactByCountryNotFound_ReturnsOkTest")
	void getPhoneContactByState_ReturnsOkTest() {
		PageRequest pageRequest = PageRequest.of(0, 50);
		List<Phone> phones = new ArrayList<>();
		phones.add(phone);
		phoneContact.setState("valid");
		when(phoneSpecificationQueryBuilder.filter(phoneContact, pageRequest)).thenReturn(phones);
		Page<GetPhoneResponse> pagePhone = phoneService.getPhone(phoneContact, pageRequest);
		Assertions.assertEquals(1, pagePhone.getTotalElements());
		Assertions.assertEquals(state, pagePhone.getContent().get(0).getState());
	}

}