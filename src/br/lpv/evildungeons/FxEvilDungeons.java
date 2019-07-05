package br.lpv.evildungeons;

import static br.lpv.evildungeons.tools.Constantes.*;

import br.lpv.evildungeons.view.EnumScenes;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FxEvilDungeons {

	public FxEvilDungeons(Stage evilDungeonsStage) {
		fxEvilDungeons(evilDungeonsStage);
	}

	public void fxEvilDungeons(Stage evilDungeonsStage) {
		try {
			Scene avaliacaoInstitucionalScene;
			//stage = evilDungeonsStage;
			
			// Definindo componente de layout do Scene e setando o fxml.
			BorderPane fxmlEvilDungeons = (BorderPane)FXMLLoader.load(getClass().getResource(BASE_PATH+EnumScenes.EVIL_DUNGEONS.getPath()));
			
			// Definindo Scenes do programa.
			avaliacaoInstitucionalScene = new Scene(fxmlEvilDungeons, EnumScenes.EVIL_DUNGEONS.getWidth(), EnumScenes.EVIL_DUNGEONS.getHeight());

			// Setando CSS dos Scenes.
			avaliacaoInstitucionalScene.getStylesheets().add(getClass().getResource(BASE_PATH+PATH_CSS).toExternalForm());
			
			// Setando Scene ao Stage principal.
			evilDungeonsStage.setScene(avaliacaoInstitucionalScene);
			
			// Setando título da janela principal.
			evilDungeonsStage.setTitle(EVIL_DUNGEONS);
			
			// Definindo Icone da barra de título do Stage.
			evilDungeonsStage.getIcons().add(new Image(getClass().getResource(BASE_PATH+ICONE_APP).toExternalForm()));
			
			// Definindo que o Stage principal não pode ser Redimensionado.
			//evilDungeonsStage.setResizable(false);
			
			// Centralizando o Stage.
			evilDungeonsStage.centerOnScreen();
			
			evilDungeonsStage.setMinWidth(EnumScenes.JOGO.getWidth());
			evilDungeonsStage.setMinHeight(EnumScenes.JOGO.getHeight());
			
			// Exibindo Stage principal.
			evilDungeonsStage.show();
			
			// Define o Scene da página incial.
			//fxmlEvilDungeons.setCenter(FXMLLoader.load(getClass().getResource(BASE_PATH+EnumScenes.EVIL_DUNGEONS.getPath())));

			// Alinhando Stage no centro da tela.
			Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
			evilDungeonsStage.setX((primScreenBounds.getWidth() - avaliacaoInstitucionalScene.getWidth()) / 2);
			evilDungeonsStage.setY((primScreenBounds.getHeight() - avaliacaoInstitucionalScene.getHeight()) / 2);
			
			evilDungeonsStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					System.exit(0);
				}
			});
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
