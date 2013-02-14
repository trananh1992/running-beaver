package android.runningbeaver;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class RunningBeaverActivity extends TabActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		/** TabHost will have Tabs */
		TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);

		/**
		 * TabSpec used to create a new tab. By using TabSpec only we can able
		 * to setContent to the tab. By using TabSpec setIndicator() we can set
		 * name to tab.
		 */

		/** tid1 is firstTabSpec Id. Its used to access outside. */
		TabSpec GameTabSpec = tabHost.newTabSpec("tid1");
		TabSpec SettingsTabSpec = tabHost.newTabSpec("tid1");
		TabSpec HelpTabSpec = tabHost.newTabSpec("tid1");

		/** TabSpec setIndicator() is used to set name for the tab. */
		/** TabSpec setContent() is used to set content for a particular tab. */
		GameTabSpec.setIndicator("Spiel").setContent(
				new Intent(this, GameTab.class));
		SettingsTabSpec.setIndicator("Einstellungen").setContent(
				new Intent(this, SettingsTab.class));
		HelpTabSpec.setIndicator("Hilfe").setContent(
				new Intent(this, HelpTab.class));

		/** Add tabSpec to the TabHost to display. */
		tabHost.addTab(GameTabSpec);
		tabHost.addTab(SettingsTabSpec);
		tabHost.addTab(HelpTabSpec);

	}
}