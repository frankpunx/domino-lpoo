package domino.logic;

import java.util.LinkedList;
import java.util.List;

import domino.ai.ArtificialInteligence;

public class Player {

	private String name;
	private int score;
	
	private List<Piece> myPieces = new LinkedList<Piece>();
	private final Board boardAssociated;
	
	private final ArtificialInteligence ai;

	public Player(String name, int score, Board b, ArtificialInteligence ai) {
		this.name = name;
		this.score = score;
		this.boardAssociated = b;
		this.ai = ai;
	}

	public Player(String name, Board b, ArtificialInteligence ai) {
		this(name, 0, b, ai);
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
	
	public final Board getBoardAssociated() {
		return boardAssociated;
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
			return this.ai.getPieceToPlay(playablePieces, myPieces);
		} else {
			// Peca escolhida pelo utilizador
		}
		
		return null;
	}
}
