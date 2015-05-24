package domino.logic;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import domino.ai.ArtificialInteligence;
import domino.exceptions.LinkingNotPossible;
import domino.exceptions.NotEnoughPieces;

public class Game {

	private static final int MAX_PIECES_PER_PLAYER = 7;
	private static final int MAX_NO_PLAYERS = 4;

	private Board board = new Board();								/* Contains the state of the board, that is, all pieces already played by the players */
	private List<Player> players = new LinkedList<Player>();		/* Contains a list of the players */

	private List<Piece> availablePieces = new LinkedList<Piece>();	/* Contains a list of pieces that can be fetched by the players */

	private int turn = 0;

	public Game() {

		/* Creates all pieces available piece */
		for (int i = 0; i <= 6; i++) {
			for (int j = 0; j <= i; j++) {

				availablePieces.add(new Piece(i, j));
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
		for (int i = 0; i < MAX_PIECES_PER_PLAYER; i++) {
			
			try {
				this.giveAvailablePieceToPlayer(p);
				
			} catch (NotEnoughPieces e) {
				System.out.println("Error in Game::addPlayer method: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	// Será necessário ?????
	/* Pedido feito ao jogador para colocar a peça */
	/*
	public final void makeMove() {

		try {
			Piece p = players.get(turn).makePlayerMove(availablePieces);
			board.putPieceOnTable(p);

		} catch(UnsupportedOperationException e) {

			// Significa que a jogada foi invalida
			System.out.println(e.getMessage());
			return;

		} catch (LinkingNotPossible e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			turn = (turn + 1) % players.size();
		}

	}
	*/


	// Pedido feito pelo jogador para obter nova peca
	public final void giveAvailablePieceToPlayer(Player p) throws NotEnoughPieces {

		Piece piece = getPiece();

		if(piece == null)
			throw new NotEnoughPieces("Not possible to give a piece to the player.");
		else
			p.addPiece(piece);
	}


	// Obter nova peca
	private final Piece getPiece() {

		if(availablePieces.isEmpty()) {
			return null;
			
		} else {
			
			return availablePieces.remove(0);
		}
	}


	// Verificar se algum dos jogadores ja ganhou. Se sim, retorna-lo
	public final Player getWinnerPlayer() {
		
		Player p = null;
		
		for (Player player : players) {
			
			if(player.isWinner()) {
				p = player;
				break;
			}
		}
		
		return p;
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



	public String toString() {
		StringBuilder str = new StringBuilder();

		str.append(board.toString() + "\n");

		str.append("Available Pieces: ");

		for (Piece piece : availablePieces) {
			str.append(piece + " ");
		}

		str.append("\nPlayers: ");

		for (Player player : players) {
			str.append(player + "\n");
		}

		return str.toString();
	}
}
