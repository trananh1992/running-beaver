package android.runningbeaver.menu;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.runningbeaver.commands.CHide;
import android.runningbeaver.commands.IStopable;
import android.runningbeaver.engine.ALayerInvoker;
import android.runningbeaver.engine.DrawInvoker;
import android.runningbeaver.engine.Duration;
import android.runningbeaver.engine.Game;
import android.runningbeaver.objects.IDrawable;
import android.runningbeaver.objects.Position;

public class Message implements IDrawable, IStopable {
	
	private String text;
	private Paint paint;
	private Position position;
	
	public Message(String text, Paint paint, Position position, int duration) {
		this.text = text;
		this.paint = paint;
		this.position = position;
		
		new Duration(duration).addCommando(new CHide(this));
		
		show();
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.drawText(text, position.getX(), position.getY(), paint);
	}
	
	@Override
	public void show() {
		Game.getInstance().getDrawInvoker().register(this, ALayerInvoker.HIGH);
	}

	@Override
	public void hide() {
		Game.getInstance().getDrawInvoker().unRegister(this);
	}
	
	@Override
	public void stop() {
		hide();
	}
	
	public static Paint defaultPaint() {
		
		Paint paint = new Paint();
		paint.setColor(Color.CYAN);
		paint.setTextSize(30);
		
		return paint;
	}

}
