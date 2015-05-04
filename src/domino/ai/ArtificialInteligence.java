package domino.ai;

import java.util.List;

import domino.logic.Piece;

public interface ArtificialInteligence {

	public Piece getPieceToPlay(List<Piece> playablePieces, List<Piece> availablePieces);
	
}
