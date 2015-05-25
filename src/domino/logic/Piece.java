package domino.logic;

public class Piece implements Comparable<Piece> {

	private Pair centerPosition;
	private Pair values;

	private Rotation rot;
	
	private boolean isFlipped = false;

	public Piece(Pair position, Pair values) {

		if(values.getSum() > 12)
			throw new IllegalArgumentException("Invalid piece: [" + values.getFirst() + ", " + values.getSecond() + "]");

		if(position != null && (position.getFirst() < 0 || position.getSecond() < 0))
			throw new IllegalArgumentException("Invalid position: [" + position.getFirst() + ", " + position.getSecond() + "]");

		this.centerPosition = position;
		this.values = values;
		this.rot = Rotation.NORTH;
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

	public final void setCenterPosition(Pair centerPosition) {
		this.centerPosition = centerPosition;
	}

	public final Pair getValuesPair() {
		return values;
	}

	public int getSum() {
		return values.getSum();
	}

	public final Rotation getRotation() {
		return rot;
	}

	public final void setRotaion(Rotation newRot) {
		this.rot = newRot;
	}

	public final boolean isFlipped() {
		return isFlipped;
	}
	
	
	

	public final void flipValues() {
		// Trocar valores (para manter a coerencia a nivel logico)
		this.values.swapValues();
		
		// Mas manter a mesma orientacao grafica (para manter a coerencia a nivel grafico)
		this.rot = Rotation.calculateRotation(this.rot, 2);
		
		// Notificar a troca
		this.isFlipped = !this.isFlipped;
	}

	public final boolean isDoubleValues() {
		return values.isSameValues();
	}


	public final boolean areMatchable(Piece p) {	
		return this.values.getFirst() == p.values.getFirst() || this.values.getFirst() == p.values.getSecond() || this.values.getSecond() == p.values.getFirst() || this.values.getSecond() == p.values.getSecond();
	}
	
	
	public String toString() {
		return values.toString();// + " -> State: " + state;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((centerPosition == null) ? 0 : centerPosition.hashCode());
		result = prime * result + ((values == null) ? 0 : values.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Piece))
			return false;
		Piece other = (Piece) obj;
		if (centerPosition == null) {
			if (other.centerPosition != null)
				return false;
		} else if (!centerPosition.equals(other.centerPosition))
			return false;
		if (values == null) {
			if (other.values != null)
				return false;
		} else if (!values.equals(other.values))
			return false;
		return true;
	}

	@Override
	public int compareTo(Piece o) {
		if(this.getSum() > o.getSum())
			return 1;
		
		else if(this.getSum() == o.getSum())
			return 0;
		
		else return -1;
	}

	public void inheritFrom(SocketPiece sp) {
		this.centerPosition = sp.getCenterPosition();
		this.rot = sp.getRot();
	}
	
	
}
