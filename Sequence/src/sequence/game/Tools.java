package sequence.game;

import java.util.HashMap;

public class Tools {
	private static HashMap<String, Rank> ABBREV_TO_RANK = null;
	private static HashMap<String, Suit> ABBREV_TO_SUIT = null;
	private static HashMap<Rank, String> RANK_TO_ABBREV = null;
	private static HashMap<Suit, String> SUIT_TO_ABBREV = null;
	
	public static Rank abbrevToRank(String abbrev) {
		if (RANK_TO_ABBREV == null) initializeArrays();
		return ABBREV_TO_RANK.get(abbrev);
	}
	
	public static Suit abbrevToSuit(String abbrev) {
		if (ABBREV_TO_SUIT == null) initializeArrays();
		return ABBREV_TO_SUIT.get(abbrev);
	}
	
	public static String rankToAbbrev(Rank r) {
		if (RANK_TO_ABBREV == null) initializeArrays();
		return RANK_TO_ABBREV.get(r);
	}
	
	public static String suitToAbbrev(Suit s) {
		if (SUIT_TO_ABBREV == null) initializeArrays();
		return SUIT_TO_ABBREV.get(s);
	}
	
	public static boolean isWild(Card c) {
		return
				// wild rank
				c.getRank() == Rank.wild ||
				// wild suit
				c.getSuit() == Suit.wild ||
				//jack of diamonds or clubs
				(c.getRank() == Rank.jack 
					&& (c.getSuit() == Suit.diamonds || c.getSuit() == Suit.clubs));
	}
	public static boolean isRemove(Card c) {
		return (c.getRank() == Rank.jack 
					&& (c.getSuit() == Suit.hearts || c.getSuit() == Suit.spades));
	}
	
	private static void initializeArrays() {
		ABBREV_TO_RANK = new HashMap<String, Rank>();
		ABBREV_TO_RANK.put("A", Rank.ace);
		ABBREV_TO_RANK.put("2", Rank.two);
		ABBREV_TO_RANK.put("3", Rank.three);
		ABBREV_TO_RANK.put("4", Rank.four);
		ABBREV_TO_RANK.put("5", Rank.five);
		ABBREV_TO_RANK.put("6", Rank.six);
		ABBREV_TO_RANK.put("7", Rank.seven);
		ABBREV_TO_RANK.put("8", Rank.eight);
		ABBREV_TO_RANK.put("9", Rank.nine);
		ABBREV_TO_RANK.put("10", Rank.ten);
		ABBREV_TO_RANK.put("J", Rank.jack);
		ABBREV_TO_RANK.put("Q", Rank.queen);
		ABBREV_TO_RANK.put("K", Rank.king);
		ABBREV_TO_RANK.put("W", Rank.wild);
		ABBREV_TO_SUIT = new HashMap<String, Suit>();
		ABBREV_TO_SUIT.put("S", Suit.spades);
		ABBREV_TO_SUIT.put("H", Suit.hearts);
		ABBREV_TO_SUIT.put("D", Suit.diamonds);
		ABBREV_TO_SUIT.put("C", Suit.clubs);
		ABBREV_TO_SUIT.put("W", Suit.wild);
		
		RANK_TO_ABBREV = new HashMap<Rank, String>();
		RANK_TO_ABBREV.put(Rank.ace, "A");
		RANK_TO_ABBREV.put(Rank.two, "2");
		RANK_TO_ABBREV.put(Rank.three, "3");
		RANK_TO_ABBREV.put(Rank.four, "4");
		RANK_TO_ABBREV.put(Rank.five, "5");
		RANK_TO_ABBREV.put(Rank.six, "6");
		RANK_TO_ABBREV.put(Rank.seven, "7");
		RANK_TO_ABBREV.put(Rank.eight, "8");
		RANK_TO_ABBREV.put(Rank.nine, "9");
		RANK_TO_ABBREV.put(Rank.ten, "10");
		RANK_TO_ABBREV.put(Rank.jack, "J");
		RANK_TO_ABBREV.put(Rank.queen, "Q");
		RANK_TO_ABBREV.put(Rank.king, "K");
		RANK_TO_ABBREV.put(Rank.wild, "W");
		SUIT_TO_ABBREV = new HashMap<Suit, String>();
		SUIT_TO_ABBREV.put(Suit.spades, "S");
		SUIT_TO_ABBREV.put(Suit.hearts, "H");
		SUIT_TO_ABBREV.put(Suit.diamonds, "D");
		SUIT_TO_ABBREV.put(Suit.clubs, "C");
		SUIT_TO_ABBREV.put(Suit.wild, "W");
	}
}
