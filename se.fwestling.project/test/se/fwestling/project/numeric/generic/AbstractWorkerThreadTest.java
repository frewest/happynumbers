package se.fwestling.project.numeric.generic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import se.fwestling.project.generic.AbstractWorkerThread;

public class AbstractWorkerThreadTest {

	@Test
	public void testRun() throws InterruptedException {
		String result = "RESULT";

		TestWorkerThread thread = new TestWorkerThread("ID", result);
		assertNull(thread.getResult());
		thread.start();
		thread.join();
		assertEquals(result, thread.getResult());

	}

	@Test
	public void testSetInput() {
		String input;

		input = "INPUT";
		TestWorkerThread thread = new TestWorkerThread("ID", null);
		assertNull(thread.getInput());
		thread.setInput(input);
		assertEquals(input, thread.getInput());

		input = "NEWINPUT";
		thread.setInput(input);
		assertEquals(input, thread.getInput());
	}

	@Test
	public void testGetIdentifier() {
		String id = "IDENTIFIER";
		TestWorkerThread thread = new TestWorkerThread(id, null);
		assertEquals(id, thread.getIdentifier());
	}

	public static class TestWorkerThread extends AbstractWorkerThread<String, String> {

		private String mResult;

		public TestWorkerThread(String identifier, String result) {
			super(identifier);
			mResult = result;
		}

		@Override
		public void doWork(String input) {
			setResult(mResult);
		}

	}
}
