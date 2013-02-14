package android.runningbeaver.commands;

public final class CRun implements ICommand {

	private Runnable object;

	public CRun(Runnable object) {
		this.object = object;
	}

	@Override
	public void run() {
		object.run();
	}
	
}
