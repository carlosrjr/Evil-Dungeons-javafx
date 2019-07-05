package br.lpv.evildungeons.model;
 
public class Humanoid extends Card {
	private int health;
	private Item itemOverTime;

	public Humanoid() {
		this(0);
	}

	public Humanoid(int health) {
		this("", "", 0, 0, health);
	}

	public Humanoid(String name, String sprite, int x, int y, int health) {
		this(name, sprite, x, y, health, null);
	}

	public Humanoid(String name, String sprite, int x, int y, int health, Item itemOverTime) {
		super(name, sprite, x, y);
		this.health = health;
		this.itemOverTime = itemOverTime;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public Item getItemOverTime() {
		return itemOverTime;
	}

	public void setItemOverTime(Item itemOverTime) {
		this.itemOverTime = itemOverTime;
	}

	@Override
	public String toString() {
		return String.format("%s\nHealth: %d", super.toString(), health);
	}
}
