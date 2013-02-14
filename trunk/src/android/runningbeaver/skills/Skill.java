package android.runningbeaver.skills;

import android.runningbeaver.commands.CStop;
import android.runningbeaver.commands.IStopable;
import android.runningbeaver.engine.ALayerInvoker;
import android.runningbeaver.engine.DrawInvoker;
import android.runningbeaver.engine.Duration;
import android.runningbeaver.engine.Game;
import android.runningbeaver.menu.CenterMessage;
import android.runningbeaver.menu.Countdown;
import android.runningbeaver.models.AModel;
import android.runningbeaver.objects.AAppearance;
import android.runningbeaver.objects.ITouchable;
import android.runningbeaver.objects.Position;


public abstract class Skill extends AAppearance implements ITouchable, ISkill, IStopable {
	
	protected int duration = 0;
	protected int cooldown = 0;
	protected int unlock = 0;
	
	protected boolean active = false;

	public Skill(AModel model, Position position) {
		super(model, position, DrawInvoker.HIGH);
		hide();
	}

	@Override
	public void touchable() {
		Game.getInstance().getTouchInvoker().register(this, ALayerInvoker.HIGH);
	}

	@Override
	public void unTouchable() {
		Game.getInstance().getTouchInvoker().unRegister(this);
	}
	
	@Override
	public void unlock(int lvl) {
		if (unlock != lvl) return;
		
		// activate skill
		touchable();
		show();
		
		// new SkillMessage
		new CenterMessage("NEW SKILL", CenterMessage.defaultPaint(), Duration.NORMAL);
	}
	
	@Override
	public void onTouch() {
		if (!active) {
			active = true;
			run();
		}
	}
	
	@Override
	public void run() {
		if (duration > 0) {
			
			// init countdown with stop command
			Position cPosition = new Position(position.getX() + 5, position.getY() + 80);
			new Countdown(cPosition, Countdown.defaultPaint(), DrawInvoker.HIGH, duration).addCommando(new CStop(this));
		}
	}
	
	@Override
	public void stop() {
		active = false;
	}
	
}
