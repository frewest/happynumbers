package se.fwestling.project.api;

public interface WorkerThread<J, K> extends Runnable {
	public void setInput(J input);

	public K getResult();

	public void startThread();

	public void joinThread() throws InterruptedException;

	public String getIdentifier();
}
