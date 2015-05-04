package domino.test;

import domino.logic.Board;
import domino.logic.Player;

public class Testes {

	private static Board board = new Board();

	public static void main(String[] args) {
		/* Add players to the game */
		board.addPlayer(new Player("ADC"));
		board.addPlayer(new Player("Francisco"));
		board.addPlayer(new Player("Rui"));
		//board.addPlayer(new Player("Diogo"));

		/* Show board status */
		System.out.println(board);
	}

}
