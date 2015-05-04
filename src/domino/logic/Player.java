package domino.logic;

import java.util.LinkedList;
import java.util.List;

import domino.ai.ArtificialInteligence;

public class Player {

	private String name;
	private int score;

	private List<Piece> myPieces = new LinkedList<Piece>();
	private final Game gameAssociated;

	private final ArtificialInteligence ai;

	public Player(String name, int score, Game g, ArtificialInteligence ai) {
		this.name = name;
		this.score = score;
		this.gameAssociated = g;
		this.ai = ai;
	}

	public Player(String name, Game g, ArtificialInteligence ai) {
		this(name, 0, g, ai);
	}

	public final String getName() {
		return name;
	}

	public final int getScore() {
		return score;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final void setScore(int score) {
		this.score = score;
	}

	public void addPiece(Piece p) {
		myPieces.add(p);
	}

	public Piece getPiece(int index) {
		return myPieces.get(index);
	}

	public boolean removePiece(int index) {

		try {
			myPieces.remove(index);

		} catch (IndexOutOfBoundsException e) {
			return false;
		}

		return true;
	}

	public final Game getGameAssociated() {
		return gameAssociated;
	}

	public final boolean isWinner() {
		return myPieces.isEmpty();
	}

	public String toString() {
		StringBuilder s = new StringBuilder();

		s.append("Name: " + name + " - Score: " + score + "\n");

		s.append("Available pieces: ");
		for (Piece piece : myPieces) {
			s.append(piece + " ");
		}

		return s.toString();
	}

	public Piece makeMove(List<Piece> playablePieces) {

		if(this.ai != null) {
			return ai.getPieceToPlay(playablePieces, myPieces);

		}
		
		
		// Peca escolhida pelo utilizador


		return null;
	}
}
