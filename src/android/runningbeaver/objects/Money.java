package android.runningbeaver.objects;

import android.runningbeaver.engine.Config;
import android.runningbeaver.engine.Game;
import android.runningbeaver.models.MMoney;

public class Money extends ABlock implements ICollectable {

	protected boolean collectable = true;

	public Money(Position position, Direction direction, int drawLayer) {
		super(new MMoney(), position, direction, drawLayer);
	}

	@Override
	public void collect() {
		// not collectable
		if (collectable == false)
			return;

		Game.getInstance().getStatistic().addScore(Config.boni.money.score);  
		if (Game.getInstance().getMenuConfig().getEffects()) {
			Game.getInstance().getSoundManager().playCoinSound();
		}

		destroy();
	}

	@Override
	public void collectable() {
		collectable = true;
	}

	@Override
	public void unCollectable() {
		collectable = false;
	}

	@Override
	public void crash(ICrashable object) {

		if (object instanceof Player) {
			collect(); // trigger collect
		}
		
		if (object instanceof Meteor) {
			destroy(); // trigger destroy
		}

	}

}
