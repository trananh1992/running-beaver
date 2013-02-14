package android.runningbeaver.skills;

import android.runningbeaver.engine.Config;
import android.runningbeaver.engine.Game;
import android.runningbeaver.models.AModel;
import android.runningbeaver.objects.Position;

public class Absorb extends Skill {

	public Absorb(AModel model, Position position) {
		super(model, position);
		
		// set skill attributes
		duration = Config.skill.absorb.duration;
		cooldown = Config.skill.absorb.cooldown;
		unlock = Config.skill.absorb.unlock;
	}

	@Override
	public void run() {
		Game.getInstance().getPlayer().setAbsorb(true);
		super.run();
	}

	@Override
	public void stop() {
		Game.getInstance().getPlayer().setAbsorb(false);
		super.stop();
	}

}
