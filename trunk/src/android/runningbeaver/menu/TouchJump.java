package android.runningbeaver.menu;

import android.runningbeaver.engine.Game;
import android.runningbeaver.engine.TouchInvoker;
import android.runningbeaver.engine.TouchObject;
import android.runningbeaver.objects.Surface;

public class TouchJump extends TouchObject {

	public TouchJump(Surface surface) {
		super(surface, TouchInvoker.LOW);
	}

	@Override
	public void onTouch() {
		Game.getInstance().getPlayer().getJump().run();
	}

}
