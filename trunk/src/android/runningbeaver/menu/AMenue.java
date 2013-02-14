package android.runningbeaver.menu;

import android.runningbeaver.models.AModel;
import android.runningbeaver.objects.AAppearance;
import android.runningbeaver.objects.Position;

public abstract class AMenue extends AAppearance {

	public AMenue(AModel model, Position position, int drawLayer) {
		super(model, position, drawLayer);
	}

}
