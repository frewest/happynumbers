package se.fwestling.project.numbersequence.exception;

public class SequenceCalculationException extends Exception {

	private static final long serialVersionUID = 8616386882267797015L;

	public SequenceCalculationException(Exception exception) {
		super(exception);
	}
}
