package android.runningbeaver.engine;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.runningbeaver.objects.AAppearance;

public final class GameLogic {
	
	private static GameLogic instance;
	private static Context context;
	
	private ArrayList<AAppearance> drawableObjects;

	private CrashInvoker crashInvoker = new CrashInvoker();
	private DirectionInvoker directionInvoker = new DirectionInvoker();
	
	private GameLogic(Context context) {
		super();
		GameLogic.context = context;
	}
	
	public static void initialize(Context context) {
		instance = new GameLogic(context);
	}
	
	public static GameLogic getInstance() {
		// todo: ex einbauen
		return instance;
	}

	public CrashInvoker getCrashInvoker() {
		return crashInvoker;
	}

	public DirectionInvoker getDirectionInvoker() {
		return directionInvoker;
	}	
	
	public void registerDrawableObject(AAppearance object) {
		this.drawableObjects.add(object);
	}
	
	public void draw(Canvas canvas) {
		for (AAppearance drawObject : this.drawableObjects) {
			drawObject.draw(canvas);
		}
	}

	public static Context getContext() {
		return context;
	}
	
	

}
