package com.example.demoproject.controller;

import com.example.demoproject.MainApp;
import com.example.demoproject.model.Person;
import com.example.demoproject.util.DateUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonOverviewController {
	@FXML
	private TableView<Person> personTable;
	@FXML
	private TableColumn<Person, String> firstNameColumn;
	@FXML
	private TableColumn<Person, String> lastNameColumn;
	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label streetLabel;
	@FXML
	private Label cityLabel;
	@FXML
	private Label postalCodeLabel;
	@FXML
	private Label birthdayLabel;
	
	// Ссылка на главное приложение.
	private MainApp mainApp;
	
	/**
	 * Конструктор. Конструктор вызывается раньше метода initialize().
	 */
	public PersonOverviewController() {
	}
	
	/**
	 * Инициализация класса-контроллера. Этот метод вызывается автоматически после того, как fxml-файл будет загружен.
	 */
	@FXML
	private void initialize() {
		// Инициализация таблицы адресатов с двумя столбцами.
		firstNameColumn.setCellValueFactory(
				cellData -> cellData.getValue().firstNameProperty());
		lastNameColumn.setCellValueFactory(
				cellData -> cellData.getValue().lastNameProperty());
		
		// Очистка дополнительной информации об адресате.
		showPersonDetails(null);
		
		// Слушаем изменения выбора, и при изменении отображаем
		// дополнительную информацию об адресате.
		personTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showPersonDetails(newValue));
	}
	
	/**
	 * Вызывается главным приложением, которое даёт на себя ссылку.
	 *
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		// Добавление в таблицу данных из наблюдаемого списка
		personTable.setItems(mainApp.getPersonData());
	}
	
	/**
	 * Заполняет все текстовые поля, отображая подробности об адресате. Если указанный адресат = null, то все текстовые
	 * поля очищаются.
	 *
	 * @param person — адресат типа Person или null
	 */
	private void showPersonDetails(Person person) {
		if (person != null) {
			// Заполняем метки информацией из объекта person.
			firstNameLabel.setText(person.getFirstName());
			lastNameLabel.setText(person.getLastName());
			streetLabel.setText(person.getStreet());
			postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
			cityLabel.setText(person.getCity());
			
			
			birthdayLabel.setText(DateUtil.format(person.getBirthday()));
			
		} else {
			// Если Person = null, то убираем весь текст.
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			streetLabel.setText("");
			postalCodeLabel.setText("");
			cityLabel.setText("");
			birthdayLabel.setText("");
		}
	}
}