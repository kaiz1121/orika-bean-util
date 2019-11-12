package com.kaiz.util;

public class UserA {

    private Long id;

    private String nameA;

    private String nameB;

	private String nameC;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameA() {
		return nameA;
	}

	public void setNameA(String nameA) {
		this.nameA = nameA;
	}

	public String getNameB() {
		return nameB;
	}

	public void setNameB(String nameB) {
		this.nameB = nameB;
	}

	public String getNameC() {
		return nameC;
	}

	public void setNameC(String nameC) {
		this.nameC = nameC;
	}

	@Override
	public String toString() {
		return "UserA{" +
				"id=" + id +
				", nameA='" + nameA + '\'' +
				", nameB='" + nameB + '\'' +
				", nameC='" + nameC + '\'' +
				'}';
	}
}
