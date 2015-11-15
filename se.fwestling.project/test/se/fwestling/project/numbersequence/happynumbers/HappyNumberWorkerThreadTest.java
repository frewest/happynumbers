package se.fwestling.project.numbersequence.happynumbers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import se.fwestling.project.numbersequence.impl.happynumbers.HappyNumberWorkerThread;
import se.fwestling.project.numeric.util.Interval;

public class HappyNumberWorkerThreadTest {
	@Test
	public void simpleIntervalTest() {
		List<Integer> expected = new ArrayList<>();
		expected.add(1);
		expected.add(7);
		expected.add(10);

		HappyNumberWorkerThread thread = new HappyNumberWorkerThread("test");

		Interval interval = new Interval(1, 10);
		assertNull(thread.getResult());
		thread.doWork(interval);
		assertNotNull(thread.getResult());

		assertEquals(expected, thread.getResult());
	}

	@Test
	public void oneInIntervalTest() {
		List<Integer> expected = new ArrayList<>();
		expected.add(1);

		HappyNumberWorkerThread thread = new HappyNumberWorkerThread("test");

		Interval interval = new Interval(1, 1);
		assertNull(thread.getResult());
		thread.doWork(interval);
		assertNotNull(thread.getResult());

		assertEquals(expected, thread.getResult());
	}
}
