package android.runningbeaver.objects;


import android.runningbeaver.engine.Config;
import android.runningbeaver.engine.Duration;
import android.runningbeaver.engine.Game;
import android.runningbeaver.menu.CenterMessage;
import android.runningbeaver.menu.Message;
import android.runningbeaver.models.AModel;
import android.runningbeaver.models.MLeftPlayer;


public class Player extends AGameObject {

	private final static AModel initModel = new MLeftPlayer();
	private final static Position initPosition = new Position(40, 400);
	private final static Direction initDirection = new Direction(0.0, 0.0); 

	private IBehavior jump;
	private IBehavior moveLeft;
	private IBehavior moveRight;

	private boolean jumping;
	
	private boolean absorb = false;

	public Player(int drawLayer) {
		super(initModel, initPosition, initDirection, drawLayer);

		// load default Behaviors
		jump = new BJump();
		moveLeft = new BMoveLeft();
		moveRight = new BMoveRight();
	}

	@Override
	public void crash(ICrashable object) {

		// player crashed with meteor
		if (object instanceof Meteor) {
			loseLifes(Config.meteor.lifeReduce);
			if (absorb) Game.getInstance().getStatistic().addScore(Config.skill.absorb.points);
		}

	}

	@Override
	public void move() {

		// confine right side
		if (position.getX() + getModel().getWidth() > Game.getInstance().getDevice()
				.getScreenWidth() && getDirection().getDx() > 0) {
			setDirection(new Direction(0.0, getDirection().getDy()));
		}

		// confine left side
		if (position.getX() < 0 && getDirection().getDx() < 0) {
			setDirection(new Direction(0.0, getDirection().getDy()));
		}

		// jump controll
		if (isJumping()) {
			jump.run();
		} else if (getDirection().getDy() > 0) {
			// jump direction ends
			setDirection(new Direction(getDirection().getDx(), 0.0));
		}

		// move my player
		super.move();
		
		// next touch event must be triggerd
		setDirection(new Direction(0.0, 0.0));
	}

	private void loseLifes(int lifes) {

		Game.getInstance().getStatistic().addLifes(-lifes);

		if (Game.getInstance().getStatistic().getPlayer().getLifes() <= 0) {
			
			// ToDo change model
			new CenterMessage("GAME OVER", Message.defaultPaint(), Duration.LONG);
			
			if (Game.getInstance().getMenuConfig().getEffects() && 
				Game.getInstance().getStatistic().getPlayer().getLifes() == 0) {
				Game.getInstance().getSoundManager().stopBackgroundMusic();
				Game.getInstance().getSoundManager().playGameOverSound(); //TODO FIX
			}
			
			Game.getInstance().setDrawState(true);
			Game.getInstance().freezeGame();

		}
	}

	public IBehavior getJump() {
		return jump;
	}

	public IBehavior getMoveLeft() {
		return moveLeft;
	}

	public IBehavior getMoveRight() {
		return moveRight;
	}

	public boolean isJumping() {
		return jumping;
	}

	public synchronized void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	
	public void setAbsorb(boolean absorb) {
		this.absorb = absorb;
	}

}