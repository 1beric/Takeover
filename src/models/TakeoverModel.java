package models;

import java.util.Observable;

import messages.EndTurnMessage;
import messages.PlaceTileMessage;
import messages.SetPlayerColorMessage;

public class TakeoverModel extends Observable {

	private int rows;
	private int cols;
	private String player1Color;
	private String player2Color;
	private boolean multiplayer;
	
	private int player;
	private boolean newColorSet;
	
	private Tile[][] grid;
	
	public TakeoverModel() {
		player = 1;
		player1Color = "red";
		player2Color = "blue";
		newColorSet = false;
	}
	
	public int setRows(int rows) {
		return this.rows = rows;
	}

	public int setCols(int cols) {
		return this.cols = cols;
	}
	
	public String setActiveColor(String color) {
		newColorSet = true;
		setChanged();
		if (player==2) {
			notifyObservers(new SetPlayerColorMessage(color, 2, player2Color));
			return player2Color = color; 
		}
		else {
			notifyObservers(new SetPlayerColorMessage(color, 1, player1Color));
			return player1Color = color;
		}
}
	
	public String setOpponentColor(String color) {
		newColorSet = true;
		setChanged();
		if (player==1) {
			notifyObservers(new SetPlayerColorMessage(color, 2, player2Color));
			return player2Color = color; 
		}
		else {
			notifyObservers(new SetPlayerColorMessage(color, 1, player1Color));
			return player1Color = color;
		}
	}
	
	public boolean setTile(int row, int col, String color) {
		if (grid[row][col] == null) grid[row][col] = new Tile(row, col, color);
		else						grid[row][col].setColor(color);
		setChanged();
		notifyObservers(new PlaceTileMessage(row,col,color));
		return true;
	}
	
	public void initialize() {
		grid = new Tile[rows][cols];
	}
	
	public void clear() {
		grid = null;
	}
	
	public boolean initialized() {
		return grid != null;
	}
	
	public int rows() {
		return rows;
	}
	
	public int cols() {
		return cols;
	}
	
	public String tile(int row, int col) {
		return grid[row][col].getColor();
	}

	public String activeColor() {
		return (player==2) ? player2Color : player1Color;
	}
	
	public String opponentColor() {
		return (player==1) ? player2Color : player1Color;
	}

	public int player() {
		return player;
	}
	
	public boolean multiplayer() {
		return multiplayer; 
	}
	
	public void setPlayer(int player) {
		this.player = player;
	}

	public void setMultiplayer(boolean m) {
		multiplayer = m;
	}
	
	public void endTurn() {
		newColorSet = false;
		setChanged();
		notifyObservers(new EndTurnMessage(3-player, multiplayer));
	}

	public boolean newColorSet() {
		return newColorSet;
	}
	
}
