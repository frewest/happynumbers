package se.fwestling.project.numbersequence.api;

import java.util.List;

import se.fwestling.project.api.WorkerThread;
import se.fwestling.project.numeric.util.Interval;

public interface NumberSequenceWorkerThread extends WorkerThread<Interval, List<Integer>> {
}
