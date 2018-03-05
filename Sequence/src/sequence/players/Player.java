package sequence.players;

import java.util.ArrayList;

import sequence.game.Card;
import sequence.game.GameState;

public abstract class Player {
	String name = "No Name";
	/**
	 * 
	 * @return "c-t" where c is the index of the card in your hand
	 * and t is the id of the tile to claim
	 * ex: 3-A4
	 */
	public abstract String play(ArrayList<Card> hand, GameState game);
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
