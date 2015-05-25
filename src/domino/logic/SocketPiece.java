package domino.logic;

public class SocketPiece {

	private Pair centerPosition;
	private Rotation rot;
	
	public SocketPiece(int x, int y, Rotation r) {
		this.centerPosition = new Pair(x, y);
		this.rot = r;
	}
	
	public SocketPiece(int x, int y) {
		this(x, y, Rotation.NORTH);
	}

	public final Pair getCenterPosition() {
		return centerPosition;
	}

	public final void setCenterPosition(Pair centerPosition) {
		this.centerPosition = centerPosition;
	}

	public final Rotation getRot() {
		return rot;
	}

	public final void setRot(Rotation rot) {
		this.rot = rot;
	}
	
	
}
