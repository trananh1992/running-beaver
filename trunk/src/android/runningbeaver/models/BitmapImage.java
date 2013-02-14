package android.runningbeaver.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.runningbeaver.engine.Game;
import android.runningbeaver.objects.Position;

public class BitmapImage implements IImage {

	private Bitmap bitmap;

	public BitmapImage(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	@Override
	public void draw(Canvas canvas, Position position) {
		canvas.drawBitmap(bitmap, position.getX(), position.getY(), null);
	}

	@Override
	public int getWidth() {
		return bitmap.getWidth();
	}

	@Override
	public int getHeight() {
		return bitmap.getHeight();
	}

	public void scaleImage(double scaleWidth, double scaleHeight) {

		int newWidth = (int) (bitmap.getWidth() * scaleWidth);
		int newHeight = (int) (bitmap.getHeight() * scaleHeight);

		bitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);
	}

	public static BitmapImage load(int imageFlag) {
		return new BitmapImage(BitmapFactory.decodeStream(Game.getInstance()
				.getContext().getResources().openRawResource(imageFlag)));
	}

}
