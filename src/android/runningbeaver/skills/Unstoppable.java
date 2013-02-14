package android.runningbeaver.skills;

import android.runningbeaver.engine.Config;
import android.runningbeaver.engine.Game;
import android.runningbeaver.models.AModel;
import android.runningbeaver.objects.Position;

public class Unstoppable extends Skill {

	public Unstoppable(AModel model, Position position) {
		super(model, position);
		
		// set skill attributes
		duration = Config.skill.unstoppable.duration;
		cooldown = Config.skill.unstoppable.cooldown;
		unlock = Config.skill.unstoppable.unlock;
	}

	@Override
	public void run() {
		Game.getInstance().getPlayer().unCrashable();
		super.run();
	}

	@Override
	public void stop() {
		Game.getInstance().getPlayer().crashable();
	}

}
