package se.fwestling.project.numbersequence.generic;

import java.util.List;

import se.fwestling.project.generic.AbstractWorkerThread;
import se.fwestling.project.numbersequence.api.NumberSequenceWorkerThread;
import se.fwestling.project.numeric.util.Interval;

public abstract class AbstractNumberSequenceWorkerThread extends AbstractWorkerThread<Interval, List<Integer>>
		implements NumberSequenceWorkerThread {

	public AbstractNumberSequenceWorkerThread(String identifier) {
		super(identifier);
	}

}
