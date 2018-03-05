package sequence.game;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private ArrayList<Card> cardList;
	private ArrayList<Card> discardPile;
	
	public Deck() {
		this.cardList = new ArrayList<Card>();
		this.discardPile = new ArrayList<Card>();
		buildDeck();
		shuffleDeck();
	}
	
	public void reset() {
		this.cardList.addAll(discardPile);
		shuffleDeck();
	}
	
	public void shuffleDeck() {
		Collections.shuffle(cardList);
	}
	
	public Card drawCard() {
		if (cardList.size() == 0) {
			return null;
		}
		discardPile.add(cardList.remove(cardList.size()-1));
		return discardPile.get(discardPile.size()-1);
	}
	
	private void buildDeck() {
		Suit[] suits = Suit.values();
		Rank[] ranks = Rank.values();
		// Add two of each card except wilds
		for (int i = 0; i < suits.length - 1; i++) {
			for (int j = 0; j < ranks.length - 1; j++) {
				cardList.add(new Card(ranks[j], suits[i]));
				cardList.add(new Card(ranks[j], suits[i]));
			}
		}
	}
}
