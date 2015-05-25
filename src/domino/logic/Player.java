package domino.logic;

import java.util.LinkedList;
import java.util.List;

import domino.ai.ArtificialInteligence;
import domino.exceptions.LinkingNotPossible;

public class Player {

	private String name;
	private int score;
	
	private final ArtificialInteligence ai;					// Pode ser null

	private List<Piece> myPieces = new LinkedList<Piece>();
	private final Game gameAssociated;
	
	private Player(String name, int score, Game g, ArtificialInteligence ai) {
		this.name = name;
		this.score = score;
		this.gameAssociated = g;
		this.ai = ai;
	}

	public Player(String name, Game g, ArtificialInteligence ai) {
		this(name, 0, g, ai);
	}
	
	public Player(String name, Game g) {
		this(name, 0, g, null);
	}

	
	// ------------------------------------------------------
	
	public final boolean isWinner() {
		return myPieces.isEmpty();
	}
	

	
	// -----------------------------------------------------------------
	
	public final String getName() {
		return name;
	}

	public final int getScore() {
		return score;
	}

	public void addPiece(Piece p) {
		myPieces.add(p);
	}

	public Piece getPiece(int index) {
		return myPieces.get(index);
	}

	public Piece removePiece(int index) {

		try {
			return myPieces.remove(index);

		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	public final List<Piece> getMyPieces() {
		return myPieces;
	}
	
	public final Game getGameAssociated() {
		return gameAssociated;
	}

	public String toString() {
		
		StringBuilder s = new StringBuilder();

		s.append("\nName: " + name + " - Score: " + score + "\n");

		s.append("My pieces: ");
		for (Piece piece : myPieces) {
			s.append(piece + " ");
		}

		return s.toString();
	}
}
