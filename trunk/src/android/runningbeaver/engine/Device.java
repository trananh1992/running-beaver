package android.runningbeaver.engine;

public final class Device {
	
	private int screenWidth;
	private int screenHeight;

	public Device(int screenWidth, int screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

}
