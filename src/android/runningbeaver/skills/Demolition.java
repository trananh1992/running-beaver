package android.runningbeaver.skills;

import java.util.ArrayList;

import android.runningbeaver.engine.Config;
import android.runningbeaver.engine.Game;
import android.runningbeaver.models.AModel;
import android.runningbeaver.objects.ICrashable;
import android.runningbeaver.objects.IExplosionAnimation;
import android.runningbeaver.objects.Meteor;
import android.runningbeaver.objects.Position;

public class Demolition extends Skill {

	public Demolition(AModel model, Position position) {
		super(model, position);
		
		// set skill attributes
		duration = Config.skill.demolition.duration;
		cooldown = Config.skill.demolition.cooldown;
		unlock = Config.skill.demolition.unlock;
	}

	@Override
	public void run() {
		ArrayList<ICrashable> crashableObjects = Game.getInstance().getCrashInvoker().getListenerList();
		
		for (ICrashable object : crashableObjects) {
			if (object instanceof Meteor && object instanceof IExplosionAnimation) {
				((IExplosionAnimation) (object)).startExplosionAnimation();
			}
		}

	}

	@Override
	public void stop() {
		;
	}

}
