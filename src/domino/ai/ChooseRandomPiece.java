package domino.ai;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import domino.logic.*;

public class ChooseRandomPiece implements ArtificialInteligence {

	Random r = new Random();

	@Override
	public boolean makeMove(Player player) {

		List<Piece> playersPieces = player.getMyPieces();
		Board b = player.getGameAssociated().getBoard();

		// Escolher primeiro as pecas mais valiosas
		Collections.sort(playersPieces);

		for (Piece piece : playersPieces) {

			switch(r.nextInt(2)) {
			case 0:
				if(! b.linkPieceToLeftExtremity(piece) && ! b.linkPieceToRightExtremity(piece))
					continue;
				else {
					
					return false;
				}
				
			case 1:
				if(! b.linkPieceToRightExtremity(piece) && ! b.linkPieceToLeftExtremity(piece))
					continue;
				else
					return false;
			}
		}

		return false;
	}

}
