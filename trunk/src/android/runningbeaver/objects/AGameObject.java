package android.runningbeaver.objects;

import android.runningbeaver.engine.Game;
import android.runningbeaver.models.AModel;

public abstract class AGameObject extends AAppearance implements IMoveable,
		ICrashable {

	private Direction direction;

	public AGameObject(AModel model, Position position, Direction direction,
			int drawLayer) {
		super(model, position, drawLayer);
		this.direction = direction;

		movable();
		crashable();
	}

	@Override
	public void move() {
		position = new Position(position.getX() + direction.getDxFloat(),
				position.getY() + direction.getDyFloat());
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	@Override
	public void movable() {
		Game.getInstance().getMoveInvoker().register(this);
	}

	@Override
	public void unMovable() {
		Game.getInstance().getMoveInvoker().unRegister(this);
	}

	@Override
	public void crashable() {
		Game.getInstance().getCrashInvoker().register(this);
	}

	@Override
	public void unCrashable() {
		Game.getInstance().getCrashInvoker().unRegister(this);
	}

}
