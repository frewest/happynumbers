package se.fwestling.project.main;

public class Main {

	public static void main(String[] args) throws Exception {
		PrintNumberSequenceLauncher launcher = new PrintNumberSequenceLauncher(args);
		if (launcher.shouldRun()) {
			launcher.run();
		}
	}

}
