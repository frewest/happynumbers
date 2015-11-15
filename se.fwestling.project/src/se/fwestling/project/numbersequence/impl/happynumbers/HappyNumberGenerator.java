package se.fwestling.project.numbersequence.impl.happynumbers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import se.fwestling.project.numbersequence.api.NumberSequenceWorkerThread;
import se.fwestling.project.numbersequence.generic.AbstractThreadedNumberGenerator;
import se.fwestling.project.numeric.util.Interval;

public class HappyNumberGenerator extends AbstractThreadedNumberGenerator {

	private static final Logger LOG = LogManager.getLogger();

	public HappyNumberGenerator(Interval interval) {
		super(interval);

		if (LOG.isDebugEnabled()) {
			LOG.debug("Created happy number generator with interval: " + interval);
		}
	}

	@Override
	public NumberSequenceWorkerThread getWorkerThread(Interval interval) {
		String identifier = "HappyNumberWorkingThread" + interval;
		NumberSequenceWorkerThread thread = new HappyNumberWorkerThread(identifier);
		thread.setInput(interval);
		return thread;
	}

}
