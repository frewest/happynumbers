package se.fwestling.project.numbersequence.generic;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import se.fwestling.project.numbersequence.api.NumberSequenceGenerator;
import se.fwestling.project.numbersequence.exception.SequenceCalculationException;
import se.fwestling.project.numeric.util.Interval;

public abstract class AbstractNumberGenerator implements NumberSequenceGenerator {
	private static final Logger LOG = LogManager.getLogger();

	private Interval mInterval;

	public AbstractNumberGenerator(Interval interval) {
		mInterval = interval;
	}

	@Override
	public List<Integer> calculateSequence() throws SequenceCalculationException {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Start calculating sequence for interval: " + getInterval());
		}
		return executeCalculation(getInterval());
	}

	private Interval getInterval() {
		return mInterval;
	}

	public abstract List<Integer> executeCalculation(Interval interval) throws SequenceCalculationException;

}
