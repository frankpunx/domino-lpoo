package domino.logic;

import java.util.LinkedList;
import java.util.List;

public class Board {

	private List<Piece> allPiecesOnTable = new LinkedList<Piece>();
	
	
	
	public final void putPieceOnTable(Piece p) {
		
		if(p == null)
			throw new UnsupportedOperationException("Piece not valid");
		
		else if(p.getState() != Piece.pieceState_t.ON_PLAYER)
			throw new UnsupportedOperationException("The piece is not from the player");
		
		else {
			p.setState(Piece.pieceState_t.ON_BOARD);
			allPiecesOnTable.add(p);
		}
	}
	
	public final List<Piece> getAllPiecesOnTable() {
		return allPiecesOnTable;
	}

	public String toString() {

		StringBuilder f = new StringBuilder();

		f.append("All pieces on table: ");

		for (Piece piece : allPiecesOnTable) {
			f.append(piece + " ");
		}

		return f.toString();
	}
}
