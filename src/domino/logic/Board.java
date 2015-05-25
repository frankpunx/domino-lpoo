package domino.logic;

import java.util.LinkedList;
import java.util.List;


public class Board {

	/**
	 * Note that the playable pieces are the first piece and the last one.
	 */
	private List<Piece> allPiecesOnTable = new LinkedList<Piece>();
	
	private Rotation leftExtremityOrientation;
	private Rotation rightExtremityOrientation;
	
	

	
	public final boolean linkPieceToLeftExtremity(Piece p, SocketPiece sp) {
		
		if(checkFirstPiecePlacement(p))
			return true;
		
		int leftValue = allPiecesOnTable.get(0).getValuesPair().getFirst();
		
		if(leftValue == p.getValuesPair().getSecond()) {
			
			allPiecesOnTable.add(0, p);
			return true;
			
		} else if(leftValue == p.getValuesPair().getFirst()) {
			
			p.getValuesPair().swapValues();
			allPiecesOnTable.add(0, p);
			return true;
		
		} else
			return false;
	}
	
	
	public final boolean linkPieceToRightExtremity(Piece p) {
		
		// Primeira peca a ser colocada
		if(checkFirstPiecePlacement(p))
			return true;

		int rightValue = allPiecesOnTable.get(allPiecesOnTable.size() - 1).getValuesPair().getSecond();

		if(rightValue == p.getValuesPair().getFirst()) {

			allPiecesOnTable.add(p);
			return true;

		} else if(rightValue == p.getValuesPair().getSecond()) {

			p.getValuesPair().swapValues();
			allPiecesOnTable.add(p);
			return true;

		} else
			return false;
	}


	private boolean checkFirstPiecePlacement(Piece p) {
		
		if(allPiecesOnTable.size() == 0) {

			allPiecesOnTable.add(p);
			return true;
		
		} else 
			return false;
	}
	
	
	
	// Peca esquerda -> get(0)
	// Peca direita  -> get(n - 1)
	public final List<Piece> getBoardExtremities() {
		List<Piece> l = new LinkedList<Piece>();
		
		l.add(allPiecesOnTable.get(0));
		l.add(allPiecesOnTable.get(allPiecesOnTable.size() - 1));
		
		return l;
	}

	
	
	
	
	
	
 	public final List<Piece> getAllPiecesOnTable() {
		return allPiecesOnTable;
	}

	public final Rotation getLeftExtremityOrientation() {
		return leftExtremityOrientation;
	}


	public final Rotation getRightExtremityOrientation() {
		return rightExtremityOrientation;
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
