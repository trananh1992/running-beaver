package android.runningbeaver;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.runningbeaver.engine.GameLogic;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;


public class Game extends Activity  {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new GameView(this));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu mymenu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.game_menu, mymenu);
	    return true;
	}
	
    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }
    
    private static class GameView extends View {

		public GameView(Context context) {
			super(context);
			setFocusable(false);
			
			
			GameLogic.initialize(context);
			
			
			// game logic here
			
			
		}
		
		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			GameLogic.getInstance().draw(canvas);
		}
    	
    }
    
}
