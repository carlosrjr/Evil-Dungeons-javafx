package br.lpv.evildungeons.view;

/**
 * Interface interna definida para especificar o <code>Scene</code> que será mostrado
 * no <code>Stage</code> e quais os dados serão passados como parâmetro para o novo
 * <code>Scene</code>.
 * 
 * @author Carlos Roberto Barbosa Júnior
 *
 * @version 0.1
 */
public interface OnChangeScreen {
	void onScreenChanged(EnumScenes screen, Object... dado);
}
