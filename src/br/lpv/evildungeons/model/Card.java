package br.lpv.evildungeons.model;

public class Card {
	private String name;
	private String sprite;
	private int x, y;
		
	public static final int MAX_POS = 3;
	
	public Card() {
		this("", "", 0, 0);
	}

	public Card(String name, String sprite) {
		this.name = name;
		this.sprite = sprite;
	}

	public Card(String name, String sprite, int x, int y) {
		this.name = name;
		this.sprite = sprite;
		this.x = x;
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSprite() {
		return sprite;
	}

	public void setSprite(String sprite) {
		this.sprite = sprite;
	}

	public void setPosicao(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return String.format("Card: %s\nSprite: %s\nxy:(%d, %d)", name, sprite, x, y);
	}
}
