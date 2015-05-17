package domino.test;

import domino.ai.ArtificialInteligence;
import domino.ai.ChooseRandomPiece;
import domino.logic.Game;

public class Testes {

	private static Game game = new Game();

	public static void main(String[] args) {

		ArtificialInteligence ai = new ChooseRandomPiece();

		/* Add players to the game */
		game.addPlayer("ADC", ai);
		game.addPlayer("Francisco", ai);
		game.addPlayer("Rui", ai);
/*
		for (int i = 0; i < 10; i++) {

			
			//Show board status 
			System.out.println(game);
			game.makeMove();
			
			System.out.println("-------------------------");
		}
	*/
		/* Show board status */
		System.out.println(game);

	}

}
