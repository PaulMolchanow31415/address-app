module com.example.demoproject {
	requires javafx.controls;
	requires javafx.fxml;
	requires com.google.gson;
	
	opens com.example.demoproject to javafx.fxml;
	exports com.example.demoproject;
	exports com.example.demoproject.controller;
	opens com.example.demoproject.controller to javafx.fxml;
	opens com.example.demoproject.model;
}