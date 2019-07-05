package br.lpv.evildungeons.view;

/**
 * Interface interna definida para especificar o <code>Scene</code> que ser� mostrado
 * no <code>Stage</code> e quais os dados ser�o passados como par�metro para o novo
 * <code>Scene</code>.
 * 
 * @author Carlos Roberto Barbosa J�nior
 *
 * @version 0.1
 */
public interface OnChangeScreen {
	void onScreenChanged(EnumScenes screen, Object... dado);
}
