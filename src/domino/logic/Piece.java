package domino.logic;

import java.util.Random;

public class Piece {

	public enum pieceState_t {
		ON_BOARD, ON_PLAYER, ON_DECK
	};

	public enum orientation_t {
		LEFT_TO_RIGHT, UP_TO_DOWN
	};

	public enum availablePosition_t {
		LEFT, RIGHT, LEFT_AND_SIDES, RIGHT_AND_SIDES, LEFT_RIGHT, ALL, NONE
	};

	private Pair centerPosition;
	private Pair values;

	private pieceState_t state;
	private orientation_t orientation;
	private availablePosition_t availablePosition;

	public Piece(Pair position, Pair values) {

		if(values.getSum() > 12)
			throw new IllegalArgumentException("Invalid piece: [" + values.getFirst() + ", " + values.getSecond() + "]");

		if(position != null && (position.getFirst() < 0 || position.getSecond() < 0))
			throw new IllegalArgumentException("Invalid position: [" + position.getFirst() + ", " + position.getSecond() + "]");

		this.centerPosition = position;
		this.values = values;
		this.state = pieceState_t.ON_DECK;
		this.orientation = null;
		this.availablePosition = availablePosition_t.NONE;
	}

	public Piece(int f, int s) {

		this(null, new Pair(f, s));
	}

	public Piece(Pair values) {

		this(null, values);
	}


	public final Pair getPositionPair() {
		return centerPosition;
	}

	public final Pair getValuesPair() {
		return values;
	}

	public int getSum() {
		return values.getSum();
	}

	public final pieceState_t getState() {
		return state;
	}

	public final void setState(pieceState_t state) {
		this.state = state;
	}

	public final orientation_t getOrientation() {
		return orientation;
	}

	public final void setOrientation(orientation_t orientation) {
		this.orientation = orientation;
	}

	public final availablePosition_t getAvailablePosition() {
		return availablePosition;
	}

	public final void setAvailablePosition(availablePosition_t availablePosition) {
		this.availablePosition = availablePosition;
	}

	public final boolean isDoubleValues() {
		return values.getFirst() == values.getSecond();
	}

	// TODO
	public final boolean isMatchable(Piece p) {

		if(this.availablePosition == availablePosition_t.NONE || p.availablePosition == availablePosition_t.NONE || this.availablePosition == p.availablePosition)
			return false;

		return true;
	}
	 
	/**
	 * Links two pieces on the board. If such connection is not possible, the link is not established.
	 * @param p The piece which is going to be linked
	 * @param r Null, if the positions are set by the player
	 * @return <true> if was successful; <false> otherwise.
	 */
	public final boolean linkPieces(Piece p, Random r) {
		/*
		if(! this.isMatchable(p)) {
			return false;
		}
		 */

		if (this.orientation == orientation_t.LEFT_TO_RIGHT && this.availablePosition == availablePosition_t.RIGHT) {

			if (this.availablePosition == availablePosition_t.RIGHT)

				switch (r.nextInt(3)) {
				case 0:
					p.centerPosition = new Pair(this.centerPosition.getFirst() + 3, this.centerPosition.getSecond() - 1);
					break;
				case 1:
					p.centerPosition = new Pair(this.centerPosition.getFirst() + 4, this.centerPosition.getSecond());
					break;
				case 2:
					p.centerPosition = new Pair(this.centerPosition.getFirst() + 3, this.centerPosition.getSecond() + 1);
					break;

				}


			else if (this.availablePosition == availablePosition_t.LEFT)

				switch (r.nextInt(3)) {
				case 0:
					p.centerPosition = new Pair(this.centerPosition.getFirst() - 3, this.centerPosition.getSecond() - 1);
					break;
				case 1:
					p.centerPosition = new Pair(this.centerPosition.getFirst() - 4, this.centerPosition.getSecond());
					break;
				case 2:
					p.centerPosition = new Pair(this.centerPosition.getFirst() - 3, this.centerPosition.getSecond() + 1);
					break;

				}

		} else if (this.orientation == orientation_t.UP_TO_DOWN && this.availablePosition == availablePosition_t.LEFT) {

			if (this.availablePosition == availablePosition_t.LEFT)

				switch (r.nextInt(3)) {
				case 0:
					p.centerPosition = new Pair(this.centerPosition.getFirst() + 1, this.centerPosition.getSecond() - 3);
					break;
				case 1:
					p.centerPosition = new Pair(this.centerPosition.getFirst(), this.centerPosition.getSecond() - 4);
					break;
				case 2:
					p.centerPosition = new Pair(this.centerPosition.getFirst() - 1, this.centerPosition.getSecond() - 3);
					break;

				}

			else if (this.availablePosition == availablePosition_t.RIGHT)

				switch (r.nextInt(3)) {
				case 0:
					p.centerPosition = new Pair(this.centerPosition.getFirst() + 1, this.centerPosition.getSecond() + 3);
					break;
				case 1:
					p.centerPosition = new Pair(this.centerPosition.getFirst(), this.centerPosition.getSecond() + 4);
					break;
				case 2:
					p.centerPosition = new Pair(this.centerPosition.getFirst() - 1, this.centerPosition.getSecond() + 3);
					break;

				}

		}

		return true;
	}

	public String toString() {
		return values.toString() + " -> State: " + state;
	}
}
