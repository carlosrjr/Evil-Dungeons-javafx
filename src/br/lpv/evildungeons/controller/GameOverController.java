package br.lpv.evildungeons.controller;

import static br.lpv.evildungeons.tools.Constantes.*;

import java.io.IOException;

import br.lpv.evildungeons.view.ChangeScreen;
import br.lpv.evildungeons.view.EnumScenes;
import br.lpv.evildungeons.view.OnChangeScreen;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GameOverController {
	@FXML private Label moedasColetadas;
	
	private ChangeScreen changeScreen;
	private BorderPane bp;
	private String moedas;
	private AudioClip player;
	private MediaPlayer gameoverSound;
	
	@FXML
	protected void initialize() {
		ChangeScreen.addOnChangeScreenListener(new OnChangeScreen() {
			@Override
			public void onScreenChanged(EnumScenes screen, Object... dados) {
				inicializando(dados);
				moedasColetadas.setText(moedas);
				player.stop();
				
				gameoverSound = new MediaPlayer(new Media(getClass().getResource(BASE_PATH+PATH_SOUND_GAME_OVER).toExternalForm()));
				gameoverSound.play();
			}
		});
	}
	
	/**
	 * Solicita a mudança do <code>Scene</code> para o <code>Scene</code> do início.
	 */
	@FXML
	public void onActionInicio() {
		gameoverSound.stop();
		player.play();
		changeScreen(EnumScenes.INICIO, BASE_PATH+EnumScenes.INICIO.getPath(), EnumScenes.INICIO.getDescricao(), EnumScenes.INICIO.getWidth(), EnumScenes.INICIO.getHeight());
		changeScreen.changeScreen(EnumScenes.INICIO, bp, changeScreen, player);
	}
	
	/**
	 * Solicita a mudança do <code>Scene</code> para o <code>Scene</code> do Jogo.
	 */
	@FXML
	public void onActionJogo() {
		gameoverSound.stop();
		player.play();
		changeScreen(EnumScenes.JOGO, BASE_PATH+EnumScenes.JOGO.getPath(), EnumScenes.JOGO.getDescricao(), EnumScenes.JOGO.getWidth(), EnumScenes.JOGO.getHeight());
		changeScreen.changeScreen(EnumScenes.JOGO, bp, changeScreen, player);
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
			//stage.setWidth(width);
			//stage.setHeight(height);

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
	
	private void inicializando(Object... dados) {
		for (Object dado : dados) {
			if(dado instanceof BorderPane) bp = (BorderPane)dado;
			else if(dado instanceof ChangeScreen) changeScreen = (ChangeScreen)dado;
			else if(dado instanceof String) moedas = (String)dado;
			else if(dado instanceof AudioClip) player = (AudioClip)dado;
		}
	}
}
