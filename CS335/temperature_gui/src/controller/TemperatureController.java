package controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.TemperatureModel;
import view.TemperatureView;

public class TemperatureController extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Temperature Converter");
		BorderPane bp = new BorderPane();
		Scene scene = new Scene(bp, 500, 500);
		TemperatureModel tm = new TemperatureModel();
		TemperatureView value = new TemperatureView(tm);
		bp.setCenter(value);
		stage.setScene(scene);
		stage.show();
	}

}
