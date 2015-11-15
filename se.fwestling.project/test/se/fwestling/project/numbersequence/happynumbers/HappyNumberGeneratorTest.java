package se.fwestling.project.numbersequence.happynumbers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import se.fwestling.project.numbersequence.api.NumberSequenceWorkerThread;
import se.fwestling.project.numbersequence.exception.SequenceCalculationException;
import se.fwestling.project.numbersequence.impl.happynumbers.HappyNumberGenerator;
import se.fwestling.project.numbersequence.impl.happynumbers.HappyNumberWorkerThread;
import se.fwestling.project.numeric.util.Interval;

public class HappyNumberGeneratorTest {

	private static List<Integer> m1to1000 = new ArrayList<>();

	@BeforeClass
	public static void beforeClass() {
		String str = "1, 7, 10, 13, 19, 23, 28, 31, 32, 44, 49, 68, 70, 79, 82, 86, 91, 94, 97, 100, 103, 109, 129, 130, 133, 139, 167, 176, 188, 190, 192, 193, 203, 208, 219, 226, 230, 236, 239, 262, 263, 280, 291, 293, 301, 302, 310, 313, 319, 320, 326, 329, 331, 338, 356, 362, 365, 367, 368, 376, 379, 383, 386, 391, 392, 397, 404, 409, 440, 446, 464, 469, 478, 487, 490, 496, 536, 556, 563, 565, 566, 608, 617, 622, 623, 632, 635, 637, 638, 644, 649, 653, 655, 656, 665, 671, 673, 680, 683, 694, 700, 709, 716, 736, 739, 748, 761, 763, 784, 790, 793, 802, 806, 818, 820, 833, 836, 847, 860, 863, 874, 881, 888, 899, 901, 904, 907, 910, 912, 913, 921, 923, 931, 932, 937, 940, 946, 964, 970, 973, 989, 998, 1000";
		for (String part : str.split(",")) {
			m1to1000.add(Integer.valueOf(part.trim()));
		}

	}

	@Test
	public void getResultNeg13to13Test() throws SequenceCalculationException {
		List<Integer> expected = new ArrayList<>();
		expected.add(-13);
		expected.add(-10);
		expected.add(-7);
		expected.add(-1);
		expected.add(1);
		expected.add(7);
		expected.add(10);
		expected.add(13);

		HappyNumberGenerator generator;
		List<Integer> result;
		Interval interval;

		interval = new Interval(-13, 13);
		generator = new HappyNumberGenerator(interval);

		result = generator.calculateSequence();
		assertEquals(expected, result);

	}

	@Test
	public void getResultNeg13toNeg7Test() throws SequenceCalculationException {
		List<Integer> expected = new ArrayList<>();
		expected.add(-13);
		expected.add(-10);
		expected.add(-7);

		HappyNumberGenerator generator;
		List<Integer> result;
		Interval interval;

		interval = new Interval(-13, -7);
		generator = new HappyNumberGenerator(interval);

		result = generator.calculateSequence();
		assertEquals(expected, result);

	}

	@Test
	public void getResult13to7Test() throws SequenceCalculationException {
		List<Integer> expected = new ArrayList<>();
		expected.add(13);
		expected.add(10);
		expected.add(7);

		HappyNumberGenerator generator;
		List<Integer> result;
		Interval interval;

		interval = new Interval(13, 7);
		generator = new HappyNumberGenerator(interval);

		result = generator.calculateSequence();
		assertEquals(expected, result);

	}

	@Test
	public void getResult7to13Test() throws SequenceCalculationException {
		List<Integer> expected = new ArrayList<>();
		expected.add(7);
		expected.add(10);
		expected.add(13);

		HappyNumberGenerator generator;
		List<Integer> result;
		Interval interval;

		interval = new Interval(7, 13);
		generator = new HappyNumberGenerator(interval);

		result = generator.calculateSequence();
		assertEquals(expected, result);

	}

	@Test
	public void getResult19to0Test() throws SequenceCalculationException {
		List<Integer> expected = new ArrayList<>();
		expected.add(19);
		expected.add(13);
		expected.add(10);
		expected.add(7);
		expected.add(1);

		HappyNumberGenerator generator;
		List<Integer> result;
		Interval interval;

		interval = new Interval(20, 0);
		generator = new HappyNumberGenerator(interval);

		result = generator.calculateSequence();
		assertEquals(expected, result);

		interval = new Interval(19, 0);
		generator = new HappyNumberGenerator(interval);

		result = generator.calculateSequence();
		assertEquals(expected, result);
	}

	@Test
	public void getResult0to19Test() throws SequenceCalculationException {
		List<Integer> expected = new ArrayList<>();
		expected.add(1);
		expected.add(7);
		expected.add(10);
		expected.add(13);
		expected.add(19);

		HappyNumberGenerator generator;
		List<Integer> result;
		Interval interval;

		interval = new Interval(0, 20);
		generator = new HappyNumberGenerator(interval);

		result = generator.calculateSequence();
		assertEquals(expected, result);

		interval = new Interval(0, 19);
		generator = new HappyNumberGenerator(interval);

		result = generator.calculateSequence();
		assertEquals(expected, result);
	}

	@Test
	public void getHappyNumberThreadTest() {
		Interval interval = new Interval(0, 100);

		HappyNumberGenerator generator = new HappyNumberGenerator(interval);
		NumberSequenceWorkerThread worker = generator.getWorkerThread(interval);

		assertTrue(worker instanceof HappyNumberWorkerThread);
	}

	@Test
	public void testResult1to1000Test() throws SequenceCalculationException {

		Interval interval = new Interval(1, 1000);

		HappyNumberGenerator generator = new HappyNumberGenerator(interval);

		assertEquals(m1to1000, generator.calculateSequence());
	}

	@Test
	public void testResult1to10003TwoThreadsTest() throws SequenceCalculationException {

		Interval interval = new Interval(1, 1000);

		HappyNumberGenerator generator = new HappyNumberGenerator(interval);
		generator.setNumberOfThreads(2);
		assertEquals(m1to1000, generator.calculateSequence());
	}

	@Test
	public void testResult1to10003ThreeThreadsTest() throws SequenceCalculationException {

		Interval interval = new Interval(1, 1000);

		HappyNumberGenerator generator = new HappyNumberGenerator(interval);
		generator.setNumberOfThreads(3);
		assertEquals(m1to1000, generator.calculateSequence());
	}

}
