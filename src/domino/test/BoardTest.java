package domino.test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import domino.logic.*;

public class BoardTest {

	@Test
	public void testLinkPieceToLeftExtremity() {

		Board b = new Board();
		
		assertTrue(b.linkPieceToLeftExtremity(new Piece(2, 3)));
		assertTrue(b.linkPieceToLeftExtremity(new Piece(6, 2)));
		assertTrue(b.linkPieceToLeftExtremity(new Piece(6, 1)));
		
		List<Piece> l = new LinkedList<Piece>();
		l.add(new Piece(1, 6));
		l.add(new Piece(6, 2));
		l.add(new Piece(2, 3));
		
 		assertEquals(l, b.getAllPiecesOnTable());
		
		assertFalse(b.linkPieceToLeftExtremity(new Piece(6, 6)));
		assertEquals(3, b.getAllPiecesOnTable().size());
	}

	@Test
	public void testLinkPieceToRightExtremity() {
		
		Board b = new Board();
		
		assertTrue(b.linkPieceToRightExtremity(new Piece(2, 6)));
		assertTrue(b.linkPieceToRightExtremity(new Piece(6, 6)));
		assertTrue(b.linkPieceToRightExtremity(new Piece(5, 6)));
		
		List<Piece> l = new LinkedList<Piece>();
		l.add(new Piece(2, 6));
		l.add(new Piece(6, 6));
		l.add(new Piece(6, 5));
		
		assertEquals(l, b.getAllPiecesOnTable());

		assertFalse(b.linkPieceToRightExtremity(new Piece(1, 2)));
		assertEquals(3, b.getAllPiecesOnTable().size());
	}
	
	@Test
	public void testGetBoardExtremities() {
		
		Board b = new Board();
		
		b.linkPieceToLeftExtremity(new Piece(2, 3));
		b.linkPieceToLeftExtremity(new Piece(6, 2));
		b.linkPieceToLeftExtremity(new Piece(6, 1));
		
		List<Piece> l = new LinkedList<Piece>();
		l.add(new Piece(1, 6));
		l.add(new Piece(2, 3));
		
		assertEquals(l, b.getBoardExtremities());
	}

}
