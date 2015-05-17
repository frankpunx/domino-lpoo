package domino.test;

import static org.junit.Assert.*;

import org.junit.Test;

import domino.logic.*;

public class PairTest {

	@Test
	public void testGetSetValues() {

		Pair p = new Pair(3, 4);

		assertEquals(3, p.getFirst());
		assertEquals(4, p.getSecond());

		p.setFirst(10);
		p.setSecond(22);

		assertEquals(10, p.getFirst());
		assertEquals(22, p.getSecond());
	}


	@Test
	public void testGetSum() {

		Pair p = new Pair(1, 2);

		assertEquals(3, p.getSum());

		p = new Pair(10, 50);

		assertEquals(60, p.getSum());
	}

	@Test
	public void testIsSameValues() {

		Pair p = new Pair(6, 6);

		assertTrue(p.isSameValues());

		p = new Pair(8, 6);

		assertFalse(p.isSameValues());
	}

	@Test
	public void testSwapValues() {
		
		Pair p = new Pair(4, 9);

		assertEquals(4, p.getFirst());
		assertEquals(9, p.getSecond());

		p.swapValues();

		assertEquals(9, p.getFirst());
		assertEquals(4, p.getSecond());
	}

	@Test
	public void testToString() {
		Pair p = new Pair(1, 2);
		
		assertEquals("[1, 2]", p.toString());
	}

}
