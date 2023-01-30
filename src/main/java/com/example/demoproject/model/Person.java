package com.example.demoproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import javafx.beans.property.*;

import java.time.LocalDate;

public class Person {
	@Expose
	private String firstName;
	@Expose
	private String lastName;
	@Expose
	private String street;
	@Expose
	private Integer postalCode;
	@Expose
	private String city;
	@Expose
//	private final ObjectProperty<LocalDate> birthday;
	private LocalDate birthday;

	public Person() {
		this(null, null);
	}
	/**
	 * Конструктор с некоторыми начальными данными.
	 *
	 * @param firstName
	 * @param lastName
	 */
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		
		// Какие-то фиктивные начальные данные для удобства тестирования.
		this.street = "какая-то улица";
		this.postalCode = 1234;
		this.city = "какой-то город";
		this.birthday = LocalDate.of(1999, 2, 21);
	}

	public Person(String firstName, String lastName, String street, Integer postalCode, String city, LocalDate birthday) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.birthday = birthday;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
}