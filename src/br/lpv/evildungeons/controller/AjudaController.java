package br.lpv.evildungeons.controller;

import static br.lpv.evildungeons.tools.Constantes.BASE_PATH;
import static br.lpv.evildungeons.tools.Constantes.EVIL_DUNGEONS;
import static br.lpv.evildungeons.tools.Constantes.PATH_SOUND_HIT_SPELL;

import java.io.IOException;

import br.lpv.evildungeons.view.ChangeScreen;
import br.lpv.evildungeons.view.EnumScenes;
import br.lpv.evildungeons.view.OnChangeScreen;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class AjudaController {
	private ChangeScreen changeScreen;
	private BorderPane bp;
	private AudioClip player;
	private AudioClip soundEffect;
	private Integer selecionado;
	
	@FXML
	protected void initialize() {
		ChangeScreen.addOnChangeScreenListener(new OnChangeScreen() {
			@Override
			public void onScreenChanged(EnumScenes screen, Object... dados) {
				inicializando(dados);
			}
		});
	}
	
	@FXML
	public void onKeyPressed(KeyEvent event) {
		switch (event.getCode()) {
		case BACK_SPACE:
			selecionarOpcao();
			break;

		default:
			break;
		}
	}
	
	private void selecionarOpcao() {
		soundEffect = new AudioClip((getClass().getResource(BASE_PATH+PATH_SOUND_HIT_SPELL).toExternalForm()));
		soundEffect.play();
		onActionInicio();
	}

	/**
	 * Solicita a mudança do <code>Scene</code> para o <code>Scene</code> do início.
	 */
	@FXML
	public void onActionInicio() {
		selecionado = 1;
		changeScreen(EnumScenes.INICIO, BASE_PATH+EnumScenes.INICIO.getPath(), EnumScenes.INICIO.getDescricao(), EnumScenes.INICIO.getWidth(), EnumScenes.INICIO.getHeight());
		changeScreen.changeScreen(EnumScenes.INICIO, bp, changeScreen, player, selecionado);
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
			else if(dado instanceof AudioClip) player = (AudioClip)dado;
			else if(dado instanceof Integer) selecionado = (Integer)dado;
		}
	}
}
