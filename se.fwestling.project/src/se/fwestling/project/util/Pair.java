package se.fwestling.project.util;

public class Pair<K, J> {
	private K mFirst;
	private J mSecond;

	public Pair(K first, J second) {
		mFirst = first;
		mSecond = second;
	}

	public K getFirst() {
		return mFirst;
	}

	public J getSecond() {
		return mSecond;
	}

	public void setFirst(K first) {
		mFirst = first;
	}

	public void setSecond(J second) {
		mSecond = second;
	}

}
