package android.runningbeaver.engine;

import android.runningbeaver.objects.Surface;

public abstract class TouchObject implements ITouchable {

	protected Surface surface;
	protected int touchLayer;
	
	public TouchObject(Surface surface, int touchLayer) {
		this.surface = surface;
		this.touchLayer = touchLayer;
		touchable();
	}

	@Override
	public void touchable() {
		Game.getInstance().getTouchInvoker().register((android.runningbeaver.objects.ITouchable) this, touchLayer);
	}

	@Override
	public void unTouchable() {
		Game.getInstance().getTouchInvoker().unRegister((android.runningbeaver.objects.ITouchable) this);
	}
	
	@Override
	public Surface getSurface() {
		return surface;
	}

}
