package android.runningbeaver.objects;

import android.graphics.Canvas;
import android.runningbeaver.engine.Game;
import android.runningbeaver.models.BitmapImage;
import android.runningbeaver.models.IImage;

public class Background implements IDrawable {
	
	private IImage image;
	private boolean drawable = false;
	private Position position = new Position(0,0);
	
	public Background(IImage image) {
		setImage(image);
		show();
	}

	@Override
	public void draw(Canvas canvas) {
		if (!drawable) return;
		image.draw(canvas, position);
	}

	@Override
	public void hide() {
		drawable = false;
		
	}

	@Override
	public void show() {
		drawable = true;
	}
	
	public void setImage(IImage image) {
		
		double scaleWidth = (double) Game.getInstance().getDevice().getScreenWidth() / (double) image.getWidth();
		double scaleHight = (double) Game.getInstance().getDevice().getScreenHeight() / (double) image.getHeight();
		
		((BitmapImage) (image)).scaleImage(scaleWidth, scaleHight);
		
		this.image = image;
	}
	
}
