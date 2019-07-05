package br.lpv.evildungeons.controller;

import static br.lpv.evildungeons.tools.Constantes.BASE_PATH;
import static br.lpv.evildungeons.tools.Constantes.EVIL_DUNGEONS;
import static br.lpv.evildungeons.tools.Constantes.ICONE_SELECAO;
import static br.lpv.evildungeons.tools.Constantes.PATH_SOUND_HIT_SPELL;
import static br.lpv.evildungeons.tools.Constantes.PATH_SOUND_TAKE_WAND;
import static br.lpv.evildungeons.tools.Constantes.SOUND_OFF;
import static br.lpv.evildungeons.tools.Constantes.SOUND_ON;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import br.lpv.evildungeons.view.ChangeScreen;
import br.lpv.evildungeons.view.EnumScenes;
import br.lpv.evildungeons.view.OnChangeScreen;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class InicioController {
	@FXML private ImageView sound;
	@FXML private ImageView selecionarJogar;
	@FXML private ImageView selecionarAjuda;
	@FXML private JFXButton btnJogar;
	@FXML private JFXButton btnAjuda;

	private BorderPane bp;
	private ChangeScreen changeScreen;
	private AudioClip player;
	private Integer selecionado;
	private MediaPlayer soundEffect;
	
	
	@FXML
	protected void initialize() {
		ChangeScreen.addOnChangeScreenListener(new OnChangeScreen() {
			@Override
			public void onScreenChanged(EnumScenes screen, Object... dados) {
				inicializando(dados);
				verificaSom();
				
				if(selecionado == 1) {
					btnJogar.setFocusTraversable(false);
					btnAjuda.setFocusTraversable(true);
					selecionarJogar.setImage(null);
					selecionarAjuda.setImage(new Image(getClass().getResource(BASE_PATH+ICONE_SELECAO).toExternalForm()));
				}else 
					if(selecionado == 0){
						btnJogar.setFocusTraversable(true);
						btnAjuda.setFocusTraversable(false);
						selecionarJogar.setImage(new Image(getClass().getResource(BASE_PATH+ICONE_SELECAO).toExternalForm()));
						selecionarAjuda.setImage(null);
					}
			}
		});
	}
	
	public void verificaSom() {
		if(player.getVolume() == 1) {
			sound.setImage(new Image(getClass().getResource(BASE_PATH+SOUND_ON).toExternalForm()));
		}else {
			sound.setImage(new Image(getClass().getResource(BASE_PATH+SOUND_OFF).toExternalForm()));
		}		
	}

	@FXML
	public void onMouseClicked() {
		if(player.getVolume() == 1) {
			sound.setImage(new Image(getClass().getResource(BASE_PATH+SOUND_OFF).toExternalForm()));
			player.setVolume(0);
			player.stop();;
		}else {
			sound.setImage(new Image(getClass().getResource(BASE_PATH+SOUND_ON).toExternalForm()));
			player.setVolume(1);
			player.play();
		}
	}
	
	@FXML
	public void actionSelectOption(KeyEvent event) {
		Stage stage = (Stage) btnJogar.getScene().getWindow();
		
		if(!stage.getTitle().contains(EnumScenes.INICIO.getDescricao())) System.out.println("a");;
		
		switch (event.getCode()) {
		case UP:
			verificarOpcao();
			break;
		case DOWN:
			verificarOpcao();
			break;
		case M:
			onMouseClicked();
			break;
		case ENTER:
			selecionarOpcao();
			break;
			
		default:
			break;
		}
	}
	
	private void verificarOpcao() {
		soundEffect = new MediaPlayer(new Media(getClass().getResource(BASE_PATH+PATH_SOUND_TAKE_WAND).toExternalForm()));
		soundEffect.play();
		
		if(selecionado == 0) {
			selecionado = 1;
			selecionarJogar.setImage(null);
			selecionarAjuda.setImage(new Image(getClass().getResource(BASE_PATH+ICONE_SELECAO).toExternalForm()));
		}else {
			selecionado = 0;
			selecionarJogar.setImage(new Image(getClass().getResource(BASE_PATH+ICONE_SELECAO).toExternalForm()));
			selecionarAjuda.setImage(null);
		}
	}

	private void selecionarOpcao() {
		soundEffect = new MediaPlayer(new Media(getClass().getResource(BASE_PATH+PATH_SOUND_HIT_SPELL).toExternalForm()));
		soundEffect.play();
		
		if(selecionado == 0) {
			onActionJogo();
		}else 
			if (selecionado == 1){
				onActionAjuda();
			}
	}
	
	/**
	 * Solicita a mudança do <code>Scene</code> para o <code>Scene</code> do início.
	 */
	@FXML
	public void onActionInicio() {
		changeScreen(EnumScenes.INICIO, BASE_PATH+EnumScenes.INICIO.getPath(), EnumScenes.INICIO.getDescricao(), EnumScenes.INICIO.getWidth(), EnumScenes.INICIO.getHeight());
		changeScreen.changeScreen(EnumScenes.INICIO, bp, changeScreen, player, selecionado);
	}
	
	/**
	 * Solicita a mudança do <code>Scene</code> para o <code>Scene</code> do Jogo.
	 */
	@FXML
	public void onActionJogo() {
		changeScreen(EnumScenes.JOGO, BASE_PATH+EnumScenes.JOGO.getPath(), EnumScenes.JOGO.getDescricao(), EnumScenes.JOGO.getWidth(), EnumScenes.JOGO.getHeight());
		changeScreen.changeScreen(EnumScenes.JOGO, bp, changeScreen, player, selecionado);
	}
	
	/**
	 * Solicita a mudança do <code>Scene</code> para o <code>Scene</code> do início.
	 */
	@FXML
	public void onActionAjuda() {
		changeScreen(EnumScenes.AJUDA, BASE_PATH+EnumScenes.AJUDA.getPath(), EnumScenes.AJUDA.getDescricao(), EnumScenes.AJUDA.getWidth(), EnumScenes.AJUDA.getHeight());
		changeScreen.changeScreen(EnumScenes.AJUDA, bp, changeScreen, player, selecionado);
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

			bp.setCenter(null);
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
