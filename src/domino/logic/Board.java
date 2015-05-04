package domino.logic;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Board {

	private List<Piece> availablePieces = new LinkedList<Piece>();
	private List<Piece> allPiecesOnTable = new LinkedList<Piece>();
	
	private List<Piece> playablePieces = new LinkedList<Piece>();
	
	public Board() {

		/* Add a new piece to the board */
		for (int i = 0; i <= 6; i++) {
			for (int j = 0; j <= i; j++) {

				Piece p = new Piece(i, j);

				availablePieces.add(p);
			}
		}
		
		/* Shuffle the available pieces */
		Collections.shuffle(availablePieces);
	}

	public final List<Piece> getAvailablePieces() {
		return availablePieces;
	}

	public final List<Piece> getAllPiecesOnTable() {
		return allPiecesOnTable;
	}
	
	public final List<Piece> getPlayablePieces() {
		return playablePieces;
	}
	
	public final Piece getPiece() {
		
		if(availablePieces.isEmpty()) {
			return null;
			
		} else {
			Piece piece = availablePieces.remove(0);
			piece.setState(Piece.pieceState_t.ON_PLAYER);
			return piece;
		}
	}
	

	public final void putPiece(Piece p) {
		
		if(p.getState() != Piece.pieceState_t.ON_PLAYER)
			throw new UnsupportedOperationException("The piece is not from the player.");
		
		
	}
	
	
	
	public String toString() {

		StringBuilder f = new StringBuilder();

		f.append("All Pieces: ");

		for (Piece piece : allPiecesOnTable) {
			f.append(piece + " ");
		}

		f.append("\nAvailable Pieces: ");

		for (Piece piece : availablePieces) {
			f.append(piece + " ");
		}

		return f.toString();
	}
}
