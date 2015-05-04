package domino.logic;

import java.util.LinkedList;
import java.util.List;

import domino.ai.ArtificialInteligence;

public class Game {

	private static final int MAX_PIECES_PER_PLAYER = 7;
	private static final int MAX_NO_PLAYERS = 4;

	private Board board = new Board();
	private List<Player> players = new LinkedList<Player>();
	
	private int turn = 0;
	
	public final void addPlayer(String name, ArtificialInteligence ai) {

		/* Check maximum number of players */
		if(players.size() >= MAX_NO_PLAYERS)
			throw new UnsupportedOperationException("Maximum number of players reached.");
		else if(board.getAvailablePieces().size() < MAX_PIECES_PER_PLAYER)
			throw new UnsupportedOperationException("Not enough pieces available.");

		/* Add player to the game */
		Player p = new Player(name, this, ai);
		players.add(p);

		/* Giving pieces to player */
		int i = 0;
		while (i < MAX_PIECES_PER_PLAYER) {

			Piece piece = board.getPiece();
			
			if(piece == null)
				throw new UnsupportedOperationException("Not possible to give a piece to the player.");
			else
				p.addPiece(piece);
			
			i++;
		}
	}

	/* Pedido feito pelo jogador para colocar a peça */
	public final void makeMove() {
		
		Piece p = players.get(turn).makeMove(board.getPlayablePieces());
		
		try {
			board.putPiece(p);
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		turn++;
	}
	
	public final Board getBoard() {
		return board;
	}

	public final List<Player> getPlayers() {
		return players;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(board.toString() + "\n");
		
		for (Player player : players) {
			str.append(player + "\n");
		}
		
		return str.toString();
	}
}
