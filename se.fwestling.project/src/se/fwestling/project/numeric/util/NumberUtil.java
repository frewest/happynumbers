package se.fwestling.project.numeric.util;

public class NumberUtil {

	public static int squareAndSum(int... values) {
		return sum(square(values));
	}

	public static int[] square(int... values) {
		for (int i = 0; i < values.length; i++) {
			values[i] = Math.multiplyExact(values[i], values[i]);
		}
		return values;
	}

	public static int sum(int... values) {
		int sum = 0;
		for (int value : values) {
			sum = Math.addExact(sum, value);
		}
		return sum;
	}

	public static int[] splitToSeparateDigits(int number) {

		int length = getLength(number);
		int[] result = new int[length];

		for (int i = 1; i <= length; i++) {
			int d = number / 10;
			int k = number - d * 10;
			number = d;
			result[length - i] = k;
		}
		return result;
	}

	public static int getLength(int value) {
		if (value == 0) {
			return 1;
		} else {
			int count = 0;
			while (value != 0) {
				count++;
				value /= 10;
			}
			return count;
		}
	}
}
