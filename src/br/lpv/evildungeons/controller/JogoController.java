package br.lpv.evildungeons.controller;

import static br.lpv.evildungeons.tools.Constantes.*;

import java.io.IOException;

import br.lpv.evildungeons.model.Card;
import br.lpv.evildungeons.model.Hero;
import br.lpv.evildungeons.model.Humanoid;
import br.lpv.evildungeons.model.Item;
import br.lpv.evildungeons.view.ChangeScreen;
import br.lpv.evildungeons.view.EnumScenes;
import br.lpv.evildungeons.view.OnChangeScreen;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class JogoController {
	@FXML private GridPane gp;

    @FXML private Pane painel01;
    @FXML private Pane painel02;
    @FXML private Pane painel00;

    @FXML private Pane painel20;
    @FXML private Pane painel21;
    @FXML private Pane painel22;

    @FXML private Pane painel12;
    @FXML private Pane painel10;
    @FXML private Pane painel11;

    @FXML private Label lblCoin;
    
	private BorderPane bp;
	private ChangeScreen changeScreen;
	
	private Card[][] cards = new Card[3][3];
	private Pane[][] paineis = new Pane[3][3];
	private int[] posicaoHeroi = {1,1};
	private int numeroMonstro;
	private int numeroPocoes;
	private int numeroArmas;
	private int rodadas;
	
	private AudioClip player;
	
	private MediaPlayer soundEvent;
	
	// Valor moeda
	private int[] dropCoin = {1, 1, 1, 1, 1, 1, 3, 3, 7, 3, 3, 3, 1, 6, 1, 1, 1, 3, 3, 3, 6, 6, 7, 10, 12};

	// HP monstros
	//private int[] hpEnemy = {3, 3, 7, 3, 3, 7, 3, 2, 6, 9, 8, 2, 4, 2, 2, 2, 9, 8, 3, 3, 3, 6, 6, 7, 10, 12, 10, 12, 15, 25, 30};
	private int[] hpEnemy = {3,4,5};
	//private int[] hpEnemy = {1};

	// Poder de ataque das armas
	private int[] wandAttack = {3,5,7,9,12};
	private int[] sword1Attack = {8,10,12};
	private int[] sword2Attack = {5,6,7,8};
	private int[] sword3Attack = {4,5};
	private int[] sword4Attack = {3,4};
	
	// Poder de cura ou dano das poçoes.
	private int[] potionPower = {3,5};

	/*
	 * 1 - UP
	 * 2 - RIGHT
	 * 3 - DOWN
	 * 4 - LEFT
	 */
	private int movimento;
	private Hero hero;
	
	@FXML
	protected void initialize() {
		ChangeScreen.addOnChangeScreenListener(new OnChangeScreen() {
			@Override
			public void onScreenChanged(EnumScenes screen, Object... dados) {
				inicializando(dados);
				
				paineis[0][0] = painel00;
				paineis[0][1] = painel10;
				paineis[0][2] = painel20;
				
				paineis[1][0] = painel01;
				paineis[1][1] = painel11;
				paineis[1][2] = painel21;
				
				paineis[2][0] = painel02;
				paineis[2][1] = painel12;
				paineis[2][2] = painel22;
				
				for (int i = 0; i < Card.MAX_POS; i++) {
					for (int j = 0; j < Card.MAX_POS; j++) {
						paineis[i][j].getStyleClass().add(ID_CARD);
					}
				}
				
				hero = new Hero(HERO, BASE_PATH+SPRITE_HERO, posicaoHeroi[0], posicaoHeroi[1], Hero.DEFAULT_HEALTH, 0); 
				
				paineis[hero.getX()][hero.getY()].getStyleClass().remove(ID_CARD);
				paineis[hero.getX()][hero.getY()].getStyleClass().add(ID_HERO);

				hero.setItemAtaque(new Item(SWORD1, BASE_PATH+SPRITE_SWORD1, 2, 1, SWORD, 5));
				
				cards[hero.getX()][hero.getY()] = hero;
				
				cards[0][0] = gerarCard(0, 0);
				cards[0][1] = gerarCard(0, 1);
				cards[0][2] = gerarCard(0, 2);

				cards[1][0] = gerarCard(1, 0);
				cards[1][2] = gerarCard(1, 2);
				
				cards[2][0] = gerarCard(2, 0);
				cards[2][1] = gerarCard(2, 1);
				cards[2][2] = gerarCard(2, 2);
				
				exibirCard(cards[0][0]);
				exibirCard(cards[0][1]);
				exibirCard(cards[0][2]);

				exibirCard(cards[1][0]);
				exibirCard(cards[1][1]);
				exibirCard(cards[1][2]);
				
				exibirCard(cards[2][0]);
				exibirCard(cards[2][1]);
				exibirCard(cards[2][2]);
			}
		});
		
		numeroMonstro = 0;
		numeroPocoes = 0;
		numeroArmas = 0;
		rodadas = 0;
	}

	@FXML
	public void onKeyPressed(KeyEvent event) {
		switch (event.getCode()) {
		case UP:
			moveUp();
			break;
		case DOWN:
			moveDown();
			break;
		case LEFT:
			moveLeft();
			break;
		case RIGHT:
			moveRight();
			break;

		default:
			break;
		}
		
		verificaMovimento();
	}

	private int[] moverHeroi(int x, int y) {
		//vai para nova posição
		posicaoHeroi[0] = posicaoHeroi[0] + x;
		posicaoHeroi[1] = posicaoHeroi[1] + y;
		
		//retorna a nova posicao
		return posicaoHeroi;
	}
	
	private int[] moveUp() {
		movimento = 1;
		if(posicaoHeroi[1] < 1)
			return posicaoHeroi;
		return moverHeroi(0,-1);
	}
	
	private int[] moveDown() {
		movimento = 3;
		if(posicaoHeroi[1] > 1)
			return posicaoHeroi;
		return moverHeroi(0,1);
	}
	
	private int[] moveLeft() {
		movimento = 4;
		if(posicaoHeroi[0] < 1)
			return posicaoHeroi;
		return moverHeroi(-1,0);
	}
	
	private int[] moveRight() {
		movimento = 2;
		if(posicaoHeroi[0] > 1)
			return posicaoHeroi;
		return moverHeroi(1, 0);
	}
	
	private void seguir() {
		System.out.println(rodadas);
		if(hero.getX() == 0 && hero.getY() == 0) {
			if(movimento == 2) {
				cards[0][2].setPosicao(0, 1);
				cards[0][1].setPosicao(0, 0);
				
				cards[0][0] = cards[0][1];
				cards[0][1] = cards[0][2];
				
				cards[0][2] = gerarCard(0, 2);
				
				exibirCard(cards[0][0]);
				exibirCard(cards[0][1]);
				exibirCard(cards[0][2]);
			}else {
				cards[2][0].setPosicao(1, 0);
				cards[1][0].setPosicao(0, 0);
				
				cards[0][0] = cards[1][0];
				cards[1][0] = cards[2][0];
				
				cards[2][0] = gerarCard(2, 0);
				
				exibirCard(cards[2][0]);
				exibirCard(cards[1][0]);
				exibirCard(cards[0][0]);
			}
		}else
			if(hero.getX() == 0 && hero.getY() == 1) {
				if(movimento == 1) {
					cards[0][2].setPosicao(0, 1);
					cards[0][1].setPosicao(0, 0);
					
					cards[0][1] = cards[0][2];
					
					cards[0][2] = gerarCard(0, 2);
					
					exibirCard(cards[0][2]);
					exibirCard(cards[0][1]);
				}else
					if(movimento == 2) {
						cards[0][1] = gerarCard(0, 1);
						exibirCard(cards[0][1]);
					} else {
						cards[0][0].setPosicao(0, 1);
						
						cards[0][1] = cards[0][0];
						
						cards[0][0] = gerarCard(0, 0);
						
						exibirCard(cards[0][0]);
						exibirCard(cards[0][1]);
					}
			}else
				if(hero.getX() == 0 && hero.getY() == 2) {
					if(movimento == 1) {
						cards[2][2].setPosicao(1, 2);
						cards[1][2].setPosicao(0, 2);
						
						cards[0][2] = cards[1][2];
						cards[1][2] = cards[2][2];
						
						cards[2][2] = gerarCard(2, 2);
						
						exibirCard(cards[2][2]);
						exibirCard(cards[1][2]);
						exibirCard(cards[0][2]);
					} else {
						cards[0][0].setPosicao(0, 1);
						cards[0][1].setPosicao(0, 2);
						
						cards[0][2] = cards[0][1];
						cards[0][1] = cards[0][0];
						
						cards[0][0] = gerarCard(0, 0);
						
						exibirCard(cards[0][0]);
						exibirCard(cards[0][1]);
						exibirCard(cards[0][2]);
					}
				}else
					if(hero.getX() == 1 && hero.getY() == 0) {
						if(movimento == 2) {
							cards[0][0].setPosicao(1, 0);
							
							cards[1][0] = cards[0][0];
							
							cards[0][0] = gerarCard(0, 0);
							
							exibirCard(cards[0][0]);
							exibirCard(cards[1][0]);
						}else
							if(movimento == 3) {
								cards[1][0] = gerarCard(1, 0);
								exibirCard(cards[1][0]);
							}else {
								cards[2][0].setPosicao(1, 0);
								
								cards[1][0] = cards[2][0];
								
								cards[2][0] = gerarCard(2, 0);
								
								exibirCard(cards[2][0]);
								exibirCard(cards[1][0]);
							}
					}else
						if(hero.getX() == 1 && hero.getY() == 1) {
							if(movimento == 1) {
								cards[1][2].setPosicao(1, 1);
								
								cards[1][1] = cards[1][2];
								
								cards[1][2] = gerarCard(1, 2);;
								
								exibirCard(cards[1][2]);
								exibirCard(cards[1][1]);
							}else
								if(movimento == 2) {
									cards[0][1].setPosicao(1, 1);
									
									cards[1][1] = cards[0][1];
									
									cards[0][1] = gerarCard(0, 1);
									
									exibirCard(cards[0][1]);
									exibirCard(cards[1][1]);
								}else 
									if(movimento == 3) {
										cards[1][0].setPosicao(1, 1);
										
										cards[1][1] = cards[1][0];
										
										cards[1][0] = gerarCard(1, 0);
										
										exibirCard(cards[1][0]);
										exibirCard(cards[1][1]);
									}else {
										cards[2][1].setPosicao(1, 1);
										
										cards[1][1] = cards[2][1];
										
										cards[2][1] = gerarCard(2, 1);
										
										exibirCard(cards[2][1]);
										exibirCard(cards[1][1]);
									}
						}else
							if(hero.getX() == 1 && hero.getY() == 2) {
								if(movimento == 1) {
									cards[1][2] = gerarCard(1, 2);
									exibirCard(cards[1][2]);
								}else
									if(movimento == 2) {
										cards[0][2].setPosicao(1, 2);
										cards[1][2] = cards[0][2];
										
										cards[0][2] = gerarCard(0, 2);
										
										exibirCard(cards[0][2]);
										exibirCard(cards[1][2]);
									} else {
										cards[2][2].setPosicao(1, 2);
										cards[1][2] = cards[2][2];
										
										cards[2][2] = gerarCard(2, 2);
										
										exibirCard(cards[2][2]);
										exibirCard(cards[1][2]);
									}
							}else
								if(hero.getX() == 2 && hero.getY() == 0) {
									if(movimento == 3) {
										cards[0][0].setPosicao(1, 0);
										cards[1][0].setPosicao(2, 0);
										
										cards[2][0] = cards[1][0];
										cards[1][0] = cards[0][0];
										
										cards[0][0] = gerarCard(0, 0);
										
										exibirCard(cards[2][0]);
										exibirCard(cards[1][0]);
										exibirCard(cards[0][0]);
									}else {
										cards[2][2].setPosicao(2, 1);
										cards[2][1].setPosicao(2, 0);
										
										cards[2][0] = cards[2][1];
										cards[2][1] = cards[2][2];
										
										cards[2][2] = gerarCard(2, 2);
										
										exibirCard(cards[2][0]);
										exibirCard(cards[2][1]);
										exibirCard(cards[2][2]);
									}
								}else
									// Dirita meio 
									if(hero.getX() == 2 && hero.getY() == 1) {
										if(movimento == 1) { // Move para cima
											cards[2][2].setPosicao(2, 1);
											cards[2][1].setPosicao(2, 0);
											
											cards[2][1] = cards[2][2];
											
											cards[2][2] = gerarCard(2, 2);
											
											exibirCard(cards[2][2]);
											exibirCard(cards[2][1]);
										}else
											if(movimento == 3) { // move para baixo.
												cards[2][0].setPosicao(2, 1);
												
												cards[2][1] = cards[2][0];
												
												cards[2][0] = gerarCard(2, 0);
												
												exibirCard(cards[2][0]);
												exibirCard(cards[2][1]);
											} else {
												// move para direito
												cards[2][1] = gerarItem(2, 1);
												exibirCard(cards[2][1]);
											}
									}else
										// Canto Inferior Direito
										if(hero.getX() == 2 && hero.getY() == 2) {
											if(movimento == 1) { // Move para cima
												cards[0][2].setPosicao(1, 2);
												cards[1][2].setPosicao(2, 2);
												
												cards[2][2] = cards[1][2];
												cards[1][2] = cards[0][2];
												
												cards[0][2] = gerarCard(0, 2);
												
												exibirCard(cards[0][2]);
												exibirCard(cards[1][2]);
												exibirCard(cards[2][2]);
											}else {// Move para esquerda
												cards[2][0].setPosicao(2, 1);
												cards[2][1].setPosicao(2, 2);
												
												cards[2][2] = cards[2][1];
												cards[2][1] = cards[2][0];
												
												cards[2][0] = gerarCard(2, 0);
												
												exibirCard(cards[2][0]);
												exibirCard(cards[2][1]);
												exibirCard(cards[2][2]);
											}
										}
		hero.setPosicao(posicaoHeroi[0], posicaoHeroi[1]);
	
		exibirCard(hero);
	}
	
	private Card gerarCard(int x, int y) {
		/*
		 * 0 - Item
		 * 1 - Humanoid
		 */
		int tipoCard = (int)(Math.random()*2);
		
		switch (tipoCard) {
		case 0:
			return gerarItem(x, y);
		case 1:
			if(numeroMonstro < MAXIMO_MONSTROS) {
				return gerarHumanoid(x, y);
			} if(numeroPocoes == MAXIMO_POCOES && numeroArmas == MAXIMO_ARMAS){
				return gerarMoeda(x, y);
			}else {
				return gerarItem(x, y);
			}
			
		default:
			return gerarItem(x, y);
		}
	}
	
	private Card gerarItem(int x, int y) {
		/*
		 * 0 - espada
		 * 1 - varinha
		 * 2 - poção
		 * 3 - moeda
		 */
		int tipoItem = (int)(Math.random()*3);
		
		switch (tipoItem) {
		case 0:
			return gerarEspada(x, y);
			
		case 1:
			return gerarVarinha(x, y);
			
		case 2:
			return gerarPocao(x, y);
		
		case 3:
			return gerarMoeda(x,y);

		default:
			return new Item(COIN, BASE_PATH+SPRITE_COIN, x, y, GOLD, 1);
		}
	}

	private Card gerarEspada(int x, int y) {
		/*
		 * 0 - Espada 1
		 * 1 - Espada 2
		 * 2 - Espada 3
		 * 3 - Espada 4
		 */
		int gerarEspada = (int)(Math.random()*4);
		if(numeroArmas < MAXIMO_ARMAS) {
			numeroArmas++;
			
			switch (gerarEspada) {
			case 0:
				return new Item(SWORD1, BASE_PATH+SPRITE_SWORD1, x, y, SWORD, sword1Attack[(int)(Math.random()*(sword1Attack.length-1))]);
			case 1:
				return new Item(SWORD2, BASE_PATH+SPRITE_SWORD2, x, y, SWORD, sword2Attack[(int)(Math.random()*(sword2Attack.length-1))]);
			case 2:
				return new Item(SWORD3, BASE_PATH+SPRITE_SWORD3, x, y, SWORD, sword3Attack[(int)(Math.random()*(sword3Attack.length-1))]);
			case 3:
				return new Item(SWORD4, BASE_PATH+SPRITE_SWORD4, x, y, SWORD, sword4Attack[(int)(Math.random()*(sword4Attack.length-1))]);
				
			default:
				return new Item(SWORD2, BASE_PATH+SPRITE_SWORD2, x, y, SWORD, wandAttack[(int)(Math.random()*(wandAttack.length-1))]);
			}
		}else {
			if(numeroMonstro < MAXIMO_MONSTROS) {
				return gerarHumanoid(x, y);
			}else {
				return gerarMoeda(x, y);
			}
				
		}
	}

	private Card gerarVarinha(int x, int y) {
		/*
		 * 0 - Varinha de fogo
		 * 1 - Varinha de gelo
		 * 2 - Varinha de veneno
		 */
		int gerarVarinha = (int)(Math.random()*3);
		
		if(numeroArmas < MAXIMO_ARMAS) {
			numeroArmas++;
			
			switch (gerarVarinha) {
			case 0:
				return new Item(WAND_FIRE, BASE_PATH+SPRITE_WAND_FIRE, x, y, WAND, wandAttack[(int)(Math.random()*(wandAttack.length-1))]);
			case 1:
				return new Item(WAND_ICE, BASE_PATH+SPRITE_WAND_ICE, x, y, WAND, wandAttack[(int)(Math.random()*(wandAttack.length-1))]);
			case 2:
				return new Item(WAND_POISON, BASE_PATH+SPRITE_WAND_POISON, x, y, WAND, wandAttack[(int)(Math.random()*(wandAttack.length-1))]);
				
			default:
				return new Item(WAND_ICE, BASE_PATH+SPRITE_WAND_ICE, x, y, WAND, wandAttack[(int)(Math.random()*(wandAttack.length-1))]);
			}
		}else {
			return gerarItem(x, y);
		}
	}
	
	private Card gerarPocao(int x, int y) {
		int gerarPocao = (int)(Math.random()*3);
		
		if(numeroPocoes < MAXIMO_POCOES) {
			numeroPocoes++;
			
			switch (gerarPocao) {
			case 0:
				return new Item(POTION_HEALTH, BASE_PATH+SPRITE_POTION_HEALTH, x, y, HEALTH, potionPower[(int)(Math.random()*(potionPower.length-1))]);
			case 1:
				return new Item(POTION_MANA, BASE_PATH+SPRITE_POTION_MANA, x, y, HEALTH, potionPower[(int)(Math.random()*(potionPower.length-1))]);
			case 2:
				return new Item(POTION_POISON, BASE_PATH+SPRITE_POTION_POISON, x, y, POISON, potionPower[(int)(Math.random()*(potionPower.length-1))]);
				
			default:
				return new Item(POTION_MANA, BASE_PATH+SPRITE_POTION_MANA, x, y, HEALTH, potionPower[(int)(Math.random()*(potionPower.length-1))]);
			}
		}else {
			return gerarItem(x, y);
		}
	}
	
	private Card gerarMoeda(int x, int y) {
		return new Item(COIN, BASE_PATH+SPRITE_COIN, x, y, GOLD, dropCoin[(int)(Math.random()*(dropCoin.length-1))]);
	}
	
	private Card gerarHumanoid(int x, int y) {
		/*
		 * 0 - Goblin
		 * 1 - Skeleton
		 * 2 - Mosca 
		 */
		int gerarHumanoid = (int)(Math.random()*3);
		numeroMonstro++;
		
		switch (gerarHumanoid) {
		case 0:
			return new Humanoid(GOBLIN, BASE_PATH+SPRITE_GOBLIN, x, y, hpEnemy[(int)(Math.random()*(hpEnemy.length-1))]);
		case 1:
			return new Humanoid(SKELETON, BASE_PATH+SPRITE_SKELETON, x, y, hpEnemy[(int)(Math.random()*(hpEnemy.length-1))]);
		case 2:
			return new Humanoid(MOSCA, BASE_PATH+SPRITE_MOSCA, x, y, hpEnemy[(int)(Math.random()*(hpEnemy.length-1))]);
			
		default:
			return new Humanoid(GOBLIN, BASE_PATH+SPRITE_GOBLIN, x, y, hpEnemy[(int)(Math.random()*(hpEnemy.length-1))]);
		}
	}
	
	private void verificaMovimento() {
		// Removendo css Hero
		paineis[hero.getX()][hero.getY()].getStyleClass().remove(ID_HERO);
		
		// Adicionando css Card
		paineis[hero.getX()][hero.getY()].getStyleClass().add(ID_CARD);
		
		// Limpando Painel card
		cards[hero.getX()][hero.getY()] = new Card();
		exibirCard(new Card("", "", hero.getX(), hero.getY()));
		
		// Verifindo se é o card da proxima posição é um item.
		if(cards[posicaoHeroi[0]][posicaoHeroi[1]] instanceof Item) {
			seguir();
			
			// Verificando 
			if(((Item)cards[hero.getX()][hero.getY()]).getType() == HEALTH) {
				hero.setItemSuporte((Item)cards[hero.getX()][hero.getY()]);
				numeroPocoes--;
				soundEvent = new MediaPlayer(new Media(getClass().getResource(BASE_PATH+PATH_SOUND_POTION).toExternalForm()));
				soundEvent.play();
			} else
				if(((Item)cards[hero.getX()][hero.getY()]).getType() == GOLD) {
					hero.setItemSuporte((Item)cards[hero.getX()][hero.getY()]);
					soundEvent = new MediaPlayer(new Media(getClass().getResource(BASE_PATH+PATH_SOUND_COIN).toExternalForm()));
					soundEvent.play();
				}else
					if(((Item)cards[hero.getX()][hero.getY()]).getType() == POISON){
						hero.setItemSuporte((Item)cards[hero.getX()][hero.getY()]);
						numeroPocoes--;
						playSoundEvent(BASE_PATH+PATH_SOUND_POTION);
					}else {
						if(hero.getItemAtaque() != null) {
							if(((Item)cards[hero.getX()][hero.getY()]).getValue() > hero.getItemAtaque().getValue()) {
								numeroArmas--;
								hero.setItemAtaque((Item)cards[hero.getX()][hero.getY()]);
							}
							if(((Item)cards[hero.getX()][hero.getY()]).getType() == SWORD) {
								playSoundEvent(BASE_PATH+PATH_SOUND_TAKE_SWORD);
							}else {
								playSoundEvent(BASE_PATH+PATH_SOUND_TAKE_WAND);
							}
						}else {
							numeroArmas--;
							hero.setItemAtaque((Item)cards[hero.getX()][hero.getY()]);
							if(((Item)cards[hero.getX()][hero.getY()]).getType() == SWORD) {
								playSoundEvent(BASE_PATH+PATH_SOUND_TAKE_SWORD);
							}else {
								playSoundEvent(BASE_PATH+PATH_SOUND_TAKE_WAND);
							}
						}
					}
			
			cards[hero.getX()][hero.getY()] = hero;
			rodadas++;
			System.out.println("rodada: "+rodadas);
		}else
			if(cards[posicaoHeroi[0]][posicaoHeroi[1]] instanceof Humanoid) {
				if(hero.getItemAtaque() == null) {
					// Comparando a vida do herói com a vida do monstro, caso a vida do herói seja menor, finaliza o jogo.
					if(hero.getHealth() > ((Humanoid)cards[posicaoHeroi[0]][posicaoHeroi[1]]).getHealth()) {
						hero.setHealth(hero.getHealth() - ((Humanoid)cards[posicaoHeroi[0]][posicaoHeroi[1]]).getHealth());
						cards[posicaoHeroi[0]][posicaoHeroi[1]] = gerarMoeda(posicaoHeroi[0], posicaoHeroi[1]);;
						exibirCard(cards[posicaoHeroi[0]][posicaoHeroi[1]]);
						numeroMonstro--;
						soundEvent = new MediaPlayer(new Media(getClass().getResource(BASE_PATH+PATH_SOUND_DAMAGE).toExternalForm()));
						soundEvent.play();
					}else {
						soundEvent = new MediaPlayer(new Media(getClass().getResource(BASE_PATH+PATH_SOUND_DEATH).toExternalForm()));
						soundEvent.play();
						hero.setHealth(0);
						exibirCard(hero);
						
						changeScreen(EnumScenes.FIM_JOGO, BASE_PATH+EnumScenes.FIM_JOGO.getPath(), EnumScenes.FIM_JOGO.getDescricao(), EnumScenes.FIM_JOGO.getWidth(), EnumScenes.FIM_JOGO.getHeight());
						changeScreen.changeScreen(EnumScenes.FIM_JOGO, bp, changeScreen, lblCoin.getText(), player);
					}
				}else {
					if(hero.getItemAtaque().getType() == SWORD){
						soundEvent = new MediaPlayer(new Media(getClass().getResource(BASE_PATH+PATH_SOUND_HIT_SWORD).toExternalForm()));
						soundEvent.play();
					}else {
						soundEvent = new MediaPlayer(new Media(getClass().getResource(BASE_PATH+PATH_SOUND_HIT_SPELL).toExternalForm()));
						soundEvent.play();
					}
						
					// Verificando a vida do monstro com o poder de ataque da arma
					if(((Humanoid)cards[posicaoHeroi[0]][posicaoHeroi[1]]).getHealth() > hero.getItemAtaque().getValue()) {
						((Humanoid)cards[posicaoHeroi[0]][posicaoHeroi[1]]).setHealth(((Humanoid)cards[posicaoHeroi[0]][posicaoHeroi[1]]).getHealth() - hero.getItemAtaque().getValue());
						
						exibirCard(cards[posicaoHeroi[0]][posicaoHeroi[1]]);
						hero.setItemAtaque(null);
						exibirCard(hero);
					}else {
						if(((Humanoid)cards[posicaoHeroi[0]][posicaoHeroi[1]]).getHealth() == hero.getItemAtaque().getValue()) {
							hero.setItemAtaque(null);
						}else {
							hero.getItemAtaque().setValue(hero.getItemAtaque().getValue() - ((Humanoid)cards[posicaoHeroi[0]][posicaoHeroi[1]]).getHealth());
						}
						
						exibirCard(hero);
						cards[posicaoHeroi[0]][posicaoHeroi[1]] = gerarMoeda(posicaoHeroi[0], posicaoHeroi[1]);
						exibirCard(cards[posicaoHeroi[0]][posicaoHeroi[1]]);
						numeroMonstro--;
					}
				}
				rodadas++;
				System.out.println("rodada: "+rodadas);
			}
		
		cards[hero.getX()][hero.getY()] = hero;
		posicaoHeroi[0] = hero.getX();
		posicaoHeroi[1] = hero.getY();
		
		paineis[hero.getX()][hero.getY()].getStyleClass().remove(ID_CARD);
		paineis[hero.getX()][hero.getY()].getStyleClass().add(ID_HERO);
		exibirCard(hero);
	}
	
	private void playSoundEvent(String path) {
		soundEvent = new MediaPlayer(new Media(getClass().getResource(path).toExternalForm()));
		soundEvent.play();
	}

	/**
	 * Gera um card com um Item ou personagem.
	 * @param card
	 */
	private void exibirCard(Card card) {
		HBox boxTop = (HBox)((VBox) paineis[card.getX()][card.getY()].getChildren().get(0)).getChildren().get(0);
		HBox boxCenter = (HBox)((VBox) paineis[card.getX()][card.getY()].getChildren().get(0)).getChildren().get(1);;
		HBox boxBottom = (HBox)((VBox) paineis[card.getX()][card.getY()].getChildren().get(0)).getChildren().get(2);;
		
		Label lblHealth = (Label)((VBox)boxTop.getChildren().get(0)).getChildren().get(0);
		ImageView heart = (ImageView)((VBox)boxTop.getChildren().get(0)).getChildren().get(1);;
		
		ImageView spriteItem = (ImageView)((HBox)boxCenter.getChildren().get(0)).getChildren().get(0);
		ImageView sprite = (ImageView)((HBox)boxCenter.getChildren().get(0)).getChildren().get(1);

		Label nome = (Label)((HBox)((VBox)boxBottom.getChildren().get(0)).getChildren().get(0)).getChildren().get(0);
		Label valorItem = (Label)((HBox)((HBox)((VBox)boxBottom.getChildren().get(0)).getChildren().get(1)).getChildren().get(0)).getChildren().get(0);
		Label valor = (Label)((HBox)((HBox)((VBox)boxBottom.getChildren().get(0)).getChildren().get(1)).getChildren().get(1)).getChildren().get(0);;
		
		lblHealth.setText("");
		heart.setImage(null);
		spriteItem.setImage(null);
		sprite.setImage(null);
		nome.setText("");
		valorItem.setText("");
		valor.setText("");
		
		if(card instanceof Hero) {
			spriteItem.setVisible(true);
			if(((Hero) card).getItemSuporte() != null) {
				if(((Hero) card).getItemSuporte().getType() == HEALTH) {
					((Hero) card).setHealth((
						((((Hero) card).getHealth() + ((Hero) card).getItemSuporte().getValue()) > Hero.DEFAULT_HEALTH ? Hero.DEFAULT_HEALTH : ((Hero) card).getHealth() + ((Hero) card).getItemSuporte().getValue())
					));
					((Hero)card).setItemSuporte(null);
				}else
					if(((Hero) card).getItemSuporte().getType() == POISON) {
						((Hero) card).setHealth((
							((((Hero) card).getHealth() - ((Hero) card).getItemSuporte().getValue()) < 1 ? 1 : ((Hero) card).getHealth() - ((Hero) card).getItemSuporte().getValue())
						));
						((Hero)card).setItemSuporte(null);
					}else 
						if(((Hero) card).getItemSuporte().getType() == GOLD) {
							lblCoin.setText(String.valueOf(Integer.valueOf(lblCoin.getText()) + ((Hero)card).getItemSuporte().getValue()));
							((Hero) card).setItemSuporte(null);
						}
			}
			
			if(((Hero) card).getItemAtaque() != null) {
				if(((Hero) card).getItemAtaque().getType() == SWORD || ((Hero) card).getItemAtaque().getType() == WAND) {
					spriteItem.setImage(new Image(getClass().getResource(((Hero) card).getItemAtaque().getSprite()).toExternalForm()));
					valorItem.setText(String.valueOf(((Hero)card).getItemAtaque().getValue()));
				}
			}
		}else {
			spriteItem.setVisible(true);
		}

		if(card instanceof Humanoid) {
			lblHealth.setText((card instanceof Hero) ? String.format("%d/%d", ((Hero)card).getHealth(), Hero.DEFAULT_HEALTH) : String.format("%d", ((Humanoid) card).getHealth()));
			lblHealth.setStyle("");
			
			heart.setImage(new Image(getClass().getResource(BASE_PATH+SPRITE_HEART).toExternalForm()));
			heart.setFitWidth(25);
			heart.setFitHeight(25);
		}
		
		sprite.setImage(new Image(getClass().getResource(card.getSprite()).toExternalForm()));
		nome.setText(card.getName());
		
		if(card instanceof Item) {
			if(((Item) card).getType().equalsIgnoreCase(POISON)) {
				lblHealth.setText(String.valueOf(((Item) card).getValue()));
			}else
				valor.setText(String.valueOf(((Item) card).getValue()));
		}
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
		}
	}
}
