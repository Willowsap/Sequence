package sequence.players;

import java.util.ArrayList;

import sequence.game.Card;
import sequence.game.GameState;

public class RandomPlayer extends Player{

	@Override
	public String play(ArrayList<Card> hand, GameState game) {
		ArrayList<String> legalPlays;
		System.out.print("My Hand: ");
		for (Card c : hand) System.out.print(c.getAbbreviation() + ", ");
		System.out.println();
		legalPlays = new ArrayList<String>();
		for (int i = 0; i < hand.size(); i++) {
			 legalPlays = game.getLegalPlays(hand.get(i), this);
			 if (legalPlays.size() > 0) {
				 System.out.println("Playing " + hand.get(i).getAbbreviation()
						 + " on " + legalPlays.get(0));
				 return i + "-" + legalPlays.get(0);
			 }
		}
		System.out.println("Skipping");
		return "skip";
	}
}
