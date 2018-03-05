package sequence.game;

import java.util.ArrayList;

import sequence.players.Player;

public class GameState {
	private Sequence s;
	private Board b;
	
	
	public GameState(Sequence s) {
		this.s = s; 
		this.b = s.getBoard();
	}
	
	public char[] getPlayerCharacters() {
		char[] list = s.getPlayerCharacterList();
		char[] copy = new char[list.length];
		for (int i = 0; i < list.length; i++) {
			copy[i] = list[i];
		}
		return list;
	}
	
	public int getCurrPlayer() {
		return s.getCurrPlayer();
	}
	
	public char getTileOwner(String tile) {
		return b.getTileOwner(tile);
	}
	
	public ArrayList<String> getLegalPlays(Card c, Player p) {
		return s.getLegalPlays(c, p);
	}
}
