package android.runningbeaver.engine;

import android.runningbeaver.objects.IDrawable;

// ToDO: change layer pattern to multi layer

public final class DrawInvoker extends ALayerInvoker<IDrawable> {

	@Override
	public void run() {

		// draw by layers

		for (IDrawable drawObject : getListenerClone(ALayerInvoker.LOW)) {
			if (drawObject == null) continue;
			drawObject.draw(Game.getInstance().getCanvas());
		}

		for (IDrawable drawObject : getListenerClone(ALayerInvoker.NORMAL)) {
			if (drawObject == null) continue;
			drawObject.draw(Game.getInstance().getCanvas());
		}

		for (IDrawable drawObject : getListenerClone(ALayerInvoker.HIGH)) {
			if (drawObject == null) continue;
			drawObject.draw(Game.getInstance().getCanvas());
		}

	}

}
