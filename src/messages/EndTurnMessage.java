package messages;

public class EndTurnMessage {

	private int nextPlayer;
	private boolean multiplayer;
	
	public EndTurnMessage(int next, boolean multi) {
		nextPlayer = next;
		multiplayer = multi;
	}
	
	public int nextPlayer() {
		return nextPlayer;
	}
	
	public boolean multiplayer() {
		return multiplayer;
	}
	
	public String toString() {
		return "EndTurnMessage\n" + 
				"\tnextPlayer: " + nextPlayer +
				"\n\tmultiplayer: " + multiplayer;
	}
}
