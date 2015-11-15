package se.fwestling.project.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PairTest {
	@Test
	public void pairTest() {
		Pair<String, Integer> pair = new Pair<String, Integer>("F", 1);
		assertEquals("F", pair.getFirst());
		assertEquals(Integer.valueOf(1), pair.getSecond());
	}

	@Test
	public void setPairTest() {
		Pair<String, Integer> pair = new Pair<String, Integer>("F", 1);
		assertEquals("F", pair.getFirst());
		assertEquals(Integer.valueOf(1), pair.getSecond());

		pair.setFirst("N");
		pair.setSecond(2);

		assertEquals("N", pair.getFirst());
		assertEquals(Integer.valueOf(2), pair.getSecond());
	}
}
