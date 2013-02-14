package android.runningbeaver.engine;

import android.runningbeaver.objects.Surface;

public interface ITouchable {
	
	public void onTouch();
	public void touchable();
	public void unTouchable();
	public Surface getSurface();

}
