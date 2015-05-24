package domino.gui;

import domino.exceptions.LinkingNotPossible;
import domino.logic.Direction;
import domino.logic.Pair;
import domino.logic.Piece;

public class ClassStub {

	/**
	 * Links two pieces on the board. If such connection is not possible, the link is not established.
	 * @param p1 The piece which is going to be linked with p2. It is already in the table
	 * @param p2 The other piece involved in the process of linkage
	 * @param r Random object used to get a random orientation for the piece (valid if the player is not human). Furthermore, it accepts <null> if the positions are set by the player.
	 * @return <true> if was successful; <false> otherwise.
	 */
	public final boolean linkPieces(Piece p1, Piece p2, Piece.orientation_t newOrientation, Direction newDirection) {

		if(p1.getPositionPair() == null)
			return false;


		if(p2.isDoubleValues()) {

			if(p1.getOrientation() == Piece.orientation_t.HORIZONTAL && p1.getAvailablePosition() == Piece.availablePosition_t.RIGHT) {

				if(p1.getValuesPair().getSecond() == p2.getValuesPair().getFirst()) {
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 3, p1.getPositionPair().getSecond()));
					p2.setOrientation(Piece.orientation_t.VERTICAL);
					p2.setAvailablePosition(Piece.availablePosition_t.LEFT_RIGHT_UP);
					p1.setAvailablePosition(Piece.availablePosition_t.NONE);

				} else 
					return false;

			} else if(p1.getOrientation() == Piece.orientation_t.HORIZONTAL && p1.getAvailablePosition() == Piece.availablePosition_t.LEFT) {

				if(p1.getValuesPair().getFirst() == p2.getValuesPair().getFirst()) {
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() - 3, p1.getPositionPair().getSecond()));
					p2.setOrientation(Piece.orientation_t.VERTICAL);
					p2.setAvailablePosition(Piece.availablePosition_t.LEFT_RIGHT_DOWN);
					p1.setAvailablePosition(Piece.availablePosition_t.NONE);

				} else 
					return false;

			} else if(p1.getOrientation() == Piece.orientation_t.VERTICAL && p1.getAvailablePosition() == Piece.availablePosition_t.LEFT) {

				if(p1.getValuesPair().getFirst() == p2.getValuesPair().getFirst()) {
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst(), p1.getPositionPair().getSecond() - 3));
					p2.setOrientation(Piece.orientation_t.HORIZONTAL);
					p2.setAvailablePosition(Piece.availablePosition_t.LEFT_RIGHT_UP);
					p1.setAvailablePosition(Piece.availablePosition_t.NONE);

				} else 
					return false;

			} else if(p1.getOrientation() == Piece.orientation_t.VERTICAL && p1.getAvailablePosition() == Piece.availablePosition_t.RIGHT) {

				if(p1.getValuesPair().getSecond() == p2.getValuesPair().getFirst()) {
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst(), p1.getPositionPair().getSecond() + 3));
					p2.setOrientation(Piece.orientation_t.HORIZONTAL);
					p2.setAvailablePosition(Piece.availablePosition_t.LEFT_RIGHT_DOWN);
					p1.setAvailablePosition(Piece.availablePosition_t.NONE);

				} else 
					return false;
			}

			// --------------------------------------------------------------------------------------------------------------------------------------------

		} else if(p1.isDoubleValues()) {

			if(p1.getOrientation() == Piece.orientation_t.HORIZONTAL && p1.getAvailablePosition() == Piece.availablePosition_t.LEFT_RIGHT_UP) {

				if(newDirection == Direction.RIGHT && p1.getValuesPair().getFirst() == p2.getValuesPair().getFirst()) {
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 4, p1.getPositionPair().getSecond()));
					p2.setOrientation(Piece.orientation_t.HORIZONTAL);
					p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

				} else if(newDirection == Direction.RIGHT && p1.getValuesPair().getFirst() == p2.getValuesPair().getSecond()) {
					p2.flipValues();
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 4, p1.getPositionPair().getSecond()));
					p2.setOrientation(Piece.orientation_t.HORIZONTAL);
					p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

				} else if(newDirection == Direction.LEFT && p1.getValuesPair().getFirst() == p2.getValuesPair().getSecond()) {
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() - 4, p1.getPositionPair().getSecond()));
					p2.setOrientation(Piece.orientation_t.HORIZONTAL);
					p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

				} else if(newDirection == Direction.LEFT && p1.getValuesPair().getFirst() == p2.getValuesPair().getFirst()) {
					p2.flipValues();
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() - 4, p1.getPositionPair().getSecond()));
					p2.setOrientation(Piece.orientation_t.HORIZONTAL);
					p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

				} else if(newDirection == Direction.UP && p1.getValuesPair().getFirst() == p2.getValuesPair().getFirst()) {
					p2.flipValues();
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst(), p1.getPositionPair().getSecond() - 4));
					p2.setOrientation(Piece.orientation_t.VERTICAL);
					p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

				} else if(newDirection == Direction.UP && p1.getValuesPair().getFirst() == p2.getValuesPair().getSecond()) {
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst(), p1.getPositionPair().getSecond() - 4));
					p2.setOrientation(Piece.orientation_t.VERTICAL);
					p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

				} else
					return false;

				p1.setAvailablePosition(Piece.availablePosition_t.NONE);

			} else if(p1.getOrientation() == Piece.orientation_t.HORIZONTAL && p1.getAvailablePosition() == Piece.availablePosition_t.LEFT_RIGHT_DOWN) {

				if(newDirection == Direction.RIGHT && p1.getValuesPair().getFirst() == p2.getValuesPair().getFirst()) {
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 4, p1.getPositionPair().getSecond()));
					p2.setOrientation(Piece.orientation_t.HORIZONTAL);
					p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

				} else if(newDirection == Direction.RIGHT && p1.getValuesPair().getFirst() == p2.getValuesPair().getSecond()) {
					p2.flipValues();
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 4, p1.getPositionPair().getSecond()));
					p2.setOrientation(Piece.orientation_t.HORIZONTAL);
					p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

				} else if(newDirection == Direction.LEFT && p1.getValuesPair().getFirst() == p2.getValuesPair().getSecond()) {
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() - 4, p1.getPositionPair().getSecond()));
					p2.setOrientation(Piece.orientation_t.HORIZONTAL);
					p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

				} else if(newDirection == Direction.LEFT && p1.getValuesPair().getFirst() == p2.getValuesPair().getFirst()) {
					p2.flipValues();
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() - 4, p1.getPositionPair().getSecond()));
					p2.setOrientation(Piece.orientation_t.HORIZONTAL);
					p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

				} else if(newDirection == Direction.DOWN && p1.getValuesPair().getFirst() == p2.getValuesPair().getFirst()) {
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst(), p1.getPositionPair().getSecond() + 4));
					p2.setOrientation(Piece.orientation_t.VERTICAL);
					p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

				} else if(newDirection == Direction.DOWN && p1.getValuesPair().getFirst() == p2.getValuesPair().getSecond()) {
					p2.flipValues();
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst(), p1.getPositionPair().getSecond() + 4));
					p2.setOrientation(Piece.orientation_t.VERTICAL);
					p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

				} else
					return false;


			} else if(p1.getOrientation() == Piece.orientation_t.VERTICAL && p1.getAvailablePosition() == Piece.availablePosition_t.LEFT_RIGHT_UP) {

				if(newDirection == Direction.RIGHT && p1.getValuesPair().getFirst() == p2.getValuesPair().getFirst()) {
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 4, p1.getPositionPair().getSecond()));
					p2.setOrientation(Piece.orientation_t.HORIZONTAL);
					p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

				} else if(newDirection == Direction.RIGHT && p1.getValuesPair().getFirst() == p2.getValuesPair().getSecond()) {
					p2.flipValues();
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 4, p1.getPositionPair().getSecond()));
					p2.setOrientation(Piece.orientation_t.HORIZONTAL);
					p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

				} else if(newDirection == Direction.UP && p1.getValuesPair().getFirst() == p2.getValuesPair().getSecond()) {
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst(), p1.getPositionPair().getSecond() - 4));
					p2.setOrientation(Piece.orientation_t.VERTICAL);
					p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

				} else if(newDirection == Direction.UP && p1.getValuesPair().getFirst() == p2.getValuesPair().getFirst()) {
					p2.flipValues();
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst(), p1.getPositionPair().getSecond() - 4));
					p2.setOrientation(Piece.orientation_t.VERTICAL);
					p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

				} else if(newDirection == Direction.DOWN && p1.getValuesPair().getFirst() == p2.getValuesPair().getFirst()) {
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst(), p1.getPositionPair().getSecond() + 4));
					p2.setOrientation(Piece.orientation_t.VERTICAL);
					p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

				} else if(newDirection == Direction.DOWN && p1.getValuesPair().getFirst() == p2.getValuesPair().getSecond()) {
					p2.flipValues();
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst(), p1.getPositionPair().getSecond() + 4));
					p2.setOrientation(Piece.orientation_t.VERTICAL);
					p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);


				} else if(p1.getOrientation() == Piece.orientation_t.VERTICAL && p1.getAvailablePosition() == Piece.availablePosition_t.LEFT_RIGHT_DOWN) {

					if(newDirection == Direction.LEFT && p1.getValuesPair().getFirst() == p2.getValuesPair().getFirst()) {
						p2.flipValues();
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() - 4, p1.getPositionPair().getSecond()));
						p2.setOrientation(Piece.orientation_t.HORIZONTAL);
						p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

					} else if(newDirection == Direction.LEFT && p1.getValuesPair().getFirst() == p2.getValuesPair().getSecond()) {
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 4, p1.getPositionPair().getSecond()));
						p2.setOrientation(Piece.orientation_t.HORIZONTAL);
						p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

					} else if(newDirection == Direction.UP && p1.getValuesPair().getFirst() == p2.getValuesPair().getSecond()) {
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst(), p1.getPositionPair().getSecond() - 4));
						p2.setOrientation(Piece.orientation_t.VERTICAL);
						p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

					} else if(newDirection == Direction.UP && p1.getValuesPair().getFirst() == p2.getValuesPair().getFirst()) {
						p2.flipValues();
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst(), p1.getPositionPair().getSecond() - 4));
						p2.setOrientation(Piece.orientation_t.VERTICAL);
						p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

					} else if(newDirection == Direction.DOWN && p1.getValuesPair().getFirst() == p2.getValuesPair().getFirst()) {
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst(), p1.getPositionPair().getSecond() + 4));
						p2.setOrientation(Piece.orientation_t.VERTICAL);
						p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

					} else if(newDirection == Direction.DOWN && p1.getValuesPair().getFirst() == p2.getValuesPair().getSecond()) {
						p2.flipValues();
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst(), p1.getPositionPair().getSecond() + 4));
						p2.setOrientation(Piece.orientation_t.VERTICAL);
						p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

					} else
						return false;

				} else
					return false;
			}



			// -------------------------------------------------------------------------------------------------------------------------------------------

		} else if (p1.getOrientation() == Piece.orientation_t.HORIZONTAL && p1.getAvailablePosition() == Piece.availablePosition_t.RIGHT) {

			if(newOrientation == Piece.orientation_t.HORIZONTAL && newDirection == Direction.LEFT) {

				if(p1.getValuesPair().getSecond() == p2.getValuesPair().getFirst()) {
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 4, p1.getPositionPair().getSecond()));

				} else if(p1.getValuesPair().getSecond() == p2.getValuesPair().getSecond()) {
					p2.flipValues();
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 4, p1.getPositionPair().getSecond()));

				} else 
					return false;

				p2.setOrientation(Piece.orientation_t.HORIZONTAL);
				p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);
				p1.setAvailablePosition(Piece.availablePosition_t.NONE);

			} else {													// new orientation: Vertical 

				if(p1.getValuesPair().getSecond() == p2.getValuesPair().getFirst()) {

					if(newDirection == Direction.DOWN) {
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 1, p1.getPositionPair().getSecond() + 3));
						p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

					} else if(newDirection == Direction.UP) {
						p2.flipValues();
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 1, p1.getPositionPair().getSecond() - 3));
						p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

					} else if(newDirection == Direction.RIGHT) {
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 3, p1.getPositionPair().getSecond() + 1));
						p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

					} else
						return false;

				} else if(p1.getValuesPair().getSecond() == p2.getValuesPair().getSecond()) {


					if(newDirection == Direction.DOWN) {
						p2.flipValues();
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 1, p1.getPositionPair().getSecond() + 3));
						p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

					} else if(newDirection == Direction.UP) {
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 1, p1.getPositionPair().getSecond() - 3));
						p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

					} else if(newDirection == Direction.RIGHT) {
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 3, p1.getPositionPair().getSecond() - 1));
						p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

					} else
						return false;
				}


				p2.setOrientation(Piece.orientation_t.VERTICAL);
				p1.setAvailablePosition(Piece.availablePosition_t.NONE);
			}



		} else if (p1.getOrientation() == Piece.orientation_t.HORIZONTAL && p1.getAvailablePosition() == Piece.availablePosition_t.LEFT) {

			if(newOrientation == Piece.orientation_t.HORIZONTAL && newDirection == Direction.RIGHT) {

				if(p1.getValuesPair().getFirst() == p2.getValuesPair().getSecond()) {
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() - 4, p1.getPositionPair().getSecond()));

				} else if(p1.getValuesPair().getFirst() == p2.getValuesPair().getFirst()) {
					p2.flipValues();
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() - 4, p1.getPositionPair().getSecond()));

				} else 
					return false;

				p2.setOrientation(Piece.orientation_t.HORIZONTAL);
				p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

			} else {													// new orientation: Vertical 

				if(p1.getValuesPair().getFirst() == p2.getValuesPair().getFirst()) {

					if(newDirection == Direction.DOWN) {
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() - 1, p1.getPositionPair().getSecond() + 3));
						p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

					} else if(newDirection == Direction.UP) {
						p2.flipValues();
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() - 1, p1.getPositionPair().getSecond() - 3));
						p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

					} else if(newDirection == Direction.LEFT) {
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() - 3, p1.getPositionPair().getSecond() + 1));
						p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

					} else
						return false;

				} else if(p1.getValuesPair().getFirst() == p2.getValuesPair().getSecond()) {


					if(newDirection == Direction.DOWN) {
						p2.flipValues();
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() - 1, p1.getPositionPair().getSecond() + 3));
						p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

					} else if(newDirection == Direction.UP) {
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() - 1, p1.getPositionPair().getSecond() - 3));
						p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

					} else if(newDirection == Direction.LEFT) {
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() - 3, p1.getPositionPair().getSecond() - 1));
						p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

					} else
						return false;
				}


				p2.setOrientation(Piece.orientation_t.VERTICAL);
				p1.setAvailablePosition(Piece.availablePosition_t.NONE);
			}





		} else if (p1.getOrientation() == Piece.orientation_t.VERTICAL && p1.getAvailablePosition() == Piece.availablePosition_t.RIGHT) {

			if(newOrientation == Piece.orientation_t.VERTICAL && newDirection == Direction.DOWN) {

				if(p1.getValuesPair().getSecond() == p2.getValuesPair().getFirst()) {
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst(), p1.getPositionPair().getSecond() + 4));

				} else if(p1.getValuesPair().getSecond() == p2.getValuesPair().getSecond()) {
					p2.flipValues();
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst(), p1.getPositionPair().getSecond() + 4));

				} else 
					return false;

				p2.setOrientation(Piece.orientation_t.VERTICAL);
				p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);
				p1.setAvailablePosition(Piece.availablePosition_t.NONE);

			} else {													// New orientation: horizontal

				if(p1.getValuesPair().getSecond() == p2.getValuesPair().getFirst()) {

					if(newDirection == Direction.LEFT) {
						p2.flipValues();
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() - 3, p1.getPositionPair().getSecond() + 1));
						p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

					} else if(newDirection == Direction.RIGHT) {
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 3, p1.getPositionPair().getSecond() + 1));
						p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

					} else if(newDirection == Direction.DOWN) {
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 1, p1.getPositionPair().getSecond() + 3));
						p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

					} else
						return false;

				} else if(p1.getValuesPair().getSecond() == p2.getValuesPair().getSecond()) {


					if(newDirection == Direction.LEFT) {
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() - 3, p1.getPositionPair().getSecond() + 1));
						p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

					} else if(newDirection == Direction.RIGHT) {
						p2.flipValues();
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 3, p1.getPositionPair().getSecond() + 1));
						p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

					} else if(newDirection == Direction.DOWN) {
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() - 1, p1.getPositionPair().getSecond() + 3));
						p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

					} else
						return false;
				}


				p2.setOrientation(Piece.orientation_t.HORIZONTAL);
				p1.setAvailablePosition(Piece.availablePosition_t.NONE);
			}




		} else if (p1.getOrientation() == Piece.orientation_t.VERTICAL && p1.getAvailablePosition() == Piece.availablePosition_t.LEFT) {

			if(newOrientation == Piece.orientation_t.VERTICAL && newDirection == Direction.UP) {

				if(p1.getValuesPair().getFirst() == p2.getValuesPair().getSecond()) {
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst(), p1.getPositionPair().getSecond() - 4));

				} else if(p1.getValuesPair().getFirst() == p2.getValuesPair().getFirst()) {
					p2.flipValues();
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst(), p1.getPositionPair().getSecond() - 4));

				} else 
					return false;

				p2.setOrientation(Piece.orientation_t.VERTICAL);
				p2.setAvailablePosition(Piece.availablePosition_t.LEFT);
				p1.setAvailablePosition(Piece.availablePosition_t.NONE);

			} else {																		// New orientation: horizontal										 

				if(p1.getValuesPair().getFirst() == p2.getValuesPair().getFirst()) {

					if(newDirection == Direction.LEFT) {
						p2.flipValues();
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() - 3, p1.getPositionPair().getSecond() - 1));
						p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

					} else if(newDirection == Direction.RIGHT) {
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 3, p1.getPositionPair().getSecond() - 1));
						p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

					} else if(newDirection == Direction.UP) {
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 1, p1.getPositionPair().getSecond() - 3));
						p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

					} else
						return false;

				} else if(p1.getValuesPair().getFirst() == p2.getValuesPair().getSecond()) {


					if(newDirection == Direction.LEFT) {
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() - 3, p1.getPositionPair().getSecond() - 1));
						p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

					} else if(newDirection == Direction.RIGHT) {
						p2.flipValues();
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 3, p1.getPositionPair().getSecond() - 1));
						p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

					} else if(newDirection == Direction.UP) {
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() - 1, p1.getPositionPair().getSecond() - 3));
						p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

					} else 
						return false;
				}


				p2.setOrientation(Piece.orientation_t.HORIZONTAL);
				p1.setAvailablePosition(Piece.availablePosition_t.NONE);
			}
		} 





		/* Colocar peca no tabuleiro */
		try {
			board.putPieceOnTable(p1, p2);
			
		} catch(LinkingNotPossible e) {
			System.out.println(e.getMessage());
			
			p2.setOrientation(null);
			p2.setCenterPosition(null);
			
			if(p2.isDoubleValues()) {
				p2.setAvailablePosition(Piece.availablePosition_t.ALL);
			} else {
				p2.setAvailablePosition(Piece.availablePosition_t.LEFT_RIGHT);
			}
			
			return false;
		}

		return true;
	}
	
	
	
	
}
