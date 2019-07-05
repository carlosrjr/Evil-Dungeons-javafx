package br.lpv.evildungeons.controller;

import static br.lpv.evildungeons.tools.Constantes.*;

import java.io.IOException;

import br.lpv.evildungeons.view.ChangeScreen;
import br.lpv.evildungeons.view.EnumScenes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class EvilDugeonsController {
	@FXML private BorderPane bp;
	private ChangeScreen changeScreen;
	private AudioClip player;
	
	@FXML
	protected void initialize() {
		changeScreen = new ChangeScreen();
		
		player = new AudioClip(getClass().getResource(BASE_PATH+PATH_SOUND_DUNGEON).toExternalForm());
		
		player.setCycleCount(MediaPlayer.INDEFINITE);
		
		player.play();
		
	}
	
	/**
	 * Solicita a mudança do <code>Scene</code> para o <code>Scene</code> do início.
	 */
	@FXML
	public void onActionInicio() {
		changeScreen(EnumScenes.INICIO, BASE_PATH+EnumScenes.INICIO.getPath(), EnumScenes.INICIO.getDescricao(), EnumScenes.INICIO.getWidth(), EnumScenes.INICIO.getHeight());
		changeScreen.changeScreen(EnumScenes.INICIO, bp, changeScreen, player);
	}
	
	/**
	 * Solicita a mudança do <code>Scene</code> para o <code>Scene</code> do Jogo.
	 */
	@FXML
	public void onActionJogo() {
		changeScreen(EnumScenes.JOGO, BASE_PATH+EnumScenes.JOGO.getPath(), EnumScenes.JOGO.getDescricao(), EnumScenes.JOGO.getWidth(), EnumScenes.JOGO.getHeight());
		changeScreen.changeScreen(EnumScenes.JOGO, bp, changeScreen, player);
	}
	
	/**
	 * Solicita a mudança do <code>Scene</code> para o <code>Scene</code> do início.
	 */
	@FXML
	public void onActionAjuda() {
		changeScreen(EnumScenes.AJUDA, BASE_PATH+EnumScenes.AJUDA.getPath(), EnumScenes.AJUDA.getDescricao(), EnumScenes.AJUDA.getWidth(), EnumScenes.AJUDA.getHeight());
		changeScreen.changeScreen(EnumScenes.AJUDA, bp, changeScreen, player);
	}

	
	/**
	 * Alterna entre as janelas de acordo com a janela selecionada pelo usuário.
	 * 
	 * @param scene um EnumScenes com a nova <code>Scene</code>.
	 * @param titulo titulo do <code>Stage</code>.
	 * @param path caminho do arquivo fxml.
	 * @param width largura do <code>Stage</code>.
	 * @param height altura do <code>Stage</code>.
	 * 
	 */
	public void changeScreen(EnumScenes scene, String path, String titulo, double width, double height) {
		try {
			
			// Obtendo o loader.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(path));

			// Obtendo o stage e setando as novas dimensões.
			Stage stage = (Stage)bp.getScene().getWindow();
			//stage.setMinWidth(width);
			//stage.setMinHeight(height);

			// Carregando o loader no Panel.
			Pane pane = loader.load();
			
			stage.setTitle(String.format("%s (%s)", EVIL_DUNGEONS, titulo));

			bp.setCenter(pane);
			
			// Centralizando o Stage.
			stage.centerOnScreen();
			Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
			stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
			stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
