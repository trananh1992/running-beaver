package android.runningbeaver.menu;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.runningbeaver.engine.Game;
import android.runningbeaver.engine.Game.GameStatistic.PlayerStatistic;
import android.runningbeaver.engine.Game.GameStatistic.SystemStatistic;
import android.runningbeaver.models.AModel;
import android.runningbeaver.objects.Position;

public class ScoreBoard extends AMenue {

	public ScoreBoard(AModel model, Position position,
			int drawLayer) {
		super(model, position, drawLayer);
	}

	@Override
	public void draw(Canvas canvas) {

		PlayerStatistic playerStatistic = Game.getInstance().getStatistic()
				.getPlayer();
		SystemStatistic systemStatistic = Game.getInstance().getStatistic()
				.getSystem();

		// set default text styles
		Paint paint = new Paint();
		paint.setColor(Color.CYAN);
		paint.setTextSize(16);

		// draw background
		super.draw(canvas);

		// draw score and level
		canvas.drawText("" + playerStatistic.getScore(), position.getX() + 155,
				position.getY() + 18, paint);
		canvas.drawText("" + systemStatistic.getLevel(), position.getX() + 155,
				position.getY() + 40, paint);

		// set live text style
		paint.setTextSize(20);

		// draw lifes
		canvas.drawText("" + playerStatistic.getLifes(), position.getX() + 50,
				position.getY() + 28, paint);

	}

}
