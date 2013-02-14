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

public class HelpTab extends ListActivity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, PLACES));
		ListView lv = getListView();
		lv.setTextFilterEnabled(true);
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// When clicked, show a toast with the TextView text
				if (((TextView) view).getText().equals("Anleitung")) {
					Intent intent = new Intent(HelpTab.this, anleitung.class);
					startActivity(intent);
				} else {
					Intent intent = new Intent(HelpTab.this, credits.class);
					startActivity(intent);
				}
			}
		});
	}

	static final String[] PLACES = new String[] { "Credits", "Anleitung" };
}