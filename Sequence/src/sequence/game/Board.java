package sequence.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Board {
	public static final char EMPTY_CHAR = ' ';
	private static final int[][] DIRECTIONS = {{1,0},{0,1},{1,1},{1,-1}};
	private static final String BOARD_FILE = "src/sequence/game/board.txt";
	private static final String[][] boardArray = {
			{"A1", "B1", "C1", "D1", "E1", "F1", "G1", "H1", "I1", "J1"},
			{"A2", "B2", "C2", "D2", "E2", "F2", "G2", "H2", "I2", "J2"},		
			{"A3", "B3", "C3", "D3", "E3", "F3", "G3", "H3", "I3", "J3"},		
			{"A4", "B4", "C4", "D4", "E4", "F4", "G4", "H4", "I4", "J4"},
			{"A5", "B5", "C5", "D5", "E5", "F5", "G5", "H5", "I5", "J5"},
			{"A6", "B6", "C6", "D6", "E6", "F6", "G6", "H6", "I6", "J6"},
			{"A7", "B7", "C7", "D7", "E7", "F7", "G7", "H7", "I7", "J7"},
			{"A8", "B8", "C8", "D8", "E8", "F8", "G8", "H8", "I8", "J8"},
			{"A9", "B9", "C9", "D9", "E9", "F9", "G9", "H9", "I9", "J9"},
			{"A10","B10","C10","D10","E10","F10","G10","H10","I10","J10"},
	};
	private HashMap<String, Card> boardMap;
	private HashMap<String, Character> ownedTiles;
	private HashMap<Card, String[]> cardLocations;
	private ArrayList<String> openTiles;
	
	
	public Board() {
		boardMap= new HashMap<String, Card>();
		ownedTiles = new HashMap<String, Character>();
		cardLocations = new HashMap<Card, String[]>();
		openTiles = new ArrayList<String>();
		buildBoard();
	}
	
	public ArrayList<String> getLegalPlays(Card c, char p) {
		ArrayList<String> legalPlays;
		if (Tools.isWild(c)) {
			legalPlays = openTiles;
		} else if (Tools.isRemove(c)) {
			legalPlays = ownedByOthers(p);
		} else {
			legalPlays = new ArrayList<String>();
			String[] locs = cardLocations.get(c);
			for (String loc : locs) {
				if (!ownedTiles.containsKey(loc)) {
					legalPlays.add(loc);
				}
			}
		}
		return legalPlays;
	}
	
	public void claimTile(String tile, char symbol) {
		System.out.println("Claiming tile " + tile + " for " + symbol);
		ownedTiles.put(tile, symbol);
		openTiles.remove(tile);
	}
	
	public void unclaimTile(String tile) {
		System.out.println("Removing claim on tile " + tile);
		ownedTiles.remove(tile);
		openTiles.add(tile);
	}
	
	public boolean checkForFive(String startTile) {
		for (int i = 0; i < DIRECTIONS.length; i++) {
			if (walk(startTile, DIRECTIONS[i]) >= 5)
				return true;
		}
		return false;
	}
	
	public char getTileOwner(String tile) {
		if (ownedTiles.containsKey(tile)) {
			return ownedTiles.get(tile);
		} else {
			return EMPTY_CHAR;
		}
	}
	
	public void printBoard() {
		String[] rows = {"1 ","2 ","3 ","4 ","5 ","6 ","7 ","8 ","9 ","10"};
		System.out.println("    --------------------------------");
		for (int i = 9; i >= 0; i--) {
			System.out.print(rows[i] + " | ");
			for (int j = 0; j < 10; j++) {
				System.out.print(" ");
				if(ownedTiles.containsKey(boardArray[i][j])) {
					System.out.print(ownedTiles.get(boardArray[i][j]));
				} else {
					System.out.print(" ");
				}
				System.out.print(" ");
			}
			System.out.println(" | ");
		}
		System.out.println("    --------------------------------");
		System.out.println("      A  B  C  D  E  F  G  H  I  J");
	}
	
	public void printEmptyBoard() {
		String[] rows = {"1 ","2 ","3 ","4 ","5 ","6 ","7 ","8 ","9 ","10"};
		System.out.println("    ----------------------------------------------------");
		for (int i = 9; i >= 0; i--) {
			System.out.print(rows[i] + " | ");
			for (int j = 0; j < 10; j++) {
				String abbrev = boardMap.get(boardArray[i][j]).getAbbreviation();
				if (abbrev.length() < 3) abbrev += " ";
				System.out.print(" " + abbrev + " ");
			}
			System.out.println(" | ");
		}
		System.out.println("    ----------------------------------------------------");
		System.out.println("       A   B   C   D   E   F   G   H   I   J");
	}
	
	private ArrayList<String> ownedByOthers(char p) {
		ArrayList<String> others = new ArrayList<String>();
		for (Entry<String, Character> entry : ownedTiles.entrySet()) {
			if (entry.getValue() != p) {
				others.add(entry.getKey());
			}
		}
		return others;
	}
	
	private void buildBoard() {
		File file = new File(BOARD_FILE);
		try {
			Scanner input = new Scanner(file);
			while(input.hasNextLine()) {
				// [0] = RANK, [1] = SUIT, [2+] = TILE
				String[] line = input.nextLine().split(",");
				Card c = new Card(Tools.abbrevToRank(line[0]), Tools.abbrevToSuit(line[1]));
				String[] tiles = new String[line.length-2];
				for(int tile = 2; tile < line.length; tile++) {
					boardMap.put(line[tile], c);
					openTiles.add(line[tile]);
					tiles[tile-2] = line[tile];
				}
				cardLocations.put(c, tiles);
				
			}
	        input.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not locate the board file.");
			e.printStackTrace();
		}
	}
	
	public void printCardList() {
		System.out.println("Card Locations: ");
		for (Entry<Card, String[]> s : cardLocations.entrySet()) {
			System.out.print(s.getKey().getAbbreviation() + ": ");
			for (int i = 0; i < s.getValue().length; i++) {
				System.out.print(s.getValue()[i] + ", ");
			}
			System.out.println();
		}
	}
	
	private int walk(String startTile, int[] directions) {
		char[] letterPosition = startTile.toCharArray();
		int xPos = letterPosition[1] - '1';
		int yPos = letterPosition[0] - 'A';
		char p = ownedTiles.get(startTile);
		int result = 1;
		int xStep = directions[0];
		int yStep = directions[1];
		while ((xStep+xPos >= 0) && (xStep+xPos < 10) && (yStep+yPos >= 0) && (yStep+yPos < 10)
				&& (ownedTiles.containsKey(boardArray[xPos+xStep][yPos+yStep])) 
				&& (ownedTiles.get(boardArray[xPos+xStep][yPos+yStep]) == p)
		) {
			result++;
			xStep += 1 * directions[0];
			yStep += 1 * directions[1];
		}
		xStep = -1 * directions[0];
		yStep = -1 * directions[1];
		while ((xStep+xPos >= 0) && (xStep+xPos < 10) && (yStep+yPos >= 0) && (yStep+yPos < 10)
				&& ownedTiles.containsKey(boardArray[xPos+xStep][yPos+yStep])
				&& ownedTiles.get(boardArray[xPos+xStep][yPos+yStep]) == p) {
			result++;
			xStep += -1 * directions[0];
			yStep += -1 * directions[1];
		}
		return result;
	}
}
