package com.phone.management.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.phone.management.entity.Phone;
import com.phone.management.model.PhoneContact;

@Component
public class PhoneSpecificationQueryBuilder {

	@Autowired
	private EntityManager entityManager;

	public List<Phone> filter(PhoneContact phoneContact, Pageable pageable) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Phone> criteriaQuery = criteriaBuilder.createQuery(Phone.class);

		Root root = criteriaQuery.from(Phone.class);

		List<Predicate> predicate = new ArrayList<Predicate>();

		if (phoneContact.getPhoneNumber() != null) {
			Predicate phoneNumberPredicate = criteriaBuilder.equal(root.get("phoneNumber"),
					phoneContact.getPhoneNumber());
			predicate.add(phoneNumberPredicate);
		}

		if (phoneContact.getCountry() != null) {
			Predicate countryPredicate = criteriaBuilder.equal(root.get("country"), phoneContact.getCountry());
			predicate.add(countryPredicate);
		}

		if (phoneContact.getState() != null) {
			Predicate statePredicate = criteriaBuilder.equal(root.get("state"), phoneContact.getState());
			predicate.add(statePredicate);
		}

		if (phoneContact.getCountryCode() != null) {
			Predicate countryCodePredicate = criteriaBuilder.equal(root.get("countryCode"),
					phoneContact.getCountryCode());
			predicate.add(countryCodePredicate);
		}

		Predicate and = criteriaBuilder.and(predicate.toArray(new Predicate[predicate.size()]));

		criteriaQuery.where(and);

		return entityManager.createQuery(criteriaQuery).setFirstResult((int) pageable.getOffset())
				.setMaxResults(pageable.getPageSize()).getResultList();

	}

}
