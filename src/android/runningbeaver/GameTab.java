package android.runningbeaver;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class GameTab extends ListActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, PLACES));
		ListView lv = getListView();
		lv.setTextFilterEnabled(true);
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				if (((TextView) view).getText().equals("Spielen")) {
					Intent intent = new Intent(GameTab.this, GameActivity.class);
					intent.putExtra("load", false);
					startActivity(intent);
				}
				
				if (((TextView) view).getText().equals("Weiter")) {
					Intent intent = new Intent(GameTab.this, GameActivity.class);
					intent.putExtra("load", true);
					startActivity(intent);
				}
			}
		});
	}

	static final String[] PLACES = new String[] { "Spielen", "Weiter",
			"Spielstände", "Insane Mode" };
}