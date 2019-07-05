package br.lpv.evildungeons.model;

public class Hero extends Humanoid {
	private int attack;
	private Item itemAtaque;
	private Item itemSuporte;
	private int hpHero;
	
	public static final int DEFAULT_HEALTH = 10;
	
	public Hero() {
		this(0);
	}

	public Hero(int attack) {
		this("", "", 0, 0, 0, attack);
	}

	public Hero(String nome, String sprite, int x, int y, int health, int attack) {
		this(nome, sprite, x, y, health, attack, null);
	}
	
	public Hero(String nome, String sprite, int x, int y, int health, int attack, Item itemAtaque) {
		this(nome, sprite, x, y, health, attack, null, null);
	}
	
	public Hero(String nome, String sprite, int x, int y, int health, int attack, Item itemAtaque, Item itemSuporte) {
		this(nome, sprite, x, y, health, attack, itemAtaque, itemSuporte, DEFAULT_HEALTH);
	}
	
	public Hero(String nome, String sprite, int x, int y, int health, int attack, Item itemAtaque, Item itemSuporte, int hpHero) {
		super(nome, sprite, x, y, health);
		this.attack = attack;
		this.itemAtaque = itemAtaque;
		this.itemSuporte = itemSuporte;
		this.hpHero = hpHero;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public Item getItemAtaque() {
		return itemAtaque;
	}

	public void setItemAtaque(Item item) {
		this.itemAtaque = item;
	}
	
	public Item getItemSuporte() {
		return itemSuporte;
	}

	public void setItemSuporte(Item itemSuporte) {
		this.itemSuporte = itemSuporte;
	}

	public int getHpHero() {
		return hpHero;
	}

	public void setHpHero(int hpHero) {
		this.hpHero = hpHero;
	}

	@Override
	public String toString() {
		return String.format("%s\nAttack: %d", super.toString(), attack);
	}
}
