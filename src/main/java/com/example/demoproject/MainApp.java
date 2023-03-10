package com.example.demoproject;

import com.example.demoproject.controller.PersonEditDialogController;
import com.example.demoproject.controller.PersonOverviewController;
import com.example.demoproject.controller.RootLayoutController;
import com.example.demoproject.model.Person;
import com.example.demoproject.model.PersonAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class MainApp extends Application {

	private final Path PERSON_DATA_PATH = Paths.get("src/main/resources/com/example/demoproject/json/temp.json");

	private Stage primaryStage;
	private BorderPane rootLayout;
	private final ObservableList<Person> personData = FXCollections.observableArrayList();

	public MainApp() {
		// В качестве образца добавляем некоторые данные
		personData.add(new Person("Paul", "Molchanov"));
		personData.add(new Person("Nikita", "Meshkov"));
		personData.add(new Person("Alex", "Positive"));
		personData.add(new Person("Dimon", "Negative"));
		personData.add(new Person("Sergey", "Pavlov"));
		personData.add(new Person("Анастасия", "Реснянская"));
		personData.add(new Person("Владимир", "Ростовцев"));
		personData.add(new Person("Артур", "Сарян"));
		personData.add(new Person("Вадим", "Федоров"));
	}

	public ObservableList<Person> getPersonData() {
		return personData;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Список адресатов");
		this.primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/app-icon.png"))));

		initRootLayout();
		showPersonOverview();
	}

	public void initRootLayout() {
		try {
			// Загружаем корневой макет из fxml файла.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = loader.load();

			RootLayoutController rootLayoutController = loader.getController();
			rootLayoutController.setMainApp(this);

			// Отображаем сцену, содержащую корневой макет.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Показывает в корневом макете сведения об адресатах.
	 */
	public void showPersonOverview() {
		try {
			// Загружаем сведения об адресатах.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
			AnchorPane personOverview = loader.load();

			// Помещаем сведения об адресатах в центр корневого макета.
			rootLayout.setCenter(personOverview);

			// Даём контроллеру доступ к главному приложению.
			PersonOverviewController personController = loader.getController();
			personController.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Открывает диалоговое окно для изменения деталей указанного адресата.
	 * Если пользователь кликнул OK, то изменения сохраняются в предоставленном
	 * объекте адресата и возвращается значение true.
	 *
	 * @param person объект адресата, который надо изменить
	 * @return true, если пользователь кликнул OK, в противном случае false.
	 */
	public boolean showPersonEditDialog(Person person) {
		try {
			// Загружаем fxml-файл и создаём новую сцену
			// для всплывающего диалогового окна.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
			AnchorPane page = loader.load();

			// Создаём диалоговое окно Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Данные о человеке");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Передаём адресата в контроллер.
			PersonEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPerson(person);

			// Отображаем диалоговое окно и ждём, пока пользователь его не закроет
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Возвращает главную сцену
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	/**
	 * записывает текущий объект personData в json файл
	 */
	public void serializePersons() {
		try (Writer writer = Files.newBufferedWriter(PERSON_DATA_PATH, StandardCharsets.UTF_8)) {

			Gson gson = new GsonBuilder()
					.setPrettyPrinting()
					.registerTypeAdapter(Person.class, new PersonAdapter())
					.excludeFieldsWithoutExposeAnnotation()
					.create();

			gson.toJson(personData, writer);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deserializePersons(File selectedFile) {
		try (Reader reader = Files.newBufferedReader(selectedFile.toPath(), StandardCharsets.UTF_8)) {

			GsonBuilder builder = new GsonBuilder();
			builder.registerTypeAdapter(Person.class, new PersonAdapter());
			Gson gson = builder.create();

			personData.clear();

			JsonArray jsonElements = gson.fromJson(reader, JsonArray.class);

			for (JsonElement element : jsonElements){
				Person person = gson.fromJson(element, Person.class);
				personData.add(person);
			}
// C:\Users\USER\Desktop\JAVA_FX\TEST\demoProject\src\main\resources\com\example\demoproject\json\temp.json
		} catch (Exception e) {
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
			errorAlert.setTitle("Неправильный формат записи данных");
			errorAlert.setHeaderText("Выберите другой файл json");
			errorAlert.setContentText("Из-за неправильного формата файла у вас возникла ошибка");
			errorAlert.showAndWait();
		}
	}

	public void clearPersons() {
		try (FileWriter fileWriter = new FileWriter(PERSON_DATA_PATH.toFile(), false)) {
			personData.clear();
			fileWriter.write("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}