package android.runningbeaver.objects;

import android.runningbeaver.engine.Config;
import android.runningbeaver.engine.Game;

public class BJump implements IBehavior {

	private int jumpStep = 0;

	@Override
	public void run() {
		
		if (jumpStep == 0 && Game.getInstance().getMenuConfig().getEffects()) {
			Game.getInstance().getSoundManager().playJumpSound();
		}

		int yMove = jumpStep
				* (Config.player.jumpSpeed / (Config.player.jumpSteps / 2))
				- Config.player.jumpSpeed;

		Game.getInstance()
				.getPlayer()
				.setDirection(
						new Direction(Game.getInstance().getPlayer()
								.getDirection().getDx(), yMove));

		if (jumpStep == Config.player.jumpSteps) {
			jumpStep = 0;
			Game.getInstance().getPlayer().setJumping(false);
		} else
			jumpStep++;

	}

}
