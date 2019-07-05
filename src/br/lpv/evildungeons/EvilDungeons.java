package br.lpv.evildungeons;
	
import javafx.application.Application;
import javafx.stage.Stage;

public class EvilDungeons extends Application {
	@Override
	public void start(Stage primaryStage) {
		new FxEvilDungeons(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}