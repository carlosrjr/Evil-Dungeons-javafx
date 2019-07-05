package br.lpv.evildungeons.view;

import java.util.ArrayList;
import java.util.List;

public class ChangeScreen {
	/**
	 * Notifica a todos os scenes da aplicação qual o Scene que está
	 * sendo utilizando atualmente e os parametros que foram passados para ele
	 * durante a troca do Scene.
	 * 
	 * @param scene <code>Scene</code> selecionado pelo usuário.
	 * @param dados Array de <code>Object</code> onde o usuário passará os parâmetros.
	 * necessários para o próximo <code>Scene</code>.
	 */
	public void changeScreen(EnumScenes scene, Object... dados) {
		switch (scene) {
		// Scene Incial
		case INICIO:
			/* 
			 * Notificando aos outros Scenes qual Scene está ativo e quais dados foram
			 * passados como parâmetro.
			 */
			notifyAllListeners(EnumScenes.INICIO, dados);
			break;

		case JOGO:
			/* 
			 * Notificando aos outros Scenes qual Scene está ativo e quais dados foram
			 * passados como parâmetro.
			 */
			notifyAllListeners(EnumScenes.JOGO, dados);
			break;
			
		case AJUDA:
			/* 
			 * Notificando aos outros Scenes qual Scene está ativo e quais dados foram
			 * passados como parâmetro.
			 */
			notifyAllListeners(EnumScenes.AJUDA, dados);
			break;
			
		case FIM_JOGO:
			/* 
			 * Notificando aos outros Scenes qual Scene está ativo e quais dados foram
			 * passados como parâmetro.
			 */
			notifyAllListeners(EnumScenes.FIM_JOGO, dados);
			break;
	
		case EVIL_DUNGEONS:
			/* 
			 * Notificando aos outros Scenes qual Scene está ativo e quais dados foram
			 * passados como parâmetro.
			 */
			notifyAllListeners(EnumScenes.EVIL_DUNGEONS, dados);
			break;
			
		default:
			break;
		}
	}
	
	/*
	 *  Lista de eventos de mudança de Scene do tipo da Interface OnChangeScreen para
	 *  ter controle sobre as janelas que estão sendo executadas e quais parâmetros
	 *  elas recebem.
	 */
	private static List<OnChangeScreen> listeners = new ArrayList<OnChangeScreen>();
	
	/**
	 * Adiciona a mudança de <code>Scene</code> a lista de eventos <code>OnChangeScreen</code>.
	 * 
	 * @param listener evento de mudança de tela.
	 */
	public static void addOnChangeScreenListener(OnChangeScreen listener) {
		listeners.clear();
		listeners.add(listener);
	}
	
	/**
	 * Notifica aos outros <code>Scene</code> qual <code>Scene</code> está em execução no
	 * momemto e os parâmetros que ele recebeu. 
	 * 
	 * @param screen <code>Scene</code> selecionado pelo usuário.
	 * @param dado Array de <code>Objecct</code> onde o usuário passará os parâmetros
	 * necessários para o próximo <code>Scene</code>.
	 */
	private static void notifyAllListeners(EnumScenes screen, Object... dado) {
		for (OnChangeScreen listener : listeners) {
			listener.onScreenChanged(screen, dado);
		}
	}
}
