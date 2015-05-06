package domino.logic;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import domino.ai.ArtificialInteligence;
import domino.exceptions.LinkingNotPossible;

public class Game {

	private static final int MAX_PIECES_PER_PLAYER = 7;
	private static final int MAX_NO_PLAYERS = 4;


	private Board board = new Board();								/* Contains the state of the board, that is, all pieces already played by the players */
	private List<Player> players = new LinkedList<Player>();		/* Contains a list of the players */

	private List<Piece> availablePieces = new LinkedList<Piece>();	/* Contains a list of pieces that */
	private List<Piece> playablePieces = new LinkedList<Piece>();

	private int turn = 0;

	public Game() {

		/* Add a new available piece */
		for (int i = 0; i <= 6; i++) {
			for (int j = 0; j <= i; j++) {

				Piece p = new Piece(i, j);

				availablePieces.add(p);
			}
		}

		/* Shuffle the available pieces */
		Collections.shuffle(availablePieces);
	}

	public final void addPlayer(String name, ArtificialInteligence ai) {

		/* Check maximum number of players */
		if(players.size() >= MAX_NO_PLAYERS)
			throw new UnsupportedOperationException("Maximum number of players reached.");

		/* Check available pieces */
		else if(availablePieces.size() < MAX_PIECES_PER_PLAYER)
			throw new UnsupportedOperationException("Not enough pieces available.");

		/* Add player to the game */
		Player p = new Player(name, this, ai);
		players.add(p);

		/* Giving pieces to player */
		int i = 0;						/* Number of pieces given to the player */
		while (i < MAX_PIECES_PER_PLAYER) {

			getNewAvailablePiece(p);

			i++;
		}
	}

	/* Pedido feito ao jogador para colocar a peça */
	public final void makeMove() {

		try {
			Piece p = players.get(turn).makePlayerMove(availablePieces);
			board.putPiece(p);

		} catch(UnsupportedOperationException e) {
			
			// Significa que a jogada foi invalida
			System.out.println(e.getMessage());
			return;
			
		} catch (LinkingNotPossible e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			turn = turn++ % MAX_NO_PLAYERS;
		}

	}

	/* Pedido feito pelo jogador para obter nova peça */
	public final void getNewAvailablePiece(Player p) {

		Piece piece = getPiece(Piece.pieceState_t.ON_PLAYER);

		if(piece == null)
			throw new UnsupportedOperationException("Not possible to give a piece to the player.");
		else
			p.addPiece(piece);
	}

	
	
	
	
	
	/* Obter nova peça */
	private final Piece getPiece(Piece.pieceState_t newState) {

		if(availablePieces.isEmpty()) {
			return null;
		} else {
			Piece piece = availablePieces.remove(0);
			piece.setState(newState);
			return piece;
		}
	}







	public final Board getBoard() {
		return board;
	}

	public final List<Player> getPlayers() {
		return players;
	}

	public final List<Piece> getAvailablePieces() {
		return availablePieces;
	}

	public final List<Piece> getPlayablePieces() {
		return playablePieces;
	}


	public String toString() {
		StringBuilder str = new StringBuilder();

		str.append(board.toString() + "\n");

		str.append("Available Pieces: ");
		
		for (Piece piece : availablePieces) {
			str.append(piece + " ");
		}
		
		str.append("\nPlayable Pieces: ");
		
		for (Piece piece : playablePieces) {
			str.append(piece + " ");
		}
		
		str.append("\nPlayers: ");
		
		for (Player player : players) {
			str.append(player + "\n");
		}

		return str.toString();
	}
}
