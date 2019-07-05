package br.lpv.evildungeons.tools;

/**
 * Fornece constantes para o programa.
 * 
 * @author Carlos Roberto Barbosa Júnior
 *
 * @version 0.1
 */
public class Constantes {
	/**
	 *  Nome da aplicação.
	 */
	public static final String EVIL_DUNGEONS = "Evil Dungeons";
	
	/**
	 * Width do Stage.
	 */
	public static final int MIN_WIDTH_STAGE = 500;
	
	/**
	 * Height do Stage.
	 */
	public static final int MIN_HEIGHT_STAGE = 600; 
	
	/**
	 * Width do Stage.
	 */
	public static final int WIDTH_STAGE = MIN_WIDTH_STAGE;
	
	/**
	 * Height do Stage.
	 */
	public static final int HEIGHT_STAGE = MIN_HEIGHT_STAGE; 
	
	/**
	 * Width do Scene.
	 */
	public static final int WIDTH_SCENE = WIDTH_STAGE+16;
	
	/**
	 * Height do Scene.
	 */
	public static final int HEIGHT_SCENE = HEIGHT_STAGE+39; 
	
	/**
	 * Css da aplicação.
	 */
	public static final String PATH_CSS = "view/css/EvilDungeons.css";
	
	/**
	 * Mensagem padrão de exceção.
	 */
	public static final String DEFAULT_EXCEPTION_MESSAGE = "Houve um problema com a apliação. Por favor, entre em contato com a equipe de TI.";
	
	/**
	 * Path icone da aplicação.
	 */
	public static final String ICONE_APP = "view/img/icon.png";
	
	/**
	 * Path icone da aplicação.
	 */
	public static final String PATH_FONT = "view/font/PixelFont.ttf";
	
	/**
	 * Coluna de assustos avaliados na pesquisa.
	 */
	public static final String ASSUNTOS_AVALIADOS = "Assuntos Avaliados";
	
	/**
	 * Stroke Default Gráfico.
	 */
	public static final int DEFAULT_STROKE_SIZE = 3;
	
	/**
	 * Base path.
	 */
	public static final String BASE_PATH = "/br/lpv/evildungeons/";

	public static final String SOUND_ON = "view/img/sound.png";
	public static final String SOUND_OFF = "view/img/mute.png";

	public static final String ICONE_SELECAO = "view/assets/sword_right.png";

	public static final String SPRITE_HERO = "view/assets/hero.png";
	public static final String SPRITE_HEART = "view/assets/heart.png";
	public static final String SPRITE_COIN = "view/assets/coin.png";

	public static final String SPRITE_GOBLIN = "view/assets/goblin.png";
	public static final String SPRITE_MOSCA = "view/assets/mosca.png";
	public static final String SPRITE_SKELETON = "view/assets/skeleton.png";
	
	public static final String SPRITE_POTION_HEALTH = "view/assets/potion_health.png";
	public static final String SPRITE_POTION_MANA = "view/assets/potion_mana.png";
	public static final String SPRITE_POTION_POISON = "view/assets/potion_poison.png";
	
	public static final String SPRITE_SWORD1 = "view/assets/sword1.png";
	public static final String SPRITE_SWORD2 = "view/assets/sword2.png";
	public static final String SPRITE_SWORD3 = "view/assets/sword4.png";
	public static final String SPRITE_SWORD4 = "view/assets/sword3.png";
	
	public static final String SPRITE_WAND_FIRE = "view/assets/wand_fire.png";
	public static final String SPRITE_WAND_ICE = "view/assets/wand_ice.png";
	public static final String SPRITE_WAND_POISON = "view/assets/wand_poison.png";

	public static final String PATH_SOUND_DUNGEON = "view/sound/dungeon.mp3";
	public static final String PATH_SOUND_HIT_SWORD = "view/sound/sword.wav";
	public static final String PATH_SOUND_HIT_SPELL = "view/sound/spell.mp3";
	public static final String PATH_SOUND_DAMAGE = "view/sound/damage.wav";
	public static final String PATH_SOUND_DEATH = "view/sound/male_death.wav";
	public static final String PATH_SOUND_GAME_OVER = "view/sound/gameover.mp3";
	public static final String PATH_SOUND_POTION = "view/sound/potion.wav";
	public static final String PATH_SOUND_SELECT = "view/sound/select.wav";
	public static final String PATH_SOUND_COIN = "view/sound/coin.wav";
	public static final String PATH_SOUND_TAKE_SWORD = "view/sound/take_sword.mp3";
	public static final String PATH_SOUND_TAKE_WAND = "view/sound/take_wand.wav";
	
	public static final String HERO = "Herói";
	public static final String GOBLIN = "Goblin";
	public static final String MOSCA = "Mosca";
	public static final String SKELETON = "Esqueleto";
	
	public static final String SWORD1 = "Excalibur";
	public static final String SWORD2 = "Espada de Astora";
	public static final String SWORD3 = "Espada de Ouro";
	public static final String SWORD4 = "Frostmourne";
	
	public static final String WAND_FIRE = "Varinha de Fogo";
	public static final String WAND_ICE = "Varinha de Gelo";
	public static final String WAND_POISON = "Varinha de Veneno";
	
	public static final String POTION_HEALTH = "Poção de Vida";
	public static final String POTION_MANA = "Poção Azul";
	public static final String POTION_POISON = "Veneno";
	public static final String COIN = "Moeda de Ouro";
	
	public static final String WEAPON = "Weapon";
	public static final String SWORD = "Sword";
	public static final String WAND = "Wand";
	
	public static final String HEALTH = "Health";
	public static final String POISON = "Poison";
	public static final String GOLD = "Gold";

	public static final String ID_CARD = "card";
	public static final String ID_HERO = "hero";
	
	public static final int MAXIMO_MONSTROS = 3;
	public static final int MAXIMO_POCOES = 2;
	public static final int MAXIMO_ARMAS = 2;
	
	public static final int MONSTER_LEVEL_1[] = {1,2,3,4,5};
	public static final int MONSTER_LEVEL_2[] = {1,2,3,4,5,6,7,8,9,10};
	public static final int MONSTER_LEVEL_3[] = {4,5,6,7,8,9,10,11,12,13,14,15};
	public static final int MONSTER_LEVEL_4[] = {7,8,9,10,11,12,13,14,15, 20, 25, 30, 35};
	public static final int MONSTER_BOSS[] = {50, 80, 100};

	public static final int BOSS_TURN = 100;
	public static final int LEVEL1_TURN = 50;
	public static final int LEVEL2_TURN = 100;
	public static final int LEVEL3_TURN = 200;
	
	public static final String LEVEL_1 = "Level 1";
	public static final String LEVEL_2 = "Level 2";
	public static final String LEVEL_3 = "Level 3";
	public static final String LEVEL_4 = "Level 4";
	
	public static final int HERO_LEVEL_1 = 10;
	public static final int HERO_LEVEL_2 = 15;
	public static final int HERO_LEVEL_3 = 20;
	public static final int HERO_LEVEL_4 = 25;

	public static final String LEVEL_BOSS = "BOSS Level";
	public static final String BOSS = "BOSS";
}
