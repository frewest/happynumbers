package se.fwestling.project.numeric.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IntervalUtil {

	private static final Logger LOG = LogManager.getLogger();

	public static List<Interval> split(Interval interval, int pieces) {

		pieces = Math.abs(pieces);

		List<Interval> subIntervals = new ArrayList<>(pieces);

		if (LOG.isDebugEnabled()) {
			LOG.debug("Splitting interval " + interval + " into " + pieces + " pieces");
		}

		// If 0 or 1 -> keep interval as is
		if (pieces == 0 || pieces == 1) {
			subIntervals.add(interval);
		} else {

			int start = interval.getStart();
			int stop = interval.getStop();

			int diff = stop - start;

			int absDiff = Math.abs(diff) + 1;

			// Calculate how many each thread should have
			int perBucket = absDiff / pieces;
			int rest = absDiff % pieces;

			if (LOG.isTraceEnabled()) {
				LOG.trace("Per bucket: " + perBucket + ", rest: " + rest);
			}

			boolean inverse = diff < 0;
			if (inverse) {
				// Switch start and stop and later interval will be reversed
				int tmp = stop;
				stop = start;
				start = tmp;
			}
			if (perBucket > 0) {
				Interval newInterval = null;
				while (start + perBucket - 1 <= stop) {
					int nextStart = start + perBucket;
					// If rest -> put one extra for each thread
					if (rest > 0) {
						nextStart += 1;
						rest--;
					}
					newInterval = new Interval(start, nextStart - 1);

					subIntervals.add(newInterval);
					start = nextStart;
				}

			} else {
				Interval newInterval = null;
				while (start <= stop) {
					newInterval = new Interval(start, start);
					subIntervals.add(newInterval);
					start++;
				}
			}

			if (inverse) {
				for (Interval calculatedInterval : subIntervals) {
					calculatedInterval.reverse();
				}
				Collections.reverse(subIntervals);
			}

		}
		if (LOG.isDebugEnabled()) {
			LOG.trace("Created sub intervals: " + subIntervals);
		}
		return subIntervals;
	}

}
