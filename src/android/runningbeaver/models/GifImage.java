package android.runningbeaver.models;

import android.graphics.Canvas;
import android.graphics.Movie;
import android.runningbeaver.engine.Game;
import android.runningbeaver.objects.Position;

public class GifImage implements IImage {

	private Movie movie;
	protected long movieStart;
	protected int duration;

	public GifImage(Movie movie) {
		this.movie = movie;
		movieStart = android.os.SystemClock.uptimeMillis();
		duration = movie.duration();
	}

	@Override
	public void draw(Canvas canvas, Position position) {

		// set (new) movie time for animation
		movie.setTime((int) ((android.os.SystemClock.uptimeMillis() - movieStart) % duration));

		// draw move on canvas
		movie.draw(canvas, position.getX(), position.getY());
	}

	@Override
	public int getWidth() {
		return movie.width();
	}

	@Override
	public int getHeight() {
		return movie.height();
	}

	public static GifImage load(int imageFlag) {
		return new GifImage(Movie.decodeStream(Game.getInstance().getContext()
				.getResources().openRawResource(imageFlag)));
	}

}
