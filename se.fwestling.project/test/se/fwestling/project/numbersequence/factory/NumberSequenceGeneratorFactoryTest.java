package se.fwestling.project.numbersequence.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import se.fwestling.project.exception.FactoryException;
import se.fwestling.project.numbersequence.api.NumberSequenceGenerator;
import se.fwestling.project.numbersequence.factory.NumberSequence;
import se.fwestling.project.numbersequence.factory.NumberSequenceGeneratorFactory;
import se.fwestling.project.numbersequence.impl.happynumbers.HappyNumberGenerator;
import se.fwestling.project.numeric.util.Interval;

public class NumberSequenceGeneratorFactoryTest {

	@Test
	public void getHappyGeneratorTest() throws FactoryException {
		Interval interval = new Interval(0, 10);
		NumberSequenceGenerator generator = NumberSequenceGeneratorFactory.getGenerator(NumberSequence.HAPPY_NUMBERS,
				interval);

		assertTrue(generator instanceof HappyNumberGenerator);

		HappyNumberGenerator happyGenerator = (HappyNumberGenerator) generator;
		assertEquals(1, happyGenerator.getNumberOfThreads());
	}

	@Test
	public void getHappyGeneratorThreadTest() throws FactoryException {
		Interval interval = new Interval(0, 10);
		int threads = 10;

		NumberSequenceGenerator generator = NumberSequenceGeneratorFactory.getGenerator(NumberSequence.HAPPY_NUMBERS,
				interval, threads);

		assertTrue(generator instanceof HappyNumberGenerator);

		HappyNumberGenerator happyGenerator = (HappyNumberGenerator) generator;
		assertEquals(10, happyGenerator.getNumberOfThreads());
	}
}
