package android.runningbeaver.engine;

public class DurationInvoker extends AInvoker<Duration> {

	@Override
	public void run() {
		
		for (Duration duration : getListenerClone()) {
			if (duration == null) continue;
			if(!duration.isActive()) {
				duration.callback();
			}
		}
		
	}

}
