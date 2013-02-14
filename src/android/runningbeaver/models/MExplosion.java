package android.runningbeaver.models;

import android.runningbeaver.R;
import android.runningbeaver.engine.Game;

public final class MExplosion extends AModel {

	public MExplosion() {
		super(OneLoopGifImage.load(R.drawable.explosion), true);
		
		if (Game.getInstance().getMenuConfig().getEffects()) {
			Game.getInstance().getSoundManager().playExplosionSound();
		}
	}

}
