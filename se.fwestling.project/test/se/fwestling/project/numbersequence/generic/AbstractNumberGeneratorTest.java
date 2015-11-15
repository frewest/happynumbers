package se.fwestling.project.numbersequence.generic;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import se.fwestling.project.numbersequence.exception.SequenceCalculationException;
import se.fwestling.project.numbersequence.generic.AbstractNumberGenerator;
import se.fwestling.project.numeric.util.Interval;

public class AbstractNumberGeneratorTest {
	@Test
	public void calculateSequenceTest() throws SequenceCalculationException {
		Interval interval = new Interval(60, 100);
		List<Integer> expected = new ArrayList<>();
		expected.add(78);

		TestNumberGenerator generator = new TestNumberGenerator(interval, expected);

		List<Integer> result = generator.executeCalculation(interval);

		assertEquals(expected, result);

	}

	private static class TestNumberGenerator extends AbstractNumberGenerator {

		private List<Integer> mResult;

		public TestNumberGenerator(Interval interval, List<Integer> result) {
			super(interval);
			mResult = result;
		}

		@Override
		public List<Integer> executeCalculation(Interval interval) throws SequenceCalculationException {
			return mResult;
		}

	}
}
