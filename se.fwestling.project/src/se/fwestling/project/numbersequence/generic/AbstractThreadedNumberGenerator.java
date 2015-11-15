package se.fwestling.project.numbersequence.generic;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import se.fwestling.project.numbersequence.api.NumberSequenceWorkerThread;
import se.fwestling.project.numbersequence.api.ThreadableGenerator;
import se.fwestling.project.numbersequence.exception.SequenceCalculationException;
import se.fwestling.project.numeric.util.Interval;
import se.fwestling.project.numeric.util.IntervalUtil;

public abstract class AbstractThreadedNumberGenerator extends AbstractNumberGenerator implements ThreadableGenerator {

	private static final Logger LOG = LogManager.getLogger();

	private int mNumberOfThreads = 1;

	public AbstractThreadedNumberGenerator(Interval interval) {
		super(interval);
	}

	@Override
	public void setNumberOfThreads(int threads) {
		if (threads < 1) {
			throw new IllegalArgumentException("Number of threads must be larger than 0");
		}
		mNumberOfThreads = threads;
	}

	public int getNumberOfThreads() {
		return mNumberOfThreads;
	}

	@Override
	public List<Integer> executeCalculation(Interval interval) throws SequenceCalculationException {
		List<Integer> result = new ArrayList<>();
		List<NumberSequenceWorkerThread> threads = new ArrayList<>(getNumberOfThreads());

		if (LOG.isDebugEnabled()) {
			LOG.debug("Creating " + getNumberOfThreads() + " threads");
		}

		for (Interval subInterval : IntervalUtil.split(interval, getNumberOfThreads())) {
			NumberSequenceWorkerThread worker = getWorkerThread(subInterval);
			worker.startThread();
			threads.add(worker);
		}

		for (NumberSequenceWorkerThread thread : threads) {
			try {
				thread.joinThread();
				List<Integer> calculated = thread.getResult();
				result.addAll(calculated);
			} catch (InterruptedException e) {
				throw new SequenceCalculationException(e);
			}
		}

		if (LOG.isTraceEnabled()) {
			LOG.trace("Final sequence: " + result);
		}

		return result;
	}

	public abstract NumberSequenceWorkerThread getWorkerThread(Interval interval);
}
