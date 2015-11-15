package se.fwestling.project.numbersequence.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import se.fwestling.project.exception.FactoryException;
import se.fwestling.project.numbersequence.api.NumberSequenceGenerator;
import se.fwestling.project.numbersequence.api.ThreadableGenerator;
import se.fwestling.project.numbersequence.impl.happynumbers.HappyNumberGenerator;
import se.fwestling.project.numeric.util.Interval;

public class NumberSequenceGeneratorFactory {

	private static final Logger LOG = LogManager.getLogger();

	public static NumberSequenceGenerator getGenerator(NumberSequence type, Interval interval) throws FactoryException {

		if (LOG.isDebugEnabled()) {
			LOG.debug("Getting generator for type: " + type + ", interval: " + interval);
		}

		switch (type) {
		case HAPPY_NUMBERS:
			return new HappyNumberGenerator(interval);
		default:
			throw new FactoryException(NumberSequenceGeneratorFactory.class, "Unknown type: " + type);
		}
	}

	public static NumberSequenceGenerator getGenerator(NumberSequence type, Interval interval, int threads)
			throws FactoryException {
		NumberSequenceGenerator generator = getGenerator(type, interval);

		if (!(generator instanceof ThreadableGenerator)) {
			throw new FactoryException(NumberSequenceGeneratorFactory.class, "Type " + type + " is not threadable");
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug("Setting number of threads to: " + threads);
		}

		ThreadableGenerator threadableGenerator = (ThreadableGenerator) generator;
		threadableGenerator.setNumberOfThreads(threads);

		return generator;
	}
}
