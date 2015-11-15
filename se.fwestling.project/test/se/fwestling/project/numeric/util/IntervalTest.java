package se.fwestling.project.numeric.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IntervalTest {
	@Test
	public void addTest() {
		Interval interval = new Interval(-50, 50);

		assertEquals(Integer.valueOf(-50), interval.getFirst());
		assertEquals(Integer.valueOf(50), interval.getSecond());

		interval.addToStart(40);
		interval.addToStop(-40);

		assertEquals(-10, interval.getStart());
		assertEquals(10, interval.getStop());
	}

}
