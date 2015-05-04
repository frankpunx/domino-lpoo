package domino.ai;

import java.util.List;
import java.util.Random;

import domino.logic.Piece;

public class ChooseRandomPiece implements ArtificialInteligence {

	Random r = new Random();

	@Override
	/**
	 * @param playablePieces These are the pieces which can be made a move
	 * @param availablePieces These are the pieces from the player
	 * 
	 * @return Piece to play; otherwise, null, to indicate that the player must fetch a new piece.
	 */
	public Piece getPieceToPlay(List<Piece> playablePieces, List<Piece> availablePieces) {

		for (int i = 0; i < playablePieces.size(); i++) {
			for (int j = 0; j < availablePieces.size(); j++) {

				if(playablePieces.get(i).isMatchable(availablePieces.get(j))) {
					return availablePieces.remove(j);
				}
			}
		}

		return null;
	}

}
