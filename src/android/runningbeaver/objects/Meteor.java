package android.runningbeaver.objects;

import android.runningbeaver.engine.Duration;
import android.runningbeaver.engine.Game;
import android.runningbeaver.menu.Message;
import android.runningbeaver.models.MExplosion;
import android.runningbeaver.models.MMeteor;

public final class Meteor extends ABlock implements IExplosionAnimation {

	public Meteor(Position position, Direction direction, int drawLayer) {
		super(new MMeteor(), position, direction, drawLayer);
	}

	@Override
	public void crash(ICrashable object) {
		
		if (object instanceof Player) {

			startExplosionAnimation();
			
			if (Game.getInstance().getMenuConfig().getFeedback()) {
				new Message("YOU CRASHED!", Message.defaultPaint(), position, Duration.SHORT);
			}
			
		}
		
		if (object instanceof ICollectable) {
			startExplosionAnimation();
		}
		
	}

	@Override
	public void move() {

		if (position.getY() > Game.getInstance().getDevice().getScreenHeight() - 30
				&& getDirection().getDy() > 0) {
			startExplosionAnimation();
		}

		position = new Position(position.getX() + getDirection().getDxFloat(),
				position.getY() + getDirection().getDyFloat());
	}

	public void startExplosionAnimation() {

		// start animation
		setDirection(new Direction(0.0, 0.0));
		setModel(new MExplosion());
		unCrashable();
	}

}
