package messages;

public class SetPlayerColorMessage {

	private String color;
	private int player;
	private String previousColor;
	
	public SetPlayerColorMessage(String color, int player, String previousColor) {
		this.color = color;
		this.player = player;
		this.previousColor = previousColor;
	}
	
	public String color() {
		return color;
	}
	
	public int player() {
		return player;
	}
	
	public String previousColor() {
		return previousColor;
	}
	
	public String toString() {
		return "SetPlayerColorMessage\n" +
				"\tplayer: " + player +
				"\n\tprevious color: " + previousColor +
				"\n\tnew color: " + color;
	}
	
	
}
