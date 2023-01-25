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
	public void openFile() {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON файлы (*.json)", "*.json");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
		System.out.println(file);
	}
	public void saveFile() {
		mainApp.writeJson();
	}
	public void clearFile() {
		mainApp.writeJson("");
	}
	public void openSite() {
		try {
			new ProcessBuilder("x-www-browser", "https://stackoverflow.com").start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
