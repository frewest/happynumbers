package se.fwestling.project.numeric.util;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class IntervalUtilTest {

	@Test
	public void testSplitThreePieces() {
		List<Interval> list;

		list = IntervalUtil.split(getInterval(1, 5), 3);
		assertIntervals(list, getInterval(1, 2), getInterval(3, 4), getInterval(5, 5));

		list = IntervalUtil.split(getInterval(0, 0), 3);
		assertIntervals(list, getInterval(0, 0));

		list = IntervalUtil.split(getInterval(0, 2), 3);
		assertIntervals(list, getInterval(0, 0), getInterval(1, 1), getInterval(2, 2));

		list = IntervalUtil.split(getInterval(2, 3), 3);
		assertIntervals(list, getInterval(2, 2), getInterval(3, 3));

		list = IntervalUtil.split(getInterval(2, 0), 3);
		assertIntervals(list, getInterval(2, 2), getInterval(1, 1), getInterval(0, 0));

		list = IntervalUtil.split(getInterval(0, 9), -3);
		assertIntervals(list, getInterval(0, 3), getInterval(4, 6), getInterval(7, 9));

		list = IntervalUtil.split(getInterval(9, 0), 3);
		assertIntervals(list, getInterval(9, 7), getInterval(6, 4), getInterval(3, 0));

		list = IntervalUtil.split(getInterval(0, -9), 3);
		assertIntervals(list, getInterval(0, -2), getInterval(-3, -5), getInterval(-6, -9));

		list = IntervalUtil.split(getInterval(-10, 10), 3);
		assertIntervals(list, getInterval(-10, -4), getInterval(-3, 3), getInterval(4, 10));

		list = IntervalUtil.split(getInterval(10, -10), 3);
		assertIntervals(list, getInterval(10, 4), getInterval(3, -3), getInterval(-4, -10));

		list = IntervalUtil.split(getInterval(1, 1000), 3);
		assertIntervals(list, getInterval(1, 334), getInterval(335, 667), getInterval(668, 1000));
	}

	@Test
	public void testSplitZeroPieces() {
		List<Interval> list;

		list = IntervalUtil.split(getInterval(0, 0), 0);
		assertIntervals(list, getInterval(0, 0));

		list = IntervalUtil.split(getInterval(-10, 10), 0);
		assertIntervals(list, getInterval(-10, 10));

		list = IntervalUtil.split(getInterval(0, 100), 0);
		assertIntervals(list, getInterval(0, 100));

		list = IntervalUtil.split(getInterval(100, 0), 0);
		assertIntervals(list, getInterval(100, 0));
	}

	@Test
	public void testSplitTwoPieces() {
		List<Interval> list;

		list = IntervalUtil.split(getInterval(0, 0), 2);
		assertIntervals(list, getInterval(0, 0));

		list = IntervalUtil.split(getInterval(-10, 10), 2);
		assertIntervals(list, getInterval(-10, 0), getInterval(1, 10));

		list = IntervalUtil.split(getInterval(100, 0), 2);
		assertIntervals(list, getInterval(100, 51), getInterval(50, 0));
	}

	@Test
	public void testSplitOnePieces() {
		List<Interval> list;

		list = IntervalUtil.split(getInterval(0, 0), 1);
		assertIntervals(list, getInterval(0, 0));

		list = IntervalUtil.split(getInterval(-10, 10), -1);
		assertIntervals(list, getInterval(-10, 10));

		list = IntervalUtil.split(getInterval(100, 0), 0);
		assertIntervals(list, getInterval(100, 0));
	}

	private void assertIntervals(List<Interval> results, Interval... allExpected) {
		assertEquals(allExpected.length, results.size());
		for (Interval expected : allExpected) {
			Interval result = results.remove(0);
			assertEquals(expected.getStart(), result.getStart());
			assertEquals(expected.getStop(), result.getStop());
		}
	}

	private Interval getInterval(int start, int stop) {
		return new Interval(start, stop);
	}
}
