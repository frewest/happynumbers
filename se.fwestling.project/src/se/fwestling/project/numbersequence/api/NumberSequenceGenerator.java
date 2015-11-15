package se.fwestling.project.numbersequence.api;

import java.util.List;

import se.fwestling.project.numbersequence.exception.SequenceCalculationException;

public interface NumberSequenceGenerator {
	public List<Integer> calculateSequence() throws SequenceCalculationException;
}
