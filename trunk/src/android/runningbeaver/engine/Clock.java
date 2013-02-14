package android.runningbeaver.engine;

public final class Clock {
	
	private long time;

	public Clock(long time) {
		this.time = time;
	}

	private String postfixTime() {
		if ((time/1000%60) < 10) return "0"+time/1000%60;
		else return ""+time/1000%60;
	}

	private String prefixTime() {
		if ((time/60000%60) < 10) return "0"+time/60000%60;
		else return ""+time/60000%60;
	}
	
	public String getText() {
		return prefixTime() + ":" + postfixTime();
	}
	
	public long getTime() {
		return time;
	}

}
