package android.runningbeaver.commands;

import android.runningbeaver.engine.Game;

public class CPlayerLeft implements ICommand {

	@Override
	public void run() {
		Game.getInstance().getPlayer().getMoveLeft().run();
	}

}
