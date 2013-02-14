package android.runningbeaver.objects;

import android.runningbeaver.engine.Config;
import android.runningbeaver.engine.Game;
import android.runningbeaver.models.MLife;

public class Life extends ABlock implements ICollectable {
	
	protected boolean collectable = true;

	public Life(Position position, Direction direction, int drawLayer) {
		super(new MLife(), position, direction, drawLayer);	
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

	@Override
	public void collect() {
		if (collectable == false)
			return; 
		Game.getInstance().getStatistic().addLifes(Config.boni.heart.lifeBonus); 
	
		destroy();
		
	}

	@Override
	public void collectable() {
		this.collectable = true;
		
	}

	@Override
	public void unCollectable() {
		this.collectable = false;
		
	}

}
