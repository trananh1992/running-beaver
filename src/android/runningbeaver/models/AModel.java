package android.runningbeaver.models;

import android.graphics.Canvas;
import android.runningbeaver.objects.Position;

public abstract class AModel implements IImage {

	private IImage image;
	private boolean oneLoopAnimation;
	int width;
	int height;

	public AModel(IImage image, boolean oneLoopAnimation) {
		this.image = image;
		this.oneLoopAnimation = oneLoopAnimation;
		this.width = image.getWidth();
		this.height = image.getHeight();
	}

	@Override
	public void draw(Canvas canvas, Position position) {
		image.draw(canvas, position);
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	public boolean isOneLoopAnimation() {
		return oneLoopAnimation;
	}

	public IImage getImage() {
		return image;
	}

}
