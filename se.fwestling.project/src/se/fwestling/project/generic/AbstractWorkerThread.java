package se.fwestling.project.generic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import se.fwestling.project.api.WorkerThread;

public abstract class AbstractWorkerThread<J, K> extends Thread implements WorkerThread<J, K> {
	private static final Logger LOG = LogManager.getLogger();

	private J mInput = null;
	private K mResult = null;

	private String mIdentifier;

	public AbstractWorkerThread(String identifier) {
		super();

		mIdentifier = identifier;

		if (LOG.isDebugEnabled()) {
			LOG.debug("Created worker thread: " + getIdentifier());
		}
	}

	@Override
	public void run() {
		doWork(mInput);
	}

	public abstract void doWork(J input);

	@Override
	public void setInput(J input) {
		mInput = input;
	}

	public J getInput() {
		return mInput;
	}

	protected void setResult(K result) {
		mResult = result;
	}

	@Override
	public K getResult() {
		return mResult;
	}

	@Override
	public void startThread() {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Starting worker thread: " + getIdentifier());
			if (LOG.isTraceEnabled()) {
				LOG.trace("Input: " + getInput());
			}
		}
		start();
	}

	@Override
	public void joinThread() throws InterruptedException {
		join();
		if (LOG.isDebugEnabled()) {
			LOG.debug("Joined worker thread: " + getIdentifier());
			if (LOG.isTraceEnabled()) {
				LOG.trace("Result: " + getResult());
			}
		}
	}

	@Override
	public String getIdentifier() {
		return mIdentifier;
	}
}
