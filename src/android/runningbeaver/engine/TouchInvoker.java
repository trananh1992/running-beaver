package android.runningbeaver.engine;

import android.runningbeaver.objects.ITouchable;
import android.runningbeaver.objects.Surface;
import android.view.MotionEvent;

// ToDO: change layer pattern to multi layer

public final class TouchInvoker extends ALayerInvoker<ITouchable> {

	@Override
	public void run() {
		
		if (Game.getInstance().getTouchEventHandler().getMotionEventKey() == MotionEvent.ACTION_UP)
			return;
		
		Surface touchPoint = Game.getInstance().getTouchEventHandler().getSurface();
		
		boolean trigger = false;
		
		for (ITouchable touchObject : getListenerClone(ALayerInvoker.HIGH)) {
			if (touchObject == null) continue;
			if (touchObject.getSurface().contact(touchPoint)) {
				touchObject.onTouch();
				trigger = true;
			}
		}
		
		if (trigger) return;
		
		for (ITouchable touchObject : getListenerClone(ALayerInvoker.NORMAL)) {
			if (touchObject == null) continue;
			if (touchObject.getSurface().contact(touchPoint)) {
				touchObject.onTouch();
				trigger = true;
			}
		}
		
		if (trigger) return;
		
		for (ITouchable touchObject : getListenerClone(ALayerInvoker.LOW)) {
			if (touchObject == null) continue;
			if (touchObject.getSurface().contact(touchPoint)) {
				touchObject.onTouch();
			}
		}

	}

}

