package se.fwestling.project.numeric.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

public class NumberUtilTest {

	@Test
	public void sumAndSquareTest() {
		assertEquals(0, NumberUtil.squareAndSum(new int[] {}));
		assertEquals(1, NumberUtil.squareAndSum(new int[] { 1 }));
		assertEquals(1, NumberUtil.squareAndSum(new int[] { -1 }));
		assertEquals(2, NumberUtil.squareAndSum(new int[] { 1, 1 }));
		assertEquals(2, NumberUtil.squareAndSum(new int[] { -1, -1 }));
		assertEquals(18, NumberUtil.squareAndSum(new int[] { 3, 3 }));
		assertEquals(18, NumberUtil.squareAndSum(new int[] { -3, -3 }));
	}

	@Test
	public void squareTest() {
		assertArrayEquals(new int[] {}, NumberUtil.square(new int[] {}));
		assertArrayEquals(new int[] { 1 }, NumberUtil.square(new int[] { 1 }));
		assertArrayEquals(new int[] { 1 }, NumberUtil.square(new int[] { -1 }));
		assertArrayEquals(new int[] { 1, 1 }, NumberUtil.square(new int[] { 1, 1 }));
		assertArrayEquals(new int[] { 1, 1 }, NumberUtil.square(new int[] { -1, -1 }));
		assertArrayEquals(new int[] { 9, 9 }, NumberUtil.square(new int[] { 3, 3 }));
		assertArrayEquals(new int[] { 9, 9 }, NumberUtil.square(new int[] { -3, -3 }));
	}

	@Test
	public void sumTest() {
		assertEquals(0, NumberUtil.sum(new int[] {}));
		assertEquals(1, NumberUtil.sum(new int[] { 1 }));
		assertEquals(-1, NumberUtil.sum(new int[] { -1 }));
		assertEquals(2, NumberUtil.sum(new int[] { 1, 1 }));
		assertEquals(-2, NumberUtil.sum(new int[] { -1, -1 }));
		assertEquals(6, NumberUtil.sum(new int[] { 3, 3 }));
		assertEquals(-6, NumberUtil.sum(new int[] { -3, -3 }));
	}

	@Test
	public void getLengthTest() {
		assertEquals(1, NumberUtil.getLength(0));
		assertEquals(1, NumberUtil.getLength(1));
		assertEquals(1, NumberUtil.getLength(-1));
		assertEquals(3, NumberUtil.getLength(107));
		assertEquals(10, NumberUtil.getLength(Integer.MAX_VALUE));
		assertEquals(10, NumberUtil.getLength(Integer.MIN_VALUE));

	}

	@Test
	public void splitToSeparteDigitsSpecificDigitsTest() {
		testSeparate(0, 0);
		testSeparate(-1, -1);
		testSeparate(1, 1);
		testSeparate(12, 1, 2);
		testSeparate(-12, -1, -2);
		testSeparate(123123, 1, 2, 3, 1, 2, 3);
		testSeparate(Integer.MAX_VALUE, 2, 1, 4, 7, 4, 8, 3, 6, 4, 7);
		testSeparate(Integer.MIN_VALUE, -2, -1, -4, -7, -4, -8, -3, -6, -4, -8);

	}

	@Test
	public void splitToSeparteDigitsRandomDigitsTest() {
		Random random = new Random();
		for (int i = 0; i < 1000000; i++) {
			int value = random.nextInt();

			String valueAsString = Integer.toString(value);
			boolean negative;
			if (valueAsString.startsWith("-")) {
				valueAsString = valueAsString.substring(1);
				negative = true;
			} else {
				negative = false;
			}

			int[] expected = new int[valueAsString.length()];
			for (int x = 0; x < valueAsString.length(); x++) {
				int tmp = Character.getNumericValue(valueAsString.charAt(x));
				if (negative) {
					expected[x] = -tmp;
				} else {
					expected[x] = tmp;
				}
			}

			testSeparate(value, expected);

		}
	}

	private void testSeparate(int input, int... expected) {
		assertArrayEquals(expected, NumberUtil.splitToSeparateDigits(input));
	}
}
