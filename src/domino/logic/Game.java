package domino.logic;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import domino.ai.ArtificialInteligence;
import domino.exceptions.LinkingNotPossible;
import domino.logic.Piece.availablePosition_t;
import domino.logic.Piece.orientation_t;

public class Game {

	private static final int MAX_PIECES_PER_PLAYER = 7;
	private static final int MAX_NO_PLAYERS = 4;


	private Board board = new Board();								/* Contains the state of the board, that is, all pieces already played by the players */
	private List<Player> players = new LinkedList<Player>();		/* Contains a list of the players */

	private List<Piece> availablePieces = new LinkedList<Piece>();	/* Contains a list of pieces that can be fetched by the players */
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

	/**
	 * Links two pieces on the board. If such connection is not possible, the link is not established.
	 * @param p1 The piece which is going to be linked with p2. It is already in the table
	 * @param p2 The other piece involved in the process of linkage
	 * @param r Random object used to get a random orientation for the piece (valid if the player is not human). Furthermore, it accepts <null> if the positions are set by the player.
	 * @return <true> if was successful; <false> otherwise.
	 */
	public final boolean linkPieces(Piece p1, Piece p2, Piece.orientation_t newOrientation, Direction newDirection) {

		if(! p1.isMatchable(p2) || p1.getPositionPair() == null)
			return false;

		if (p1.getOrientation() == Piece.orientation_t.HORIZONTAL && p1.getAvailablePosition() == Piece.availablePosition_t.RIGHT) {

			if(newOrientation == Piece.orientation_t.HORIZONTAL) {

				if(p1.getValuesPair().getSecond() == p2.getValuesPair().getFirst()) {
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 4, p1.getPositionPair().getSecond()));

				} else if(p1.getValuesPair().getSecond() == p2.getValuesPair().getSecond()) {
					p2.flipValues();
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 4, p1.getPositionPair().getSecond()));

				} else 
					return false;

				p2.setOrientation(Piece.orientation_t.HORIZONTAL);
				p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

			} else {													// new orientation: Vertical 

				if(p1.getValuesPair().getSecond() == p2.getValuesPair().getFirst()) {

					if(newDirection == Direction.DOWN) {
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 1, p1.getPositionPair().getSecond() + 3));
						p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

					} else if(newDirection == Direction.UP) {
						p2.flipValues();
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 1, p1.getPositionPair().getSecond() - 3));
						p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

					} else
						return false;

				} else if(p1.getValuesPair().getSecond() == p2.getValuesPair().getSecond()) {


					if(newDirection == Direction.DOWN) {
						p2.flipValues();
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 1, p1.getPositionPair().getSecond() + 3));
						p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

					} else if(newDirection == Direction.UP) {
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 1, p1.getPositionPair().getSecond() - 3));
						p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

					} else
						return false;
				}


				p2.setOrientation(Piece.orientation_t.VERTICAL);
			}

		}









		if (p1.getOrientation() == Piece.orientation_t.HORIZONTAL && p1.getAvailablePosition() == Piece.availablePosition_t.LEFT) {

			if(newOrientation == Piece.orientation_t.HORIZONTAL) {

				if(p1.getValuesPair().getFirst() == p2.getValuesPair().getSecond()) {
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() - 4, p1.getPositionPair().getSecond()));

				} else if(p1.getValuesPair().getFirst() == p2.getValuesPair().getFirst()) {
					p2.flipValues();
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() - 4, p1.getPositionPair().getSecond()));

				} else 
					return false;

				p2.setOrientation(Piece.orientation_t.HORIZONTAL);
				p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

			} else {													// new orientation: Vertical 

				if(p1.getValuesPair().getFirst() == p2.getValuesPair().getFirst()) {

					if(newDirection == Direction.DOWN) {
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() - 1, p1.getPositionPair().getSecond() + 3));
						p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

					} else if(newDirection == Direction.UP) {
						p2.flipValues();
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() - 1, p1.getPositionPair().getSecond() - 3));
						p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

					} else
						return false;

				} else if(p1.getValuesPair().getFirst() == p2.getValuesPair().getSecond()) {


					if(newDirection == Direction.DOWN) {
						p2.flipValues();
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() - 1, p1.getPositionPair().getSecond() + 3));
						p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

					} else if(newDirection == Direction.UP) {
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() - 1, p1.getPositionPair().getSecond() - 3));
						p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

					} else
						return false;
				}


				p2.setOrientation(Piece.orientation_t.VERTICAL);
			}

		}





		if (p1.getOrientation() == Piece.orientation_t.VERTICAL && p1.getAvailablePosition() == Piece.availablePosition_t.RIGHT) {

			if(newOrientation == Piece.orientation_t.VERTICAL) {

				if(p1.getValuesPair().getSecond() == p2.getValuesPair().getFirst()) {
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst(), p1.getPositionPair().getSecond() + 4));

				} else if(p1.getValuesPair().getSecond() == p2.getValuesPair().getSecond()) {
					p2.flipValues();
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst(), p1.getPositionPair().getSecond() + 4));

				} else 
					return false;

				p2.setOrientation(Piece.orientation_t.VERTICAL);
				p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

			} else {													

				if(p1.getValuesPair().getSecond() == p2.getValuesPair().getFirst()) {

					if(newDirection == Direction.LEFT) {
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 3, p1.getPositionPair().getSecond() + 1));
						p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

					} else if(newDirection == Direction.RIGHT) {
						p2.flipValues();
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() - 3, p1.getPositionPair().getSecond() + 1));
						p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

					} else
						return false;

				} else if(p1.getValuesPair().getSecond() == p2.getValuesPair().getSecond()) {


					if(newDirection == Direction.LEFT) {
						p2.flipValues();
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 3, p1.getPositionPair().getSecond() + 1));
						p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

					} else if(newDirection == Direction.RIGHT) {
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() - 3, p1.getPositionPair().getSecond() + 1));
						p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

					} else
						return false;
				}


				p2.setOrientation(Piece.orientation_t.HORIZONTAL);
			}

		}

		

		if (p1.getOrientation() == Piece.orientation_t.VERTICAL && p1.getAvailablePosition() == Piece.availablePosition_t.LEFT) {

			if(newOrientation == Piece.orientation_t.VERTICAL) {

				if(p1.getValuesPair().getFirst() == p2.getValuesPair().getSecond()) {
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst(), p1.getPositionPair().getSecond() - 4));

				} else if(p1.getValuesPair().getFirst() == p2.getValuesPair().getFirst()) {
					p2.flipValues();
					p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst(), p1.getPositionPair().getSecond() - 4));

				} else 
					return false;

				p2.setOrientation(Piece.orientation_t.VERTICAL);
				p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

			} else {													 

				if(p1.getValuesPair().getFirst() == p2.getValuesPair().getFirst()) {

					if(newDirection == Direction.LEFT) {
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() - 3, p1.getPositionPair().getSecond() - 1));
						p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

					} else if(newDirection == Direction.RIGHT) {
						p2.flipValues();
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 3, p1.getPositionPair().getSecond() - 1));
						p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

					} else
						return false;

				} else if(p1.getValuesPair().getFirst() == p2.getValuesPair().getSecond()) {


					if(newDirection == Direction.LEFT) {
						p2.flipValues();
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() - 3, p1.getPositionPair().getSecond() - 1));
						p2.setAvailablePosition(Piece.availablePosition_t.LEFT);

					} else if(newDirection == Direction.RIGHT) {
						p2.setCenterPosition(new Pair(p1.getPositionPair().getFirst() + 3, p1.getPositionPair().getSecond() - 1));
						p2.setAvailablePosition(Piece.availablePosition_t.RIGHT);

					} else
						return false;
				}


				p2.setOrientation(Piece.orientation_t.HORIZONTAL);
			}

		}














































		p2.setState(Piece.pieceState_t.ON_BOARD);
		board.putPieceOnTable(p2);

		return true;
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
