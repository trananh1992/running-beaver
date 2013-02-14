package android.runningbeaver.engine;

import android.runningbeaver.commands.CPlayerJump;
import android.runningbeaver.commands.CPlayerLeft;
import android.runningbeaver.commands.CPlayerRight;
import android.runningbeaver.objects.Surface;
import android.runningbeaver.objects.TouchObject;
import android.view.MotionEvent;

public class TouchEventHandler {

	private float lastTouchX = 0;
	private float lastTouchY = 0;
	
	private float touchX = 0;
	private float touchY = 0;
	
	private int motionEventKey = MotionEvent.ACTION_UP;
	
	public TouchEventHandler() {
		playerControl();
	}

	public void onTouch(MotionEvent ev) {
		
		motionEventKey = ev.getAction();

		if (motionEventKey != MotionEvent.ACTION_UP) {
			lastTouchX = touchX;
			lastTouchY = touchY;
			
			touchX = ev.getX();
			touchY = ev.getY();
		}

	}

	/**
	 * process behaivors..
	 */
	private void playerControl() {
		
		int halfWidth = Game.getInstance().getDevice().getScreenWidth() / 2;
		int halfHeight = Game.getInstance().getDevice().getScreenHeight() / 2;
		
		// TOP LEFT
		TouchObject topLeft = new TouchObject(new Surface(0, 0, halfWidth, halfHeight), ALayerInvoker.LOW);
		
		topLeft.addCommando(new CPlayerLeft());
		topLeft.addCommando(new CPlayerJump());
		
		// TOP RIGHT
		TouchObject topRight = new TouchObject(new Surface(halfWidth, 0, halfWidth, halfHeight), ALayerInvoker.LOW);
		
		topRight.addCommando(new CPlayerRight());
		topRight.addCommando(new CPlayerJump());
		
		// BOTTOM LEFT
		TouchObject bottomLeft = new TouchObject(new Surface(0, halfHeight, halfWidth, halfHeight), ALayerInvoker.LOW);
		
		bottomLeft.addCommando(new CPlayerLeft());
		
		// BOTTOM RIGHT
		TouchObject bottomRight = new TouchObject(new Surface(halfWidth, halfHeight, halfWidth, halfHeight), ALayerInvoker.LOW);
		
		bottomRight.addCommando(new CPlayerRight());

	}

	public float getTouchX() {
		return touchX;
	}
	
	public float getTouchY() {
		return touchY;
	}
	
	public float getLastTouchX() {
		return lastTouchX;
	}

	public float getLastTouchY() {
		return lastTouchY;
	}
	
	public Surface getSurface() {
		return new Surface(touchX, touchY, 1, 1);
	}
	
	public int getMotionEventKey() {
		return motionEventKey;
	}

}
