package sequence;

import sequence.game.IllegalMoveException;
import sequence.game.Sequence;
import sequence.players.RandomPlayer;

public class Simulator {
	public static void main(String[] args) {
		RandomPlayer p1 = new RandomPlayer();
		RandomPlayer p2 = new RandomPlayer();
		p1.setName("Player 1");
		p2.setName("Player 2");
		Sequence s = new Sequence(p1,p2);
		try {
			s.play();
			s.showResults();
		} catch (IllegalMoveException e) {
			e.printStackTrace();
		}
	}
}
