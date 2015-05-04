package domino.test;

import domino.ai.ArtificialInteligence;
import domino.ai.ChooseRandomPiece;
import domino.logic.Board;

public class Testes {

	private static Board board = new Board();

	public static void main(String[] args) {
		
		ArtificialInteligence ai = new ChooseRandomPiece();
		
		/* Add players to the game */
		board.addPlayer("ADC", ai);
		board.addPlayer("Francisco", ai);
		board.addPlayer("Rui", ai);
		//board.addPlayer(new Player("Diogo"));

		/* Show board status */
		System.out.println(board);
	}

}
