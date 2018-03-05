package sequence.game;

import java.util.ArrayList;
import java.util.HashMap;

import sequence.players.Player;

public class Sequence {
	public static final int NUM_PLAYERS = 2;
	public static final int NUM_CARDS_IN_HAND = 7;
	public static final String SPLITTER = "-";
	private Board board;
	private Deck deck;
	private ArrayList<ArrayList<Card>> hands;
	private HashMap<Player, Character> playerCharacters;
	Player[] players;
	private GameState game;
	private boolean gameOver;
	private Player winningPlayer = null;
	
	
	public Sequence(Player p1, Player p2) {
		board = new Board();
		deck = new Deck();
		game = new GameState(this);
		hands = new ArrayList<ArrayList<Card>>(NUM_PLAYERS);
		for (int i = 0; i < NUM_PLAYERS; i++) {
			hands.add(new ArrayList<Card>(NUM_CARDS_IN_HAND));
			for (int j = 0; j < NUM_CARDS_IN_HAND; j++) {
				hands.get(i).add(deck.drawCard());
			}
		}
		playerCharacters = new HashMap<Player,Character>(NUM_PLAYERS);
		players = new Player[2];
		players[0] = p1;
		players[1] = p2;
		playerCharacters.put(p1, 'X');
		playerCharacters.put(p2, 'O');
		gameOver = false;
	}
	
	public void play() throws IllegalMoveException {
		int currPlayer = 0;
		int playersSkipped = 0;
		while(!gameOver) {
			String move = players[currPlayer].play(hands.get(currPlayer), game);
			if (validMove(move)) {
				if (move.equals("skip")) {
					playersSkipped++;
				} else {
					String[] splitMove = move.split(SPLITTER);
					if (Tools.isRemove(hands.get(currPlayer).get(Integer.parseInt(splitMove[0])))) {
						board.unclaimTile(splitMove[1]);
					} else {
						board.claimTile(splitMove[1], playerCharacters.get(players[currPlayer]));
						checkIfPlayerWon(currPlayer, splitMove[1]);
					}
					drawCard(currPlayer, Integer.parseInt(splitMove[0]));
				}
			} else {
				throw new IllegalMoveException("Invalid Move");
			}
			if (currPlayer == NUM_PLAYERS - 1) {
				if (playersSkipped == NUM_PLAYERS) {
					gameOver = true;
				}
			}
			currPlayer = (currPlayer+1)%NUM_PLAYERS;
		}
	}
	
	public void showResults() {
		board.printBoard();
		if (winningPlayer == null) {
			System.out.println("Game is a draw");
		} else {
			System.out.println(winningPlayer.getName() + " won!");
		}
	}
	public ArrayList<String> getLegalPlays(Card c, Player p) {
		return board.getLegalPlays(c, playerCharacters.get(p));
	}
	
	private void checkIfPlayerWon(int currPlayer, String move) {
		gameOver = board.checkForFive(move);
		if(gameOver) {
			winningPlayer = players[currPlayer];
		}
	}
	
	private void drawCard(int currPlayer, int handIndex) {
		Card drawnCard = deck.drawCard();
		if (drawnCard != null) {
			hands.get(currPlayer).set(handIndex, drawnCard);
		} else {
			hands.get(currPlayer).remove(handIndex);
		}
	}
	
	private boolean validMove(String move) {
		return true;
	}
	
	
}
