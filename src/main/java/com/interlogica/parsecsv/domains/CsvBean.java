package com.interlogica.parsecsv.domains;

import com.opencsv.bean.CsvBindByName;

public class CsvBean {

	@CsvBindByName(column = "id")
	private String id;

	@CsvBindByName(column = "sms_phone")
	private String smsPhone;

	public CsvBean() {
	}

	public CsvBean(String id, String smsPhone) {
		this.id = id;
		this.smsPhone = smsPhone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSmsPhone() {
		return smsPhone;
	}

	public void setSmsPhone(String smsPhone) {
		this.smsPhone = smsPhone;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", smsPhone='" + smsPhone + "}";
	}
}
