package android.runningbeaver;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.runningbeaver.engine.Config;
import android.runningbeaver.engine.Device;
import android.runningbeaver.engine.Game;
import android.runningbeaver.engine.MemoryManager;
import android.runningbeaver.engine.MenuConfig;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

public class GameActivity extends Activity {

	private MenuConfig menuConfig;
	private Bundle bundle;
	private Device device;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// do not restart game after "resume"
		if (Game.getInstance() != null) return;

		// init Device
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		device = new Device(metrics.widthPixels, metrics.heightPixels);
		
		menuConfig = new MenuConfig(this);
		bundle = getIntent().getExtras();

		setContentView(new GameView(this));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.game_menu_exit_save:
			
			// save game instance
			MemoryManager.saveGame();
			
			// destroy game
			Game.getInstance().freezeGame();
			
			finish();
			return true;
		case R.id.game_menu_exit_dontsave:
			
			// destroy game
			Game.getInstance().freezeGame();
			
			finish();
			return true;
		case R.id.game_menu_save:
			
			// save game instance
			MemoryManager.saveGame();
			
			return true;
		case R.id.game_menu_music:

			boolean music = (menuConfig.getMusic()) ? false : true;
			menuConfig.setMusic(music);
			
			if (music) {
				Game.getInstance().getSoundManager().startBackgroundMusic();
			} else {
				Game.getInstance().getSoundManager().stopBackgroundMusic();
			}

			return true;
		case R.id.game_menu_effects:

			boolean effect = (menuConfig.getEffects()) ? false : true;
			menuConfig.setEffects(effect);
			
			return true;
		case R.id.game_menu_fededback:
			
			boolean feedback = (menuConfig.getFeedback()) ? false : true;
			menuConfig.setFeedback(feedback);
			
			return true;
		default:
			return true;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return super.onKeyDown(keyCode, event);
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

	private class GameView extends View {

		private RefreshHandler drawHandler = new RefreshHandler();

		public GameView(Context context) {
			super(context);
			setFocusable(false);

			// init GameLogic
			if (bundle.getBoolean("load")) {
				
				// load game
				MemoryManager.loadGame(context);
			}
			
			if (Game.getInstance() == null) {
				
				// new game
				Game.initialize(context, menuConfig, device);
			}

			// first draw
			draw();

		}

		protected void draw() {
			if (Game.getInstance().isDrawState()) {
				invalidate();
			}

			drawHandler.sleep(Config.system.drawHandler);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			Game.getInstance().draw(canvas);
		}

		@Override
		public boolean onTouchEvent(MotionEvent ev) {

			Game.getInstance().touchEvent(ev);

			return true;
		}

		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {

			// key pressed for key_menu
			if (keyCode == KeyEvent.KEYCODE_MENU) {
				// freeze game in game menu
				Game.getInstance().freezeGame();
			}

			return super.onKeyDown(keyCode, event);
		}

		private class RefreshHandler extends Handler {

			@Override
			public void handleMessage(Message msg) {
				draw();
			}

			public void sleep(long delayMillis) {
				this.removeMessages(0);
				sendMessageDelayed(obtainMessage(0), delayMillis);
			}

		}

	}

}
