package com.interlogica.parsecsv;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.interlogica.parsecsv.controllers.PhoneController;

@SpringBootTest
public class UploadApplicationTests {

	@Autowired
	private PhoneController controller;

	@Test
	void contextLoads() {
		assertTrue(controller != null);
	}

}
