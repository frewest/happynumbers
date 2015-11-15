package se.fwestling.project.numeric.util;

import se.fwestling.project.util.Pair;

public class Interval extends Pair<Integer, Integer> {

	public Interval(int start, int stop) {
		super(start, stop);
	}

	public int getStart() {
		return getFirst();
	}

	public int getStop() {
		return getSecond();
	}

	@Override
	public String toString() {
		return "[" + getStart() + "," + getStop() + "]";
	}

	public void addToStop(int i) {
		setSecond(getSecond() + i);
	}

	public void addToStart(int i) {
		setFirst(getFirst() + i);
	}

	public void reverse() {
		Integer first = getFirst();
		setFirst(getSecond());
		setSecond(first);
	}

}
