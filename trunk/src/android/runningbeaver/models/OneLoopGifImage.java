package android.runningbeaver.models;

import android.graphics.Movie;
import android.runningbeaver.engine.Game;

public class OneLoopGifImage extends GifImage implements IAnimation {

	public OneLoopGifImage(Movie movie) {
		super(movie);
	}

	@Override
	public boolean isFinished() {
		return (movieStart + duration < android.os.SystemClock.uptimeMillis());
	}

	public static OneLoopGifImage load(int imageFlag) {
		return new OneLoopGifImage(Movie.decodeStream(Game.getInstance()
				.getContext().getResources().openRawResource(imageFlag)));
	}

}
