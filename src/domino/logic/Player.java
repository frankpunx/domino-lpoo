package domino.logic;

import java.util.LinkedList;
import java.util.List;

public class Player {

	private String name;
	private int score;
	
	private List<Piece> myPieces = new LinkedList<Piece>();

	public Player(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public Player(String name) {
		this(name, 0);
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
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		s.append("Name: " + name + " - Score: " + score + "\n");
		
		s.append("Available pieces: ");
		for (Piece piece : myPieces) {
			s.append(piece + " ");
		}
		
		return s.toString();
	}
}
