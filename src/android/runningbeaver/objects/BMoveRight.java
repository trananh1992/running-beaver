package android.runningbeaver.objects;

import android.runningbeaver.engine.Config;
import android.runningbeaver.engine.Game;
import android.runningbeaver.models.MRightPlayer;

public class BMoveRight implements IBehavior {

	@Override
	public void run() {
		Game.getInstance().getPlayer()
				.setDirection(new Direction(Config.player.move, 0.0));
		Game.getInstance().getPlayer().setModel(new MRightPlayer());
	}

}
