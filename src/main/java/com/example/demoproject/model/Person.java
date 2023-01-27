package com.example.demoproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import javafx.beans.property.*;

import java.time.LocalDate;

public class Person {
	@Expose
	private final StringProperty firstName;
	@Expose
	private final StringProperty lastName;
	@Expose
	private final StringProperty street;
	@Expose
	private final IntegerProperty postalCode;
	@Expose
	private final StringProperty city;
	@Expose
	private final ObjectProperty<LocalDate> birthday;
	/**
	 * Конструктор без параметров.
	 */
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
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		
		// Какие-то фиктивные начальные данные для удобства тестирования.
		this.street = new SimpleStringProperty("какая-то улица");
		this.postalCode = new SimpleIntegerProperty(1234);
		this.city = new SimpleStringProperty("какой-то город");
		this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
	}

	public Person(StringProperty firstName, StringProperty lastName, StringProperty street, IntegerProperty postalCode, StringProperty city, ObjectProperty<LocalDate> birthday) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.birthday = birthday;
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public StringProperty firstNameProperty() {
		return firstName;
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public StringProperty lastNameProperty() {
		return lastName;
	}

	public String getStreet() {
		return street.get();
	}

	public void setStreet(String street) {
		this.street.set(street);
	}

	public StringProperty streetProperty() {
		return street;
	}

	public int getPostalCode() {
		return postalCode.get();
	}

	public void setPostalCode(int postalCode) {
		this.postalCode.set(postalCode);
	}

	public IntegerProperty postalCodeProperty() {
		return postalCode;
	}

	public String getCity() {
		return city.get();
	}

	public void setCity(String city) {
		this.city.set(city);
	}

	public StringProperty cityProperty() {
		return city;
	}

	public LocalDate getBirthday() {
		return birthday.get();
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday.set(birthday);
	}

	public ObjectProperty<LocalDate> birthdayProperty() {
		return birthday;
	}
}