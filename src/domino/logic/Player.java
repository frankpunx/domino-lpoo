package domino.logic;

import java.util.LinkedList;
import java.util.List;

import domino.ai.ArtificialInteligence;
import domino.exceptions.LinkingNotPossible;

public class Player {

	private String name;
	private int score;
	private final ArtificialInteligence ai;

	private List<Piece> myPieces = new LinkedList<Piece>();
	private final Game gameAssociated;

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


	public Piece makePlayerMove(List<Piece> playablePieces) throws LinkingNotPossible {

		if(this.ai != null) {
			Piece pieceToPlay = ai.getPieceToPlay(playablePieces, myPieces);

			if(pieceToPlay == null) {
				gameAssociated.getNewAvailablePiece(this);
				throw new LinkingNotPossible();
			}
		}


		// Peca escolhida pelo utilizador


		return null;
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
