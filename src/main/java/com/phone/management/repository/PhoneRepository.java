package com.phone.management.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.phone.management.entity.Phone;

@Repository
public interface PhoneRepository extends PagingAndSortingRepository<Phone, Integer>, JpaSpecificationExecutor<Phone> {

}