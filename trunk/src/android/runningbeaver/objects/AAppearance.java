package android.runningbeaver.objects;

import android.graphics.Canvas;
import android.runningbeaver.engine.Game;
import android.runningbeaver.models.AModel;

public abstract class AAppearance implements IDrawable {

	protected Position position;
	private AModel model;

	private int drawLayer;

	public AAppearance(AModel model, Position position, int drawLayer) {
		super();
		this.model = model;
		this.position = position;
		this.drawLayer = drawLayer;

		show();
	}

	public AModel getModel() {
		return model;
	}

	public void setModel(AModel model) {
		this.model = model;
	}

	public void setDrawLayer(int layer) {
		hide();
		drawLayer = layer;
		show();
	}

	@Override
	public void draw(Canvas canvas) {
		model.draw(canvas, position);
	}

	public Surface getSurface() {
		return new Surface(position.getX(), position.getY(), model.getWidth(),
				model.getHeight());
	}

	@Override
	public void hide() {
		Game.getInstance().getDrawInvoker().unRegister(this);
	}

	@Override
	public void show() {
		Game.getInstance().getDrawInvoker().register(this, drawLayer);
	}

}
