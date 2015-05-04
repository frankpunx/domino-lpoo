package domino.logic;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Board {

	private static final int MAX_PIECES_PER_PLAYER = 7;
	private static final int MAX_NO_PLAYERS = 4;

	private List<Piece> availablePieces = new LinkedList<Piece>();
	private List<Piece> allPieces = new LinkedList<Piece>();
	private List<Player> availablePlayers = new LinkedList<Player>();
	
	private List<Piece> playablePieces = new LinkedList<Piece>();
	
	private Piece[][] board = new Piece[55][55];
	
	private Random r = new Random();

	public Board() {

		// Add a new piece to the board
		for (int i = 0; i <= 6; i++) {
			for (int j = 0; j <= i; j++) {

				Piece p = new Piece(i, j);

				availablePieces.add(p);
				allPieces.add(p);
			}
		}
	}

	public final List<Piece> getAvailablePieces() {
		return availablePieces;
	}
	public final List<Player> getAvailablePlayers() {
		return availablePlayers;
	}

	public final List<Piece> getAllPieces() {
		return allPieces;
	}
	
	

	public final void addPlayer(Player p) {

		/* Check maximum number of players */
		if(availablePlayers.size() >= MAX_NO_PLAYERS)
			throw new UnsupportedOperationException("Maximum number of players reached.");
		else if(availablePieces.size() < MAX_PIECES_PER_PLAYER)
			throw new UnsupportedOperationException("Not enough pieces available.");

		/* Add player to board */
		availablePlayers.add(p);

		/* Giving pieces to player */
		int i = 0;
		while (i < MAX_PIECES_PER_PLAYER) {

			if(! this.givePieceToPlayer(p))
				throw new UnsupportedOperationException("Not possible to give a piece to the player.");

			i++;
		}
	}

	public final boolean givePieceToPlayer(Player p) {
		
		// Check whether the player is playing in this game
		if(! availablePlayers.contains(p))
			return false;
		
		// Check whether there are enough pieces available
		else if(availablePieces.size() == 0) 
			return false;
			
		// Give a piece to the player
		else {
			
			int randPos = r.nextInt(availablePieces.size());

			Piece chosen = availablePieces.get(randPos);
			chosen.setState(Piece.pieceState_t.ON_PLAYER);
			
			p.addPiece(chosen);
			availablePieces.remove(randPos);
		}
		
		return true;
	}
	
	public String toString() {

		StringBuilder f = new StringBuilder();

		f.append("All Pieces: ");

		for (Piece piece : allPieces) {
			f.append(piece + " ");
		}

		f.append("\nAvailable Pieces: ");

		for (Piece piece : availablePieces) {
			f.append(piece + " ");
		}

		f.append("\n\nPlayers: \n");

		for (Player player : availablePlayers) {
			f.append(player + "\n");
		}
		
		f.append("\n\nBoard: \n");

		for (Piece[] piece : board) {
			
			for (Piece piece2 : piece) {
				f.append(piece2 + " ");
			}
			
			f.append('\n');
		}

		return f.toString();
	}
}
