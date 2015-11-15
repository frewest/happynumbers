package se.fwestling.project.main;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import se.fwestling.project.exception.FactoryException;
import se.fwestling.project.numbersequence.api.NumberSequenceGenerator;
import se.fwestling.project.numbersequence.exception.SequenceCalculationException;
import se.fwestling.project.numbersequence.factory.NumberSequence;
import se.fwestling.project.numbersequence.factory.NumberSequenceGeneratorFactory;
import se.fwestling.project.numeric.util.Interval;

public class PrintNumberSequenceLauncher {
	private static final Logger LOG = LogManager.getLogger();

	public static final String SEQUENCE_ARG = "sequence";
	public static final String START_ARG = "start";
	public static final String STOP_ARG = "stop";
	public static final String THREADS_ARG = "threads";
	public static final String HELP_ARG = "help";

	private NumberSequence mSequence = NumberSequence.HAPPY_NUMBERS;
	private int mStart = 1;
	private int mStop = 1000;
	private int mThreads = 1;
	private boolean mShouldRun = true;;

	public PrintNumberSequenceLauncher(String[] args) throws ParseException {
		Options options = new Options();
		options.addOption(HELP_ARG, false, "Shows this help info");
		options.addOption(SEQUENCE_ARG, true, "Name of the sequence to calculate");
		options.addOption(START_ARG, true, "Start calculate at");
		options.addOption(STOP_ARG, true, "Stop calculate at");
		options.addOption(THREADS_ARG, true, "Number of threads used to compute the sequence");

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = parser.parse(options, args);

		if (cmd.hasOption(HELP_ARG)) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp(" ", options);
			mShouldRun = false;
		}

		if (cmd.hasOption(SEQUENCE_ARG)) {
			String sequence = cmd.getOptionValue(SEQUENCE_ARG);
			mSequence = NumberSequence.valueOf(sequence.toUpperCase());
		}

		if (cmd.hasOption(START_ARG)) {
			String start = cmd.getOptionValue(START_ARG);
			mStart = Integer.valueOf(start);
		}

		if (cmd.hasOption(STOP_ARG)) {
			String stop = cmd.getOptionValue(STOP_ARG);
			mStop = Integer.valueOf(stop);
		}

		if (cmd.hasOption(THREADS_ARG)) {
			String theads = cmd.getOptionValue(THREADS_ARG);
			mThreads = Integer.valueOf(theads);
		}
	}

	public NumberSequence getNumberSeqence() {
		return mSequence;
	}

	public int getStart() {
		return mStart;
	}

	public int getStop() {
		return mStop;
	}

	public int getThreads() {
		return mThreads;
	}

	public boolean shouldRun() {
		return mShouldRun;
	}

	public void run() throws FactoryException, SequenceCalculationException {
		LOG.info("Launching number sequence generator printer");

		List<Integer> sequence = getSequence();
		LOG.info("Printing result to log");
		for (Integer number : sequence) {
			LOG.info(number);
		}
	}

	protected List<Integer> getSequence() throws FactoryException, SequenceCalculationException {
		Interval interval = new Interval(getStart(), getStop());

		StringBuilder sb = new StringBuilder();
		sb.append("Generating sequence for: ").append(getNumberSeqence());
		sb.append(" between ").append(interval);
		sb.append(" (").append(getThreads()).append(" threads)");
		LOG.info(sb);

		NumberSequenceGenerator generator = NumberSequenceGeneratorFactory.getGenerator(getNumberSeqence(), interval,
				getThreads());

		long start = System.currentTimeMillis();
		List<Integer> sequence = generator.calculateSequence();
		long stop = System.currentTimeMillis();

		LOG.info("Calculation completed in " + (stop - start) + " ms");
		return sequence;
	}

}
