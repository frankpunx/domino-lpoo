package domino.logic;

import java.util.LinkedList;
import java.util.List;

public class Board {

	private List<Piece> availablePieces;
	private List<Player> availablePlayers;
	
	public Board() {
		this.availablePieces = new LinkedList<Piece>();
		this.availablePlayers = new LinkedList<Player>();
		
		for (int i = 0; i <= 6; i++) {
			for (int j = 0; j <= i; j++) {
			
				availablePieces.add(new Piece(new Pair(j, i)));
			}
		}
		
		for (int i = 0; i < availablePieces.size(); i++) {
			System.out.println(availablePieces.get(i).getValuesPair());
		}
	}
	
	public final List<Piece> getAvailablePieces() {
		return availablePieces;
	}
	public final List<Player> getAvailablePlayers() {
		return availablePlayers;
	}
	
}
