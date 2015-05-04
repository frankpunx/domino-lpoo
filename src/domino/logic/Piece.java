package domino.logic;

public class Piece {

	public enum pieceState_t {
		ON_BOARD, ON_PLAYER, ON_DECK
	};
	
	public enum orientation_t {
		HORIZONTAL, VERTICAL
	};
	
	public enum availablePosition_t {
		LEFT, RIGHT, BOTH, NONE
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
		this.availablePosition = availablePosition_t.BOTH;
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

	public String toString() {
		return values.toString() + " -> State: " + state;
	}
}
