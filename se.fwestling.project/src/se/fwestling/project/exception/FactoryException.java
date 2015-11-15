package se.fwestling.project.exception;

public class FactoryException extends Exception {
	private static final long serialVersionUID = 6366648133272312113L;

	public FactoryException(Class<?> factoryClass, String msg) {
		super(factoryClass.getSimpleName() + ": " + msg);
	}
}
