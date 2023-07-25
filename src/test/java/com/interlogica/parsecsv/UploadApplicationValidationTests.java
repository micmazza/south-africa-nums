package com.interlogica.parsecsv;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.interlogica.parsecsv.domains.Phone;
import com.interlogica.parsecsv.domains.Status;
import com.interlogica.parsecsv.service.ValidationService;

@SpringBootTest
public class UploadApplicationValidationTests {

	@Autowired
	private ValidationService valService;

	@Test
	void contextLoads() {
	}

	@Test
	@DisplayName("Phone number validation: not a number")
	void testValidationNotNumber() {
		Phone p = new Phone("123abc");
		valService.validatePhone(p);
		assertEquals(Status.INCORRECT, p.getStatus());
		assertEquals("not a number", p.getDescription());
	}

	@Test
	@DisplayName("Phone number validation: invalid number length")
	void testValidationTooShort() {
		Phone p = new Phone("123456");
		valService.validatePhone(p);
		assertEquals(Status.INCORRECT, p.getStatus());
		assertEquals("invalid number length", p.getDescription());
	}

	@Test
	@DisplayName("Phone number validation: invalid number length")
	void testValidationTooLong() {
		Phone p = new Phone("123456789123456789");
		valService.validatePhone(p);
		assertEquals(Status.INCORRECT, p.getStatus());
		assertEquals("invalid number length", p.getDescription());
	}

	@Test
	@DisplayName("Phone number validation: invalid number length")
	void testValidationInvalidLength() {
		Phone p = new Phone("1234567890");
		valService.validatePhone(p);
		assertEquals(Status.INCORRECT, p.getStatus());
		assertEquals("invalid number length", p.getDescription());
	}

	@Test
	@DisplayName("Phone number validation: added prefix")
	void testValidationAddedPrefix() {
		Phone p = new Phone("123456789");
		valService.validatePhone(p);
		assertEquals(Status.CORRECTED, p.getStatus());
		assertEquals("added prefix", p.getDescription());
	}

	@Test
	@DisplayName("Phone number validation: invalid international prefix")
	void testValidationInvalidPrefix() {
		Phone p = new Phone("29736529279");
		valService.validatePhone(p);
		assertEquals(Status.INCORRECT, p.getStatus());
		assertEquals("invalid international prefix", p.getDescription());
	}

}
