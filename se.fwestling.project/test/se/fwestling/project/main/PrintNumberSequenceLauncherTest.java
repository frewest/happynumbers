package se.fwestling.project.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.UnrecognizedOptionException;
import org.junit.Test;

import se.fwestling.project.numbersequence.factory.NumberSequence;

public class PrintNumberSequenceLauncherTest {
	private Random mRandom = new Random();

	@Test
	public void getHelpTest() throws Exception {

		List<String> argsList = new ArrayList<>();
		argsList.add("-" + PrintNumberSequenceLauncher.HELP_ARG);

		String[] args = new String[argsList.size()];
		argsList.toArray(args);

		PrintNumberSequenceLauncher lanucher = new PrintNumberSequenceLauncher(args);

		assertFalse(lanucher.shouldRun());
	}

	@Test
	public void getSequenceTest() throws Exception {
		List<Integer> expected = new ArrayList<>();
		expected.add(1);
		expected.add(7);
		expected.add(10);
		expected.add(13);
		expected.add(19);

		List<String> argsList = new ArrayList<>();
		argsList.add("-" + PrintNumberSequenceLauncher.START_ARG);
		argsList.add(Integer.toString(1));
		argsList.add("-" + PrintNumberSequenceLauncher.STOP_ARG);
		argsList.add(Integer.toString(20));

		String[] args = new String[argsList.size()];
		argsList.toArray(args);

		PrintNumberSequenceLauncher lanucher = new PrintNumberSequenceLauncher(args);

		List<Integer> result = lanucher.getSequence();

		assertEquals(expected, result);
	}

	@Test(expected = UnrecognizedOptionException.class)
	public void setValuesTestInvalidOption() throws ParseException {
		List<String> argsList = new ArrayList<>();
		argsList.add("-invalid");

		String[] args = new String[argsList.size()];
		argsList.toArray(args);

		new PrintNumberSequenceLauncher(args);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setValuesTestThread() throws ParseException {
		List<String> argsList = new ArrayList<>();
		argsList.add("-" + PrintNumberSequenceLauncher.THREADS_ARG);
		argsList.add("123a");

		String[] args = new String[argsList.size()];
		argsList.toArray(args);

		new PrintNumberSequenceLauncher(args);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setValuesTestInvalidStop() throws ParseException {
		List<String> argsList = new ArrayList<>();
		argsList.add("-" + PrintNumberSequenceLauncher.STOP_ARG);
		argsList.add("123a");

		String[] args = new String[argsList.size()];
		argsList.toArray(args);

		new PrintNumberSequenceLauncher(args);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setValuesTestInvalidStart() throws ParseException {
		List<String> argsList = new ArrayList<>();
		argsList.add("-" + PrintNumberSequenceLauncher.START_ARG);
		argsList.add("123a");

		String[] args = new String[argsList.size()];
		argsList.toArray(args);

		new PrintNumberSequenceLauncher(args);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setValuesTestInvalidSequence() throws ParseException {
		List<String> argsList = new ArrayList<>();
		argsList.add("-" + PrintNumberSequenceLauncher.SEQUENCE_ARG);
		argsList.add("invalid_sequence");

		String[] args = new String[argsList.size()];
		argsList.toArray(args);

		new PrintNumberSequenceLauncher(args);
	}

	@Test
	public void setValuesTest() throws ParseException {
		for (int i = 0; i < 10000; i++) {
			int start = mRandom.nextInt();
			int stop = mRandom.nextInt();
			int threads = mRandom.nextInt();

			List<String> argsList = new ArrayList<>();
			argsList.add("-" + PrintNumberSequenceLauncher.SEQUENCE_ARG);
			argsList.add("happy_numbers");
			argsList.add("-" + PrintNumberSequenceLauncher.START_ARG);
			argsList.add(Integer.toString(start));
			argsList.add("-" + PrintNumberSequenceLauncher.STOP_ARG);
			argsList.add(Integer.toString(stop));
			argsList.add("-" + PrintNumberSequenceLauncher.THREADS_ARG);
			argsList.add(Integer.toString(threads));

			String[] args = new String[argsList.size()];
			argsList.toArray(args);

			PrintNumberSequenceLauncher lanucher = new PrintNumberSequenceLauncher(args);
			assertEquals(NumberSequence.HAPPY_NUMBERS, lanucher.getNumberSeqence());
			assertEquals(start, lanucher.getStart());
			assertEquals(stop, lanucher.getStop());
			assertEquals(threads, lanucher.getThreads());
		}
	}

	@Test
	public void defaultValuesTest() throws ParseException {
		String[] args = new String[] {};
		PrintNumberSequenceLauncher lanucher = new PrintNumberSequenceLauncher(args);
		assertEquals(NumberSequence.HAPPY_NUMBERS, lanucher.getNumberSeqence());
		assertEquals(1, lanucher.getStart());
		assertEquals(1000, lanucher.getStop());
		assertEquals(1, lanucher.getThreads());
	}
}
