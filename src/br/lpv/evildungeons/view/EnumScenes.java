package br.lpv.evildungeons.view;

import static br.lpv.evildungeons.tools.Constantes.*;

/**
 * Gerência os <code>Scene</code> da aplicação.
 * 
 * @author Carlos Roberto Barbosa Júnior
 *
 * @version 0.1
 */
public enum EnumScenes {
	// Scenes
	EVIL_DUNGEONS("Evil Dungeons", "view/fxml/EvilDungeons.fxml",  WIDTH_STAGE, HEIGHT_STAGE),
	JOGO("Jogo", "view/fxml/Jogo.fxml",  WIDTH_STAGE+350, HEIGHT_STAGE+350),
	AJUDA("Ajuda", "view/fxml/Ajuda.fxml",  WIDTH_STAGE+220, HEIGHT_STAGE-100),
	FIM_JOGO("Fim de Jogo", "view/fxml/GameOver.fxml",  WIDTH_STAGE+220, HEIGHT_STAGE-20),
	INICIO("Inicio", "view/fxml/Inicio.fxml", WIDTH_SCENE, HEIGHT_SCENE);

	// Declaração dos campos do Enum
	private String descricao;
	private String path;
	private double width;
	private double height;
	
	/**
	 * Contrutor do Enum.
	 * 
	 * @param descricao 
	 */
	private EnumScenes(String descricao, String path, double width, double height) {
		this.descricao = descricao;
		this.path = path;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Obtém a descrição do <code>AnchorPane</code>.
	 * 
	 * @return retorna a descrição do Scene.
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/**
	 * Obtém o path do <code>AnchorPane</code>.
	 * 
	 * @return retorna o path do <code>AnchorPane</code>.
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * Obtém a largura do <code>AnchorPane</code>.
	 * 
	 * @return retorna a largura do <code>AnchorPane</code>.
	 */
	public double getWidth() {
		return width;
	}
	
	/**
	 * Obtém a altura do <code>AnchorPane</code>.
	 * 
	 * @return retorna a algura do <code>AnchorPane</code> .
	 */
	public double getHeight() {
		return height;
	}
}
