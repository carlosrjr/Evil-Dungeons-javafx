package br.lpv.evildungeons.model;

public class Item extends Card {
	private String type;
	private int value;
	
	public Item() {
		this("", 0);
	}

	public Item(String type, int value) {
		this("", "", 0, 0, type, value);
	}

	public Item(String name, String sprite, int x, int y, String type, int value) {
		super(name, sprite, x, y);
		this.type = type;
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.format("%s\nType: %s\nValue: %d", super.toString(), type, value);
	}
}
