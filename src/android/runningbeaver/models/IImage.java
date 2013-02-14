package android.runningbeaver.models;

import android.graphics.Canvas;
import android.runningbeaver.objects.Position;

public interface IImage {

	public void draw(Canvas canvas, Position position);

	public int getHeight();

	public int getWidth();

}
