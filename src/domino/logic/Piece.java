package domino.logic;

public class Piece {

	public enum pieceState_t {
		ON_BOARD, ON_PLAYER, ON_DECK
	};

	public enum orientation_t {
		LEFT_TO_RIGHT, RIGHT_TO_LEFT, UP_TO_DOWN, DOWN_TO_UP
	};

	public enum availablePosition_t {
		LEFT, RIGHT, LEFT_RIGHT, ABOVE, BELOW, ABOVE_BELOW, ALL, NONE
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

	public final boolean isMatchable(Piece p) {

		if(this.availablePosition == availablePosition_t.NONE || p.availablePosition == availablePosition_t.NONE || this.availablePosition == p.availablePosition)
			return false;
		
		
		

		if((this.availablePosition == availablePosition_t.RIGHT || this.availablePosition == availablePosition_t.LEFT_RIGHT) && (values.getSecond() == p.values.getFirst() || values.getSecond() == p.values.getSecond()))
			return true;
		
		else if((this.availablePosition == availablePosition_t.LEFT || this.availablePosition == availablePosition_t.LEFT_RIGHT) && (values.getFirst() == p.values.getFirst() || values.getFirst() == p.values.getSecond()))
			return true;
		
		else if(this.isDoubleValues() && (values.getFirst() == p.values.getFirst() || values.getFirst() == p.values.getSecond()))
			return true;
		
		
		return false;
	}

	public String toString() {
		return values.toString() + " -> State: " + state;
	}
}
