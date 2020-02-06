package controller;

import java.util.Observer;
import java.util.Random;

import models.TakeoverModel;

public class TakeoverController {
	
	private TakeoverModel model;

	public TakeoverController(Observer obs) {
		model = new TakeoverModel();
		model.addObserver(obs);
	}

	public void setPlayer(int player) {
		if (player != 1 || player != 2) ; // TODO THROW EXCEPTION
		model.setPlayer(player);
		
	}

	
	public void setSize(int rows, int cols) {
		model.setRows(rows);
		model.setCols(cols);
	}
	
	public boolean initBoard() {
		if (model.initialized())	return false; // TODO THROW EXCEPTION
		model.initialize();
		for (int row = 0; row < model.rows(); row++) {
			for (int col = 0; col < model.cols(); col++) {
				if (!model.setTile(row, col, convertToColor((new Random()).nextInt(6)))) {
					model.clear();
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean selectTile(int row, int col) {
		if (!model.newColorSet()) return false;
		String changingColor = model.tile(row, col);
		if (model.activeColor().equals(changingColor)) return false;
		model.setTile(row, col, model.activeColor());
		for (int i=-1;i<2;i++) {
			if (row+i<0 || row+i>=model.rows()) continue;
			for (int j=-1;j<2;j++) {
				if (col+j<0 || col+j>=model.cols()) continue;
				if (i+j==0 || i+j==2 || i+j==-2) continue;
				if (model.tile(row+i, col+j).equals(changingColor))
					selectTile(row+i,col+j);
			}
		}
		return true;
	}
	
	public void endTurn() {
		model.endTurn();
	}
	
	public String setActiveColor(String color) {
		return model.setActiveColor(color);
	}
	
	public String setOpponentColor(String color) {
		return model.setOpponentColor(color);
	}
	
	public boolean clearBoard() {
		if (!model.initialized())	return false;
		model.clear();
		return true;
	}
	
	public int size() {
		return model.rows();
	}
	
	private String convertToColor(int col) {
		switch (col) {
		case 0:
			return "green";
		case 1:
			return "pink";
		case 2:
			return "red";
		case 3:
			return "yellow";
		case 4:
			return "blue";
		case 5:
			return "purple";
		default:
			return "white";
		}
	}

	public void setMultiplayer(boolean m) {
		model.setMultiplayer(m);
	}

	public void selectRandomTile() {
		Random r = new Random();
		int row = r.nextInt(model.rows());
		int col = r.nextInt(model.cols());
		String color = model.tile(row, col);
		if (color == model.activeColor() || color == model.opponentColor())
				selectRandomTile();
		else	selectTile(row, col);
	}

	public void selectRandomColor() {
		Random r = new Random();
		String color = convertToColor(r.nextInt(6));
		if (color == model.activeColor() || color == model.opponentColor())
				selectRandomColor();
		else	model.setActiveColor(color);
	}
}
