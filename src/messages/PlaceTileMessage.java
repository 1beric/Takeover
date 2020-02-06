package messages;

public class PlaceTileMessage {

	private int row;
	private int col;
	private String color;
	
	public PlaceTileMessage(int row, int col, String color) {
		this.row = row;
		this.col = col;
		this.color = color;
	}

	/**
	 * @return the row
	 */
	public int row() {
		return row;
	}

	/**
	 * @return the col
	 */
	public int col() {
		return col;
	}

	/**
	 * @return the color
	 */
	public String color() {
		return color;
	}
	
	public String toString() {
		return "PlaceTileMessage:\n" + 
				"\trow: " + row +
				"\n\tcol: " + col +
				"\n\tcolor: " + color;
	}
	
}
