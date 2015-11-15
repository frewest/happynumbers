package se.fwestling.project.numbersequence.impl.happynumbers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import se.fwestling.project.numbersequence.generic.AbstractNumberSequenceWorkerThread;
import se.fwestling.project.numeric.util.Interval;
import se.fwestling.project.numeric.util.NumberUtil;

public class HappyNumberWorkerThread extends AbstractNumberSequenceWorkerThread {

	public HappyNumberWorkerThread(String identifier) {
		super(identifier);
	}

	@Override
	public void doWork(Interval input) {
		List<Integer> result = new ArrayList<>();
		Set<Integer> set = new HashSet<Integer>();

		int start = input.getStart();
		int stop = input.getStop();

		if (start <= stop) {
			for (int i = start; i <= stop; i++) {
				if (isHappy(i, set)) {
					result.add(i);
				}
			}
		} else {
			// Loop reverse if start <= stop
			for (int i = start; i >= stop; i--) {
				if (isHappy(i, set)) {
					result.add(i);
				}
			}
		}

		setResult(result);
	}

	public boolean isHappy(int n, Set<Integer> set) {
		set.clear();
		// If back to same number as before -> infinity loop -> not happy
		while (!set.contains(n)) {
			set.add(n);
			int[] values = NumberUtil.splitToSeparateDigits(n);
			n = NumberUtil.squareAndSum(values);
			// If one -> is happy -> return true
			if (n == 1) {
				return true;
			}
		}

		return false;
	}

}
