package android.runningbeaver.menu;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.runningbeaver.commands.CRun;
import android.runningbeaver.commands.ICommand;
import android.runningbeaver.commands.ICommandList;
import android.runningbeaver.engine.Clock;
import android.runningbeaver.engine.Duration;
import android.runningbeaver.engine.Game;
import android.runningbeaver.objects.IDrawable;
import android.runningbeaver.objects.Position;

public class Countdown implements IDrawable, Runnable, ICommandList {
	
	private static int DURATION_TIME = 100;
	
	private Position position;
	private Paint paint;
	private int drawLayer;
	private long duration;
	
	private ArrayList<ICommand> commands = new ArrayList<ICommand>();
	
	public Countdown(Position position, Paint paint, int drawLayer, long duration) {
		this.position = position;
		this.paint = paint;
		this.drawLayer = drawLayer;
		this.duration = duration + DURATION_TIME;
		run();
		show();
	}
	
	@Override
	public void run() {
		if (duration <= 0) {
			over(); // countdown is over
			return;
		}
		
		if (duration < 2000) {
			paint.setColor(Color.RED);
		}
		
		duration -= 100;
		
		new Duration(100).addCommando(new CRun(this));
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawText(new Clock(duration).getText(), position.getX(), position.getY(), paint);
	}

	@Override
	public void show() {
		Game.getInstance().getDrawInvoker().register(this, drawLayer);
	}
	
	@Override
	public void hide() {
		Game.getInstance().getDrawInvoker().unRegister(this);
	}
	
	public static Paint defaultPaint() {
		
		Paint paint = new Paint();
		paint.setColor(Color.CYAN);
		paint.setTextSize(20);
		
		return paint;
	}
	
	private void over() {
		// run over commands
		for (ICommand command : commands) {
			command.run();
		}
		hide(); // hide countdown
	}

	@Override
	public void addCommando(ICommand command) {
		commands.add(command);
	}

	@Override
	public void removeCommando(ICommand command) {
		commands.remove(command);
	}

}
