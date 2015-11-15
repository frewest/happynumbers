package se.fwestling.project.numbersequence.generic;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import se.fwestling.project.numbersequence.api.NumberSequenceWorkerThread;
import se.fwestling.project.numbersequence.exception.SequenceCalculationException;
import se.fwestling.project.numeric.util.Interval;

public class AbstractThreadedNumberGeneratorTest {

	@Test(expected = IllegalArgumentException.class)
	public void invalidThreadNegativeNumberTest() throws SequenceCalculationException {
		Interval interval = new Interval(0, 100);
		TestThreadedNumberGenerator generator = new TestThreadedNumberGenerator(interval);
		generator.setNumberOfThreads(-10);
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidThreadNumber0Test() throws SequenceCalculationException {
		Interval interval = new Interval(0, 100);
		TestThreadedNumberGenerator generator = new TestThreadedNumberGenerator(interval);
		generator.setNumberOfThreads(0);
	}

	@Test
	public void multiThreadExecuteTest() throws SequenceCalculationException {
		Interval interval = new Interval(0, 100);
		TestThreadedNumberGenerator generator = new TestThreadedNumberGenerator(interval);
		generator.setNumberOfThreads(2);

		List<Integer> result = generator.calculateSequence();

		assertEquals(2, generator.getCreatedThreads().size());

		List<Integer> expected = new ArrayList<>();
		expected.add(0);
		expected.add(50);
		expected.add(51);
		expected.add(100);

		assertEquals(expected, result);
	}

	@Test
	public void defaultExecuteTest() throws SequenceCalculationException {
		Interval interval = new Interval(0, 100);
		TestThreadedNumberGenerator generator = new TestThreadedNumberGenerator(interval);

		List<Integer> result = generator.calculateSequence();

		assertEquals(1, generator.getCreatedThreads().size());

		List<Integer> expected = new ArrayList<>();
		expected.add(0);
		expected.add(100);

		assertEquals(expected, result);
	}

	private static class TestThreadedNumberGenerator extends AbstractThreadedNumberGenerator {

		private List<TestNumberSequenceWorkerThread> createdThreads = new ArrayList<>();

		public TestThreadedNumberGenerator(Interval interval) {
			super(interval);
		}

		public List<TestNumberSequenceWorkerThread> getCreatedThreads() {
			return createdThreads;
		}

		@Override
		public NumberSequenceWorkerThread getWorkerThread(Interval interval) {
			TestNumberSequenceWorkerThread thread = new TestNumberSequenceWorkerThread(interval.toString());
			thread.setInput(interval);
			createdThreads.add(thread);
			return thread;
		}

		private static class TestNumberSequenceWorkerThread extends AbstractNumberSequenceWorkerThread {

			public TestNumberSequenceWorkerThread(String identifier) {
				super(identifier);
			}

			@Override
			public void doWork(Interval input) {
				List<Integer> result = new ArrayList<Integer>();
				result.add(input.getStart());
				result.add(input.getStop());
				setResult(result);
			}

		}
	}

}
