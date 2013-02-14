package android.runningbeaver.commands;

import android.runningbeaver.engine.Game;

public class CPlayerRight implements ICommand {

	@Override
	public void run() {
		Game.getInstance().getPlayer().getMoveRight().run();
	}

}
