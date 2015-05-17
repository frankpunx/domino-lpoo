package domino.logic;

import java.util.LinkedList;
import java.util.List;

import domino.exceptions.LinkingNotPossible;

public class Board {

	/**
	 * Note that the playable pieces are the first piece and the last one.
	 */
	private List<Piece> allPiecesOnTable = new LinkedList<Piece>();
	
	
	
	public final void putPieceOnTable(Piece onTable, Piece toPlay) throws LinkingNotPossible {
		
		if(onTable == null || toPlay == null)
			throw new UnsupportedOperationException("Piece not valid");
		
		else if(toPlay.getState() != Piece.pieceState_t.ON_PLAYER)
			throw new UnsupportedOperationException("The piece is not from the player");
		
		else if(onTable == allPiecesOnTable.get(0)) {
			toPlay.setState(Piece.pieceState_t.ON_BOARD);
			allPiecesOnTable.add(0, toPlay);
			
		} else if(onTable == allPiecesOnTable.get(allPiecesOnTable.size() - 1)) {
			toPlay.setState(Piece.pieceState_t.ON_BOARD);
			allPiecesOnTable.add(toPlay);
			
		} else {
			throw new LinkingNotPossible("The piece cannot be linked to any available on the table");
			
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
