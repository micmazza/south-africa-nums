package com.interlogica.parsecsv.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phones")
public class Phone {

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "sms_phone")
	private String smsPhone;

	@Column(name = "status")
	private Status status;

	@Column(name = "description")
	private String description;

	public Phone() {
	}

	public Phone(String smsPhone) {
		this.smsPhone = smsPhone;
	}

	public Phone(String id, String smsPhone) {
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Tutorial [id=" + id + ", smsPhone=" + smsPhone + ", status=" + status + ", description=" + description
				+ "]";
	}
}