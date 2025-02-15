package models;

public class Tile {

	private int row;
	private int col;
	private String color;
	
	
	public Tile(int row, int col, String color) {
		this.row = row;
		this.col = col;
		this.color = color;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public String getColor() {
		return color;
	}
	
	public String setColor(String color) {
		return this.color = color;
	}

}
