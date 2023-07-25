package com.interlogica.parsecsv.service;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.interlogica.parsecsv.domains.Phone;
import com.interlogica.parsecsv.domains.Status;

@Service
public class ValidationService {

	private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
	private final String internatPrefix = "27";

	public Phone validatePhone(Phone p) {
		if (!isNumeric(p.getSmsPhone())) {
			p.setStatus(Status.INCORRECT);
			p.setDescription("not a number");
		} else if (p.getSmsPhone().length() != 11 && p.getSmsPhone().length() != 9) {
			p.setStatus(Status.INCORRECT);
			p.setDescription("invalid number length");
		} else if (p.getSmsPhone().length() == 9) {
			p.setSmsPhone(internatPrefix + p.getSmsPhone());
			p.setStatus(Status.CORRECTED);
			p.setDescription("added prefix");
		} else if (p.getSmsPhone().length() == 11) {
			if (p.getSmsPhone().startsWith(internatPrefix)) {
				p.setStatus(Status.ACCEPTED);
				p.setDescription("accepted");
			} else {
				p.setStatus(Status.INCORRECT);
				p.setDescription("invalid international prefix");
			}
		}
		return p;
	}

	private boolean isNumeric(String strNum) {
		try {
			if (strNum == null || strNum.isEmpty()) {
				return false;
			}
			return pattern.matcher(strNum).matches();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
