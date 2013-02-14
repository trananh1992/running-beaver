package android.runningbeaver.skills;

import java.util.ArrayList;

import android.runningbeaver.commands.CRun;
import android.runningbeaver.engine.Config;
import android.runningbeaver.engine.Duration;
import android.runningbeaver.engine.Game;
import android.runningbeaver.models.AModel;
import android.runningbeaver.objects.ICrashable;
import android.runningbeaver.objects.IExplosionAnimation;
import android.runningbeaver.objects.Meteor;
import android.runningbeaver.objects.Position;
import android.runningbeaver.objects.Surface;

public class SchockWave extends Skill implements Runnable {
	
	private int delay = Config.skill.schockWave.delay;
	private int addRange = Config.skill.schockWave.range;
	
	private int range = 0;
	private Surface playerSurface;
	
	public SchockWave(AModel model, Position position) {
		super(model, position);
		
		// set skill attributes
		duration = Config.skill.schockWave.duration;
		cooldown = Config.skill.schockWave.cooldown;
		unlock = Config.skill.schockWave.unlock;
	}

	@Override
	public void run() {
		
		if (range == 0) {
			playerSurface = Game.getInstance().getPlayer().getSurface();
			super.run();
		}
		
		if (!active) {
			range = 0;
			return;
		}
		 
		range += Config.skill.schockWave.range;
		
		ArrayList<ICrashable> crashableObjects = Game.getInstance().getCrashInvoker().getListenerList();
		
		for (ICrashable object : crashableObjects) {
			if (object instanceof Meteor) {
				
				Surface surface = ((Meteor) (object)).getSurface();
				
				// c = (a^2 + b^2)^(1/2)
				int desistance = (int) Math.pow((Math.pow(surface.getX() - playerSurface.getX(), 2) + Math.pow(surface.getY() - playerSurface.getY(), 2)), 0.5);
				
				if (desistance < range) {
					((IExplosionAnimation) (object)).startExplosionAnimation();
				}
			}
		}
		
		new Duration(Config.skill.schockWave.delay).addCommando(new CRun(this));
	}

}
