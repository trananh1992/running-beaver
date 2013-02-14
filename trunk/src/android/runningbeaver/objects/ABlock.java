package android.runningbeaver.objects;

import android.graphics.Canvas;
import android.runningbeaver.models.AModel;
import android.runningbeaver.models.OneLoopGifImage;

public abstract class ABlock extends AGameObject implements IDestroyable {

	protected boolean destroyable = true;

	public ABlock(AModel model, Position position, Direction direction,
			int drawLayer) {
		super(model, position, direction, drawLayer);
	}

	@Override
	public void draw(Canvas canvas) {
		if (getModel().isOneLoopAnimation()) {
			if (((OneLoopGifImage) (getModel().getImage())).isFinished()) {

				// unregister object -> destroy
				destroy();
				return;
			}
		}

		super.draw(canvas);
	}

	@Override
	public void destroy() {

		// unDestroyable!
		if (destroyable == false)
			return;

		unCrashable();
		unMovable();
		super.hide();
	}

	@Override
	public void destroyable() {
		destroyable = true;
	}

	@Override
	public void unDestroyable() {
		destroyable = false;
	}

}
