package android.runningbeaver.menu;

import android.runningbeaver.engine.ALayerInvoker;
import android.runningbeaver.engine.Game;
import android.runningbeaver.engine.TouchInvoker;
import android.runningbeaver.engine.TouchObject;
import android.runningbeaver.objects.Surface;

public class TouchRight extends TouchObject {

	public TouchRight(Surface surface) {
		super(surface, ALayerInvoker.LOW);
	}

	@Override
	public void onTouch() {
		Game.getInstance().getPlayer().getMoveRight().run();
	}

}
