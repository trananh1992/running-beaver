package android.runningbeaver.engine;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public final class MenuConfig {
	
	private SharedPreferences preferences;
	private SharedPreferences.Editor editor;
	
	public MenuConfig(Activity activity) {
		preferences = PreferenceManager.getDefaultSharedPreferences(activity);
		editor = preferences.edit();
	}
	
	public void setBoolean(String key, boolean value) {
		editor.putBoolean(key, value);
		editor.commit();
	}
	
	public void setInteger(String key, int value) {
		editor.putInt(key, value);
		editor.commit();
	}
	
	public boolean getBoolean(String key) {
		return preferences.getBoolean(key, true);
	}
	
	public int getInteger(String key) {
		return preferences.getInt(key, 1);
	}
	
	// effects, music, control, feedback
	
	// SETTER
	public void setEffects(boolean value) {
		setBoolean("effects", value);
	}
	
	public void setMusic(boolean value) {
		setBoolean("music", value);
	}
	
	public void setFeedback(boolean value) {
		setBoolean("feedback", value);
	}
	
	public void setControls(int value) {
		setInteger("controls", value);
	}
	
	// GETTER
	public boolean getEffects() {
		return getBoolean("effects");
	}
	
	public boolean getMusic() {
		return getBoolean("music");
	}
	
	public boolean getFeedback() {
		return getBoolean("feedback");
	}
	
	public int getControls() {
		return getInteger("controls");
	}

}
