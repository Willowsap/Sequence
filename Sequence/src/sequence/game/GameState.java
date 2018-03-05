package sequence.game;

import java.util.ArrayList;

import sequence.players.Player;

public class GameState {
	Sequence s;
	
	public GameState(Sequence s) {
		this.s = s; 
	}
	
	public ArrayList<String> getLegalPlays(Card c, Player p) {
		return s.getLegalPlays(c, p);
	}
}
