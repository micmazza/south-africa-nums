package com.interlogica.parsecsv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interlogica.parsecsv.domains.Phone;
import com.interlogica.parsecsv.domains.Status;

public interface PhoneRepository extends JpaRepository<Phone, Object> {

	Phone findBySmsPhone(String p);

	List<Phone> findByStatus(Status s);

}