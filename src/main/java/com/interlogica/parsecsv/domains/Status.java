package com.interlogica.parsecsv.domains;

public enum Status {

	ACCEPTED("ACCEPTED"), CORRECTED("CORRECTED"), INCORRECT("INCORRECT");

	private String desc;

	private Status(String desc) {
		this.desc = desc;
	}

	public static Status fromId(String id) {
		switch (id) {
		case "0":
			return ACCEPTED;
		case "1":
			return CORRECTED;
		case "2":
			return INCORRECT;
		}
		return null;
	}

	public String toString() {
		return desc;
	}

}
