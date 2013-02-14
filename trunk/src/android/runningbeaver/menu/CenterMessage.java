package android.runningbeaver.menu;

import android.graphics.Paint;
import android.runningbeaver.engine.Game;
import android.runningbeaver.objects.Position;

public class CenterMessage extends Message {

	public CenterMessage(String text, Paint paint, int duration) {
		super(text, paint, getCenterPosition(text), duration);
	}
	
	private static Position getCenterPosition(String text) {
		
		float x = (Game.getInstance().getDevice().getScreenWidth() / 2) - text.length() * 8;
		float y = (Game.getInstance().getDevice().getScreenHeight() / 2) - 10;
		
		return new Position(x, y);
	}

}
