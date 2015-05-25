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



	// NOTA: na extremidade esquerda, e o primeiro valor que esta sempre livre
	public final void linkPieceToLeftExtremity(Piece p, SocketPiece sp) {

		p.inheritFrom(sp);
		allPiecesOnTable.add(0, p);

		if(p.getRotation() == Rotation.NORTH) {

			if(p.isDoubleValues()) {

				leftExtremityOrientation = Rotation.WEST;

				if(allPiecesOnTable.size() == 1)
					rightExtremityOrientation = Rotation.EAST;

			} else {

				leftExtremityOrientation = Rotation.NORTH;

				if(allPiecesOnTable.size() == 1)
					rightExtremityOrientation = Rotation.SOUTH;
			}


		} else if(p.getRotation() == Rotation.EAST) {

			if(p.isDoubleValues()) {

				leftExtremityOrientation = Rotation.NORTH;

				if(allPiecesOnTable.size() == 1)
					rightExtremityOrientation = Rotation.SOUTH;

			} else {

				leftExtremityOrientation = Rotation.EAST;

				if(allPiecesOnTable.size() == 1)
					rightExtremityOrientation = Rotation.WEST;
			}


		} else if(p.getRotation() == Rotation.SOUTH) {

			if(p.isDoubleValues()) {

				leftExtremityOrientation = Rotation.EAST;

				if(allPiecesOnTable.size() == 1)
					rightExtremityOrientation = Rotation.WEST;

			} else {

				leftExtremityOrientation = Rotation.SOUTH;

				if(allPiecesOnTable.size() == 1)
					rightExtremityOrientation = Rotation.NORTH;
			}


		} else {

			if(p.isDoubleValues()) {

				leftExtremityOrientation = Rotation.SOUTH;

				if(allPiecesOnTable.size() == 1)
					rightExtremityOrientation = Rotation.NORTH;

			} else {

				leftExtremityOrientation = Rotation.WEST;

				if(allPiecesOnTable.size() == 1)
					rightExtremityOrientation = Rotation.EAST;
			}
		}
	}



	// NOTA: na extremidade direita, e o segundo valor que esta sempre livre
	// p -> peca a colocar
	public final void linkPieceToRightExtremity(Piece p, SocketPiece sp) {

		p.inheritFrom(sp);
		allPiecesOnTable.add(p);

		if(p.getRotation() == Rotation.NORTH) {

			if(p.isDoubleValues()) {

				rightExtremityOrientation = Rotation.WEST;

				if(allPiecesOnTable.size() == 1)
					leftExtremityOrientation = Rotation.EAST;

			} else {

				rightExtremityOrientation = Rotation.SOUTH;

				if(allPiecesOnTable.size() == 1)
					leftExtremityOrientation = Rotation.NORTH;
			}



		} else if(p.getRotation() == Rotation.EAST) {

			if(p.isDoubleValues()) {

				rightExtremityOrientation = Rotation.NORTH;

				if(allPiecesOnTable.size() == 1)
					leftExtremityOrientation = Rotation.SOUTH;

			} else {

				rightExtremityOrientation = Rotation.WEST;

				if(allPiecesOnTable.size() == 1)
					leftExtremityOrientation = Rotation.EAST;
			}




		} else if(p.getRotation() == Rotation.SOUTH) {

			if(p.isDoubleValues()) {

				rightExtremityOrientation = Rotation.EAST;

				if(allPiecesOnTable.size() == 1)
					leftExtremityOrientation = Rotation.WEST;

			} else {

				rightExtremityOrientation = Rotation.NORTH;

				if(allPiecesOnTable.size() == 1)
					leftExtremityOrientation = Rotation.SOUTH;
			}

		} else {

			if(p.isDoubleValues()) {

				rightExtremityOrientation = Rotation.SOUTH;

				if(allPiecesOnTable.size() == 1)
					leftExtremityOrientation = Rotation.NORTH;

			} else {

				rightExtremityOrientation = Rotation.EAST;

				if(allPiecesOnTable.size() == 1)
					leftExtremityOrientation = Rotation.WEST;
			}

		}
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
