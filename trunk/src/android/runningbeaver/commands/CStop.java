package android.runningbeaver.commands;

public final class CStop implements ICommand {

	private IStopable object;

	public CStop(IStopable object) {
		this.object = object;
	}

	@Override
	public void run() {
		object.stop();
	}
	
}
