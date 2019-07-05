package br.lpv.evildungeons.view;

import java.util.ArrayList;
import java.util.List;

public class ChangeScreen {
	/**
	 * Notifica a todos os scenes da aplica��o qual o Scene que est�
	 * sendo utilizando atualmente e os parametros que foram passados para ele
	 * durante a troca do Scene.
	 * 
	 * @param scene <code>Scene</code> selecionado pelo usu�rio.
	 * @param dados Array de <code>Object</code> onde o usu�rio passar� os par�metros.
	 * necess�rios para o pr�ximo <code>Scene</code>.
	 */
	public void changeScreen(EnumScenes scene, Object... dados) {
		switch (scene) {
		// Scene Incial
		case INICIO:
			/* 
			 * Notificando aos outros Scenes qual Scene est� ativo e quais dados foram
			 * passados como par�metro.
			 */
			notifyAllListeners(EnumScenes.INICIO, dados);
			break;

		case JOGO:
			/* 
			 * Notificando aos outros Scenes qual Scene est� ativo e quais dados foram
			 * passados como par�metro.
			 */
			notifyAllListeners(EnumScenes.JOGO, dados);
			break;
			
		case AJUDA:
			/* 
			 * Notificando aos outros Scenes qual Scene est� ativo e quais dados foram
			 * passados como par�metro.
			 */
			notifyAllListeners(EnumScenes.AJUDA, dados);
			break;
			
		case FIM_JOGO:
			/* 
			 * Notificando aos outros Scenes qual Scene est� ativo e quais dados foram
			 * passados como par�metro.
			 */
			notifyAllListeners(EnumScenes.FIM_JOGO, dados);
			break;
	
		case EVIL_DUNGEONS:
			/* 
			 * Notificando aos outros Scenes qual Scene est� ativo e quais dados foram
			 * passados como par�metro.
			 */
			notifyAllListeners(EnumScenes.EVIL_DUNGEONS, dados);
			break;
			
		default:
			break;
		}
	}
	
	/*
	 *  Lista de eventos de mudan�a de Scene do tipo da Interface OnChangeScreen para
	 *  ter controle sobre as janelas que est�o sendo executadas e quais par�metros
	 *  elas recebem.
	 */
	private static List<OnChangeScreen> listeners = new ArrayList<OnChangeScreen>();
	
	/**
	 * Adiciona a mudan�a de <code>Scene</code> a lista de eventos <code>OnChangeScreen</code>.
	 * 
	 * @param listener evento de mudan�a de tela.
	 */
	public static void addOnChangeScreenListener(OnChangeScreen listener) {
		listeners.clear();
		listeners.add(listener);
	}
	
	/**
	 * Notifica aos outros <code>Scene</code> qual <code>Scene</code> est� em execu��o no
	 * momemto e os par�metros que ele recebeu. 
	 * 
	 * @param screen <code>Scene</code> selecionado pelo usu�rio.
	 * @param dado Array de <code>Objecct</code> onde o usu�rio passar� os par�metros
	 * necess�rios para o pr�ximo <code>Scene</code>.
	 */
	private static void notifyAllListeners(EnumScenes screen, Object... dado) {
		for (OnChangeScreen listener : listeners) {
			listener.onScreenChanged(screen, dado);
		}
	}
}
