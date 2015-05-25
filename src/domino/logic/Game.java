package domino.logic;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import domino.ai.ArtificialInteligence;
import domino.exceptions.LinkingNotPossible;
import domino.exceptions.NotEnoughPieces;

public class Game {

	private static final int ROOT_PIECE_X_COORD = 0;
	private static final int ROOT_PIECE_Y_COORD = 0;
	private static final int MAX_PIECES_PER_PLAYER = 7;
	private static final int MAX_NO_PLAYERS = 4;

	private Board board = new Board();								/* Contains the state of the board, that is, all pieces already played by the players */
	private List<Player> players = new LinkedList<Player>();		/* Contains a list of the players */

	private List<Piece> availablePieces = new LinkedList<Piece>();	/* Contains a list of pieces that can be fetched by the players */

	private int turn = 0;

	public Game() {

		/* Creates all pieces available piece. NOTE: first <= second */
		for (int i = 0; i <= 6; i++) {
			for (int j = 0; j <= i; j++) {

				availablePieces.add(new Piece(j, i));
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



	// get(0) -> Pecas passiveis de serem colocadas a esquerda
	// get(1) -> Pecas passiveis de serem colocadas a direita
	public final List<List<SocketPiece>> getAvailableMoves(Piece piece) {

		List<List<SocketPiece>> possibleMoves = new LinkedList<List<SocketPiece>>();

		// Obter as extremidades disponiveis para jogar
		List<Piece> boardExtremities = this.board.getBoardExtremities();

		// Verificar se esta é a primeira jogada
		if(boardExtremities.size() == 0) {
			possibleMoves.get(0).add(new SocketPiece(ROOT_PIECE_X_COORD, ROOT_PIECE_Y_COORD, Rotation.NORTH));
			possibleMoves.get(0).add(new SocketPiece(ROOT_PIECE_X_COORD, ROOT_PIECE_Y_COORD, Rotation.WEST));

		} else {
			possibleMoves.add(getLeftPossibleCombinations(piece));
			possibleMoves.add(getRightPossibleCombinations(piece));
		}
		
		return possibleMoves;
	}



	public final List<SocketPiece> getLeftPossibleCombinations(Piece p) {

		List<SocketPiece> possibleMoves = new LinkedList<SocketPiece>();
		Piece leftPieceOnBoard = board.getBoardExtremities().get(0);

		// Se p for double
		if(p.isDoubleValues() && p.getValuesPair().getFirst() == leftPieceOnBoard.getValuesPair().getFirst()) {

			if(board.getLeftExtremityOrientation() == Rotation.NORTH) {

				possibleMoves.add(new SocketPiece(leftPieceOnBoard.getPositionPair().getFirst(), 
						leftPieceOnBoard.getPositionPair().getSecond() - 3, 
						Rotation.EAST));

			} else if(board.getLeftExtremityOrientation() == Rotation.EAST) {

				possibleMoves.add(new SocketPiece(leftPieceOnBoard.getPositionPair().getFirst() + 3, 
						leftPieceOnBoard.getPositionPair().getSecond(), 
						Rotation.SOUTH));

			} else if(board.getLeftExtremityOrientation() == Rotation.SOUTH) {

				possibleMoves.add(new SocketPiece(leftPieceOnBoard.getPositionPair().getFirst(), 
						leftPieceOnBoard.getPositionPair().getSecond() + 3, 
						Rotation.WEST));

			} else if(board.getLeftExtremityOrientation() == Rotation.WEST) {

				possibleMoves.add(new SocketPiece(leftPieceOnBoard.getPositionPair().getFirst() - 3, 
						leftPieceOnBoard.getPositionPair().getSecond(), 
						Rotation.NORTH));
			}

		} else {

			if(p.getValuesPair().getSecond() == leftPieceOnBoard.getValuesPair().getFirst())
				p.flipValues();

			if(p.getValuesPair().getFirst() == leftPieceOnBoard.getValuesPair().getFirst()) {

				if(board.getLeftExtremityOrientation() == Rotation.NORTH) {

					possibleMoves.add(new SocketPiece(leftPieceOnBoard.getPositionPair().getFirst() - 3, 
							leftPieceOnBoard.getPositionPair().getSecond() - 1, 
							Rotation.EAST));

					possibleMoves.add(new SocketPiece(leftPieceOnBoard.getPositionPair().getFirst(), 
							leftPieceOnBoard.getPositionPair().getSecond() - 4, 
							Rotation.SOUTH));

					possibleMoves.add(new SocketPiece(leftPieceOnBoard.getPositionPair().getFirst() + 3, 
							leftPieceOnBoard.getPositionPair().getSecond() - 1, 
							Rotation.WEST));

				} else if(board.getLeftExtremityOrientation() == Rotation.EAST) {

					possibleMoves.add(new SocketPiece(leftPieceOnBoard.getPositionPair().getFirst() + 1, 
							leftPieceOnBoard.getPositionPair().getSecond() + 3, 
							Rotation.NORTH));

					possibleMoves.add(new SocketPiece(leftPieceOnBoard.getPositionPair().getFirst() + 4, 
							leftPieceOnBoard.getPositionPair().getSecond(), 
							Rotation.WEST));

					possibleMoves.add(new SocketPiece(leftPieceOnBoard.getPositionPair().getFirst() + 1, 
							leftPieceOnBoard.getPositionPair().getSecond() - 3, 
							Rotation.SOUTH));

				} else if(board.getLeftExtremityOrientation() == Rotation.SOUTH) {

					possibleMoves.add(new SocketPiece(leftPieceOnBoard.getPositionPair().getFirst() - 3, 
							leftPieceOnBoard.getPositionPair().getSecond() + 1, 
							Rotation.EAST));

					possibleMoves.add(new SocketPiece(leftPieceOnBoard.getPositionPair().getFirst(), 
							leftPieceOnBoard.getPositionPair().getSecond() + 4, 
							Rotation.NORTH));

					possibleMoves.add(new SocketPiece(leftPieceOnBoard.getPositionPair().getFirst() + 3, 
							leftPieceOnBoard.getPositionPair().getSecond() + 1, 
							Rotation.WEST));

				} else if(board.getLeftExtremityOrientation() == Rotation.WEST) {

					possibleMoves.add(new SocketPiece(leftPieceOnBoard.getPositionPair().getFirst() - 1, 
							leftPieceOnBoard.getPositionPair().getSecond() + 3, 
							Rotation.NORTH));

					possibleMoves.add(new SocketPiece(leftPieceOnBoard.getPositionPair().getFirst() - 4, 
							leftPieceOnBoard.getPositionPair().getSecond(), 
							Rotation.EAST));

					possibleMoves.add(new SocketPiece(leftPieceOnBoard.getPositionPair().getFirst() - 1, 
							leftPieceOnBoard.getPositionPair().getSecond() - 3, 
							Rotation.SOUTH));

				}
			}
		}

		return possibleMoves;
	}
	
	
	public final List<SocketPiece> getRightPossibleCombinations(Piece p) {

		List<SocketPiece> possibleMoves = new LinkedList<SocketPiece>();
		Piece rightPieceOnBoard = board.getBoardExtremities().get(1);

		// Se p for double
		if(p.isDoubleValues() && p.getValuesPair().getFirst() == rightPieceOnBoard.getValuesPair().getFirst()) {

			if(board.getRightExtremityOrientation() == Rotation.NORTH) {

				possibleMoves.add(new SocketPiece(rightPieceOnBoard.getPositionPair().getFirst(), 
						rightPieceOnBoard.getPositionPair().getSecond() - 3, 
						Rotation.EAST));

			} else if(board.getRightExtremityOrientation() == Rotation.EAST) {

				possibleMoves.add(new SocketPiece(rightPieceOnBoard.getPositionPair().getFirst() + 3, 
						rightPieceOnBoard.getPositionPair().getSecond(), 
						Rotation.SOUTH));

			} else if(board.getRightExtremityOrientation() == Rotation.SOUTH) {

				possibleMoves.add(new SocketPiece(rightPieceOnBoard.getPositionPair().getFirst(), 
						rightPieceOnBoard.getPositionPair().getSecond() + 3, 
						Rotation.WEST));

			} else if(board.getRightExtremityOrientation() == Rotation.WEST) {

				possibleMoves.add(new SocketPiece(rightPieceOnBoard.getPositionPair().getFirst() - 3, 
						rightPieceOnBoard.getPositionPair().getSecond(), 
						Rotation.NORTH));
			}

		} else {

			if(p.getValuesPair().getSecond() == rightPieceOnBoard.getValuesPair().getFirst())
				p.flipValues();

			if(p.getValuesPair().getFirst() == rightPieceOnBoard.getValuesPair().getFirst()) {

				if(board.getRightExtremityOrientation() == Rotation.NORTH) {

					possibleMoves.add(new SocketPiece(rightPieceOnBoard.getPositionPair().getFirst() - 3, 
							rightPieceOnBoard.getPositionPair().getSecond() - 1, 
							Rotation.EAST));

					possibleMoves.add(new SocketPiece(rightPieceOnBoard.getPositionPair().getFirst(), 
							rightPieceOnBoard.getPositionPair().getSecond() - 4, 
							Rotation.SOUTH));

					possibleMoves.add(new SocketPiece(rightPieceOnBoard.getPositionPair().getFirst() + 3, 
							rightPieceOnBoard.getPositionPair().getSecond() - 1, 
							Rotation.WEST));

				} else if(board.getRightExtremityOrientation() == Rotation.EAST) {

					possibleMoves.add(new SocketPiece(rightPieceOnBoard.getPositionPair().getFirst() + 1, 
							rightPieceOnBoard.getPositionPair().getSecond() + 3, 
							Rotation.NORTH));

					possibleMoves.add(new SocketPiece(rightPieceOnBoard.getPositionPair().getFirst() + 4, 
							rightPieceOnBoard.getPositionPair().getSecond(), 
							Rotation.WEST));

					possibleMoves.add(new SocketPiece(rightPieceOnBoard.getPositionPair().getFirst() + 1, 
							rightPieceOnBoard.getPositionPair().getSecond() - 3, 
							Rotation.SOUTH));

				} else if(board.getRightExtremityOrientation() == Rotation.SOUTH) {

					possibleMoves.add(new SocketPiece(rightPieceOnBoard.getPositionPair().getFirst() - 3, 
							rightPieceOnBoard.getPositionPair().getSecond() + 1, 
							Rotation.EAST));

					possibleMoves.add(new SocketPiece(rightPieceOnBoard.getPositionPair().getFirst(), 
							rightPieceOnBoard.getPositionPair().getSecond() + 4, 
							Rotation.NORTH));

					possibleMoves.add(new SocketPiece(rightPieceOnBoard.getPositionPair().getFirst() + 3, 
							rightPieceOnBoard.getPositionPair().getSecond() + 1, 
							Rotation.WEST));

				} else if(board.getRightExtremityOrientation() == Rotation.WEST) {

					possibleMoves.add(new SocketPiece(rightPieceOnBoard.getPositionPair().getFirst() - 1, 
							rightPieceOnBoard.getPositionPair().getSecond() + 3, 
							Rotation.NORTH));

					possibleMoves.add(new SocketPiece(rightPieceOnBoard.getPositionPair().getFirst() - 4, 
							rightPieceOnBoard.getPositionPair().getSecond(), 
							Rotation.EAST));

					possibleMoves.add(new SocketPiece(rightPieceOnBoard.getPositionPair().getFirst() - 1, 
							rightPieceOnBoard.getPositionPair().getSecond() - 3, 
							Rotation.SOUTH));

				}
			}
		}

		return possibleMoves;
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
