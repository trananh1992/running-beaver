package android.runningbeaver.commands;

import android.runningbeaver.engine.Game;

public class CPlayerJump implements ICommand {

	@Override
	public void run() {
		Game.getInstance().getPlayer().setJumping(true);
	}

}
