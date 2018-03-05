package sequence.game;

public class Card {
	private Rank rank;
	private Suit suit;
	private String abbreviation;
	
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
		this.abbreviation = Tools.rankToAbbrev(rank) + Tools.suitToAbbrev(suit);
	}

	public Rank getRank() {
		return rank;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public String getAbbreviation() {
		return abbreviation;
	}
	
	@Override
	public String toString() {
		return rank + " of " + suit;
	}
	
	@Override
	public int hashCode() {
	    return this.toString().hashCode();
	}
	
	@Override
	public boolean equals(Object card) {
		return this.toString().equals(card.toString());
	}
}
