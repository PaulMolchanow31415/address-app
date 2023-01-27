package com.example.demoproject.controller;

import com.example.demoproject.MainApp;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;

public class RootLayoutController {
	// Ссылка на главное приложение.
	private MainApp mainApp;
	
	public RootLayoutController() {}
	@FXML
	private void initialize() {}
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	@FXML
	public void exitPlatform() {
		Platform.exit();
	}
	// https://attacomsian.com/blog/gson-read-write-json#convert-list-of-java-objects-to-json-array
	// TODO: открытие / закрытие файла
	public void openFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Выберите файл с данными людей");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("все файлы json","*.json"));

		File selectedFile = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
		if (selectedFile != null)
			mainApp.deserializePersons(selectedFile);
	}
	public void saveFile() {
		mainApp.serializePersons();
	}
	public void clearFile() {
		mainApp.clearPersons();
	}
}
